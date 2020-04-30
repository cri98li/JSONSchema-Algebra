package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Common;

public class GrammarStringDefinitions {
	//JSON SCHEMA
	public static final String JSONSCHEMA = "{\r\n%s\r\n}";
	
	//LOGIC OP
	public static final String COMMA = ",\r\n";
	
	//RANGE
	public static final String BETWEENNUMBER = "bet(%s,%s)";
	public static final String BETWEENNUMBER_EXCL = "xbet(%s,%s)";
	public static final String BETWEENITEMS = "betItems(%s,%s)";
	public static final String BETWEENPROPERTIES = "pro(%s,%s)";

	// +/- inf
	public static final String POS_INF = "+inf";
	public static final String NEG_INF = "-inf";

	//MULTIPLE OF
	public static final String MULTIPLEOF = "mof(%s)";
	public static final String NOTMULTIPLEOF = "notMof(%s)";
	
	//TYPE
	public static final String TYPE = "type[%s]";
		public static final String TYPE_BOOLEAN = "bool";
		public static final String TYPE_STRING = "str";
		public static final String TYPE_NULL = "null";
		public static final String TYPE_NUMBER = "num";
		public static final String TYPE_INTEGER = "int";
		public static final String TYPE_ARRAY = "arr";
		public static final String TYPE_OBJECT = "obj";
		public static final String TYPE_NUMNOTINT = "numNotInt";

	
	//allOf, anyOf, oneOf
	public static final String ALLOF = "allOf[\r\n%s\r\n]";
	public static final String ANYOF = "anyOf[\r\n%s\r\n]";
	public static final String ONEOF = "oneOf[\r\n%s\r\n]";
	
	//not
	public static final String NOT = "not(%s)";
		
	//required
	public static final String REQUIRED = "req[%s]";
		
	//pattern
	public static final String PATTERN = "pattern(\"%s\")";
	public static final String NOTPATTERN = "notPattern(\"%s\")";
		
	//length
	public static final String LENGTH = "length(%s,%s)";
	
	//items
	public static final String ITEMS = "items[%s;%s]";
	
	//contains
	public static final String CONTAINS = "contains(%s, %s; %s)";
		
	//enum
	public static final String ENUM = "enum[%s]";
	
	//const
	public static final String CONST = "const(%s)";
	
	//unique items
	public static final String UNIQUEITEMS = "uniqueItems";
	
	//repeated items
	public static final String REPEATEDITEMS = "repeatedItems";

	//defs e rootdef
	public static final String DEFS = "def \"%s\" = %s";
	public static final String ROOTDEF = "rootdef \"%s\" = %s";
	public static final String ROOTDEF_DEFAULTNAME = "\"root\"";
	public static final String NOT_DEFS = "not_";

	//if-then-else
	public static final String IF_THEN_ELSE = "ifThenElse(%s; \r\n\t%s; \r\n\t%s)";
	public static final String IF_THEN = "ifThen(%s; \r\n\t%s)";
	
	//properties
	public static final String PROPERTIES = "props[\r\n%s\r\n;%s]";
	public static final String SINGLEPROPERTIES = "\"%s\": %s";
	
	//pattReq
	public static final String PATTERNREQUIRED = "pattReq[\r\n%s\r\n]";
	
	//addPattReq
	public static final String ADDPATTERNREQUIRED = "addPattReq([%s] : %s)";
	
	//PropertyNames
	public static final String PROPERTYNAMES = "names(%s)";
	
	//ref
	public static final String REF = "ref(\"%s\")";
	
	//null value
	public static final String NULLVALUE = "null";
		
	//unknow keyword (annotations?)
	public static final String UNKNOW = "annotations[%s]";
	public static final String SINGLEUNKNOW = "\"%s\": \"%s\"";
	
	//ExName
	public static final String EXNAME = "exNames(\r\n%s\r\n)";

	//IfBoolThen
	public static final String IFBOOLTHEN = "ifBoolThen(%s)";
}
