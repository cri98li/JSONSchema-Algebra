package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

//Per avere un output leggibile: https://www.prettifier.net/perl/

public class GrammarStringDefinitions {
	//JSON SCHEMA
	public static final String JSONSCHEMA = "{\r\n%s\r\n}";
	
	//LOGIC OP
	public static final String OR = "\r\n|\t";
	public static final String COMMA = ",\r\n";
	
	//RANGE
	public static final String BETWEENNUMBER = "bet(%s,%s)";
	public static final String BETWEENNUMBER_EXCL = "xbet(%s,%s)";
	public static final String BETWEENITEMS = "betitems(%s,%s)";
	public static final String BETWEENPROPERTIES = "pro(%s,%s)";
	
	//MULTIPLE OF
	public static final String MULTIPLEOF = "mof(%s)";
	
	//TYPE
	public static final String TYPE = "type(%s)";
		public static final String TYPE_BOOLEAN = "bool";
		public static final String TYPE_STRING = "str";
		public static final String TYPE_NULL = "null";
		public static final String TYPE_NUMBER = "num";
		public static final String TYPE_INTEGER = "int";
		public static final String TYPE_ARRAY = "arr";
		public static final String TYPE_OBJECT = "obj";
		
	
	//allOf, anyOf, oneOf
	public static final String ALLOF = "allOf[\r\n%s\r\n]";
	public static final String ANYOF = "anyOf[\r\n%s\r\n]";
	public static final String ONEOF = "oneOf[\r\n%s\r\n]";
	
	//not
	public static final String NOT = "not: %s";
		
	//required
	public static final String REQUIRED = "req(%s)";
		
	//pattern
	public static final String PATTERN = "pattern(%s)";
		
	//length
	public static final String LENGTH = "length(%s,%s)";
	
	//items
	public static final String ITEMS = "items(%s;%s)";
	
	//contains
	public static final String CONTAINS = "contains(%s,%s)(%s)";
		
	//enum
	public static final String ENUM = "enum[%s]";
	
	//const
	public static final String CONST = "const(%s)";
	
	//unique items
	public static final String UNIQUEITEMS = "uniqueItems";
	
	public static final String DEFS = "def %s = %s";
	
	//if-then-else
	public static final String IF_THEN_ELSE = "if: %s, \r\nthen: %s, \r\nelse: %s";
	/*public static final String THEN = "then %s";
	public static final String ELSE = "else %s";*/
	
	//properties
	public static final String PROPERTIES = "properties[\r\n%s\r\n]";
	public static final String SINGLEPROPERTIES = "%s::%s";
	public static final String ADDITIONALPROPERTIES = "addp(%s)::%s";
	
	//ref
	public static final String REF = "ref(%s)";
	
	//null value
	public static final String NULLVALUE = "null";
}
