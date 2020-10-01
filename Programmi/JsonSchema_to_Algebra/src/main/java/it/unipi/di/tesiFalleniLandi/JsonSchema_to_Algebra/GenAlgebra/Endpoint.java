package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.Exceptions.WitnessException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessEnv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.io.FileWriter;
import java.io.IOException;

public class Endpoint {
    private static Logger logger = LogManager.getLogger(Endpoint.class);

    public static void main(String[] args) throws IOException, WitnessException, REException {


        String path = System.getProperty("user.dir")+ "/testFiles/";
        String file = "test"//"canon1" //"example_4-5"//
                ;
        String extension = ".algebra";
        String inputFileName = path+file+extension;

        Assertion schema = Utils_FullAlgebra.parseFile(inputFileName);
        logger.info("parsed ", inputFileName);
//        System.out.println("parsed "+inputFileName);
//        System.out.println(schema.toGrammarString());

        //System.out.println(schema.toGrammarString());

        WitnessEnv env = Utils_FullAlgebra.getWitnessAlgebra(schema);

        //WitnessEnv env = (WitnessEnv) schema.toWitnessAlgebra();

        env.buildOBDD_notElimination();

        env.checkLoopRef(null, null);

        System.out.println("\r\n\r\n Algebra: \r\n");

//        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        env = (WitnessEnv) env.merge(null, null);

        System.out.println("\r\n\r\n Merge: \r\n");
        System.out.flush();
//        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Groupize: \r\n");
        System.out.flush();

        env = env.groupize();
//        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n DNF: \r\n");
        System.out.flush();

        env = env.DNF();
//        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Separation: \r\n");
        System.out.flush();

        env.varNormalization_separation(null, null);

        env = env.DNF();

//        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n Expansion: \r\n");
        System.out.flush();

        env = env.varNormalization_expansion(null);

//        System.out.println(Utils.beauty(env.getFullAlgebra().toGrammarString()));

        System.out.println("\r\n\r\n objectPrepare: \r\n");
        System.out.flush();

        env = (WitnessEnv) env.merge(null, null);

        env.toOrPattReq();
        env.objectPrepare();

        String result = Utils.beauty(env.getFullAlgebra().toGrammarString());
//        System.out.println(env);
        String outputFileName = path+"prep_"+file+extension;
        FileWriter fw = new FileWriter(outputFileName);
        fw.write(result);
        fw.close();
        System.out.println("output "+ outputFileName);


//        System.out.println(env);

        System.out.println("\r\n\r\n witness generation: \r\n");
        System.out.flush();
        GenEnv genv = null;
        try {
            genv = new GenEnv(env);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(genv);

        String witness = genv.generate().toString();

        System.out.flush();
        System.out.println("==witness== \n"+witness);




    }
}
