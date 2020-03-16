package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

//Per avere un output leggibile: https://www.prettifier.net/perl/

public class GrammarStringDefinitions {
	//JSON SCHEMA
	public static final String JSONSCHEMA = "{%s}";
	
	//LOGIC OP
	public static final String OR = " _OR ";
	public static final String AND = ", \r\n";
	
	//RANGE
	public static final String BETWEENNUMBER = "bet<%s,%s>";
	public static final String BETWEENNUMBER_EXCL = "xbet<%s,%s>";
	public static final String BETWEENITEMS = "betitems<%s,%s>";
	public static final String BETWEENPROPERTIES = "pro<%s,%s>";
	
	//MULTIPLE OF
	public static final String MULTIPLEOF = "mof<%s>";
	
	//TYPE
	public static final String TYPE = "%s";
		public static final String TYPE_BOOLEAN = "Bool";
		public static final String TYPE_STRING = "Str";
		public static final String TYPE_NULL = "Null";
		public static final String TYPE_NUMBER = "Num";
		public static final String TYPE_INTEGER = "Int";
		public static final String TYPE_ARRAY = "Arr";
		public static final String TYPE_OBJECT = "Obj";
		
	
	//allOf, anyOf, oneOf
	public static final String ALLOF = "_AND(%s)";
	public static final String ANYOF = "_OR(%s)";
	public static final String ONEOF = "_XOR(%s)";
	
	//not
	public static final String NOT = "_NOT(%s)";
		
	//required
	public static final String REQUIRED = "req(%s)";
		
	//pattern
	public static final String PATTERN = "pattern(%s)";
		
	//length
	public static final String LENGTH = "length<%s,%s>";
	
	//items
	public static final String ITEMS = "items(%s;%s)";
	
	//contains
	public static final String CONTAINS = "contains<%s,%s>(%s)";
		
	//enum & const
	public static final String ENUM = "enum(%s)";
	
	//enum & const
	public static final String UNIQUEITEMS = "uniqueItems";
	
	//if-then-else
	public static final String IF_THEN_ELSE = "if %s => %s | %s";
	/*public static final String THEN = "then %s";
	public static final String ELSE = "else %s";*/
}
