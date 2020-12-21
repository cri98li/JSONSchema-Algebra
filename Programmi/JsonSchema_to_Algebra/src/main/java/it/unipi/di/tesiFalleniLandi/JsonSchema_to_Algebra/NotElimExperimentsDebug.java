package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Commons.Utils;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Utils_FullAlgebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.GenAlgebra.Endpoint;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.Utils_JSONSchema;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.WitnessAlgebra.WitnessAssertion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterns.REException;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.*;

public class NotElimExperimentsDebug
{

	private static Logger logger = LogManager.getLogger(NotElimExperimentsDebug.class);


	private static long nbLines(String str){
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}


	public static void main(String[] args ) throws IOException{
		String path = "/Users/mab/Documents/WORK/GIT/LIP6/JSONSchema-Algebra/Programmi/JsonSchema_to_Algebra/extract";
		File dir = new File(path);
		File [] files = dir.listFiles();

		String filename="";

		Gson gson = new Gson();
		JsonObject schemaObject = null,object=null;
		Assertion fullAlgebra, fullAlgebraNegated = null;

		JSONSchema root = null;

		for (File file : files) {
			filename=file.getName();
			logger.info(file);

			try (Reader reader = new FileReader(file.getAbsolutePath())) {
				try {
					object = gson.fromJson(reader, JsonObject.class);
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}


			try{
				//extract line and  schema_file
				Iterator<?> it = object.keySet().iterator();
				while(it.hasNext()) {
					String key = (String) it.next();
					switch (key) {
						case "line":
							logger.info("line " + object.get(key).getAsLong());
							break;
						case "id":
							logger.info("id " + object.get(key).getAsLong());
							break;
						case "schema_file":
							schemaObject = object.getAsJsonObject(key);
							break;
					}
				}

				root = new JSONSchema(schemaObject);
			}catch (Exception e) {
				e.printStackTrace();
			}


			String alg = file.getAbsolutePath()+".alg",
					notelim = file.getAbsolutePath()+".algNot";
			FileWriter fw1 = new FileWriter(alg),
						fw2 = new FileWriter(notelim);


			fullAlgebra = Utils_JSONSchema.normalize(root).toGrammar();
			String fstr = Utils.beauty(fullAlgebra.toGrammarString());
			fw1.write(fstr);
			logger.info("algebraic form size {} is written to disk",nbLines(fstr));
			fw1.close();
			fullAlgebraNegated = Utils_FullAlgebra.parseString(fstr).notElimination();
			String nfstr = Utils.beauty(fullAlgebraNegated.toGrammarString());
			fw2.write(nfstr);
			fw2.close();
			logger.info("algebraic form after not elimination size {} written to disk",nbLines(nfstr));

		}




	}


}