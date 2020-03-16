package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.MainClass_Algebra;
import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.JSONSchema.MainClass_JSONSchema;

/**
 * Hello world!
 *
 */
public class MainClass 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {
    	System.out.println("avvia \r\n\t[1] MainClass_JSONSchema \r\n\t[2]MainClass_Algebra");
    	try (Scanner s = new Scanner(System.in)) {
			switch(s.nextInt()) {
			case 1:
				MainClass_JSONSchema.main(args);
				break;
			case 2:
				MainClass_Algebra.main(args);
				break;
			}
		}
    }
}
