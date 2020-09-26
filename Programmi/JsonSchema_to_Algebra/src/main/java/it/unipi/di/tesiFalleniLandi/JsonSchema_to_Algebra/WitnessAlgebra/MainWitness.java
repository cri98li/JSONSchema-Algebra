package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MainWitness {
    private static Logger logger = LogManager.getLogger(MainWitness.class);

    public static void main(String[] args) throws IOException, WitnessException, REException {
        //executeTests();

        String path = "test.algebra";

        Assertion schema = Utils_FullAlgebra.parseFile(path);

        WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema);
        env.buildOBDD_notElimination();

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        env = (WitnessEnv) env.merge(null, null);

        System.out.println("\r\n\r\n Merge: \r\n");
        System.out.flush();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Groupize: \r\n");
        System.out.flush();

        env = env.groupize();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Separation: \r\n");
        System.out.flush();

        env.varNormalization_separation(null, null);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Expansion: \r\n");
        System.out.flush();

        env = env.varNormalization_expansion(null);

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));


        System.out.println("\r\n\r\n DNF: \r\n");
        System.out.flush();

        env = env.DNF();
        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        Collection<Map.Entry<WitnessVar, ReportResults>> o1 = env.getReport();
        printReportResult(o1);

        System.out.println("\r\n\r\n objectPrepare: \r\n");
        System.out.flush();

        env.objectPrepare();

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n arrayPrepare: \r\n");
        System.out.flush();

        env.arrayPreparation();

        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n FINE! \r\n");
        System.out.flush();

        /*Collection<Map.Entry<WitnessVar, ReportResults>> o2 = env.getReport();
        printReportResult(o2);


        printReportResultCompare(o1, o2);*/

    }


    private static void printReportResult(Collection<Map.Entry<WitnessVar, ReportResults>> reportResult){
        for(Map.Entry<WitnessVar, ReportResults> entry : reportResult){
            StringBuilder b = new StringBuilder("VAR: ");
            b.append(entry.getKey().getName())
                    .append("\r\n\t #OR: ")
                    .append(entry.getValue().getNumOfElementInAnyOf())
                    .append("\t #AND: ")
                    .append(entry.getValue().getNumOfElementInAllOf());

            System.out.println(b.toString());
        }

        System.out.println("#var: "+reportResult.size());
    }

    //nomeFileJSON,operazione,#var,#OR,#AND
    private static void saveCSVReportResult(Collection<Map.Entry<WitnessVar, ReportResults>> reportResult, String path) throws IOException {
        BufferedWriter dest = new BufferedWriter(new FileWriter(path));

        for(Map.Entry<WitnessVar, ReportResults> entry : reportResult){
            StringBuilder b = new StringBuilder();
            b.append(entry.getKey().getName())
                    .append(",")
                    .append(entry.getValue().getNumOfElementInAnyOf())
                    .append(",")
                    .append(entry.getValue().getNumOfElementInAllOf())
                    .append("\r\n");

            dest.write(b.toString());
        }

        dest.close();
    }

    private static void executeTests() throws IOException {
        File folder = new File("./tredicik_1000/");
        File[] listOfFiles = folder.listFiles();

        List<Collection<Map.Entry<WitnessVar, ReportResults>>> reportResult1 = new LinkedList<>();
        List<Collection<Map.Entry<WitnessVar, ReportResults>>> reportResult2 = new LinkedList<>();
        List<String> fileJSON = new LinkedList<>();

        for (File file : listOfFiles) {
            try {
                System.out.println("ESEGUO: "+ file.getName());
                System.out.flush();

                JSONSchema js = Utils_JSONSchema.parse(file.getAbsolutePath());
                Assertion schema = Utils_FullAlgebra.parseString(Utils_JSONSchema.toGrammarString(Utils_JSONSchema.normalize(js)));
                WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema);

                env = (WitnessEnv) env.merge(null, null);
                env = env.groupize();
                env.varNormalization_separation(null, null);
                env = env.varNormalization_expansion(null);
                env = env.DNF();
                Object o1 = env.getReport();
                env.objectPrepare();
                Object o2 = env.getReport();

                //alla fine aggiungo alla lista
                fileJSON.add(file.getName());
                reportResult1.add((Collection<Map.Entry<WitnessVar, ReportResults>>) o1);
                reportResult2.add((Collection<Map.Entry<WitnessVar, ReportResults>>) o2);
            }catch (Exception ex){
                //ex.printStackTrace();
            }
        }

        CSVReportResult(reportResult1, reportResult2, "reportResult.csv", fileJSON);
    }

    //fileJSON, #varPreObj, #orPreObj, #varPostObj, #orPostObj
    private static void CSVReportResult(List<Collection<Map.Entry<WitnessVar, ReportResults>>> reportResult1,
                                        List<Collection<Map.Entry<WitnessVar, ReportResults>>> reportResult2,
                                        String csvPath,
                                        List<String> fileJSON) throws IOException {
        BufferedWriter dest = new BufferedWriter(new FileWriter(csvPath));

        for(int i = 0 ; i < fileJSON.size(); i++) {
            int sommaOr1 = 0;

            for (Map.Entry<WitnessVar, ReportResults> entry : reportResult1.get(i)) {
                sommaOr1 += entry.getValue().getNumOfElementInAnyOf();
            }

            int sommaOr2 = 0;

            for (Map.Entry<WitnessVar, ReportResults> entry : reportResult2.get(i)) {
                sommaOr2 += entry.getValue().getNumOfElementInAnyOf();
            }

            dest.write(fileJSON.get(i) + ", " + reportResult1.get(i).size() + ", " + sommaOr1 + ", " + reportResult2.get(i).size() + ", " + sommaOr2 + "\r\n");
        }
        dest.close();
    }

    private static void printReportResultCompare(Collection<Map.Entry<WitnessVar, ReportResults>> r1, Collection<Map.Entry<WitnessVar, ReportResults>> r2){
        HashMap<WitnessVar, Map.Entry<WitnessVar, ReportResults>> hr2 = new HashMap();

        for(Map.Entry<WitnessVar, ReportResults> entry : r2)
            hr2.put(entry.getKey(), entry);


        for(Map.Entry<WitnessVar, ReportResults> entry : r1){
            StringBuilder b = new StringBuilder("VAR: ");

            Map.Entry<WitnessVar, ReportResults> compareEntry = new AbstractMap.SimpleEntry(new WitnessVar("nomeMaiUsato!//##"), new ReportResults());
            if(hr2.containsKey(entry.getKey()))
                compareEntry = hr2.remove(entry.getKey());

            b.append(entry.getKey().getName())
                    .append("\r\n\t #OR: ")
                    .append(entry.getValue().getNumOfElementInAnyOf()).append(" vs ").append(compareEntry.getValue().getNumOfElementInAnyOf())
                    .append("\t #AND: ")
                    .append(entry.getValue().getNumOfElementInAllOf()).append(" vs ").append(compareEntry.getValue().getNumOfElementInAllOf());

            System.out.println(b.toString());
        }

        System.out.println("Remaining Value ["+hr2.size()+"]: ");
        printReportResult(hr2.values());

        System.out.println("#var: "+r1.size()+" vs "+ r2.size());
    }
}
