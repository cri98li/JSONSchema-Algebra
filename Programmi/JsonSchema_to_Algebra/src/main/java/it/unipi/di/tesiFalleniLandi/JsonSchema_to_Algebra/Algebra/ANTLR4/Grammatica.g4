grammar Grammatica;

@header {package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;
}

assertion_list : '{' assertion (',' assertion)* '}'																	#ParseAssertionList							
				|    BOOLEAN																						#ParseBooleanSchema;

assertion : 		type_assertion																					#NewTypeAssertion
   				|	between_assertion	         																	#NewBetweenAssertion
   				|	not_assertion																					#NewNot
   				| 	xbetween_assertion																				#NewXBetweenAssertion
   				|	bet_items_assertion																				#NewBetweenItems
   				|	length_assertion																				#NewLength
   				|	between_properties_assertion																	#NewBetweenProperties
   				|	all_of_assertion																				#NewAllOf
   				|	any_of_assertion																				#NewAnyOf
   				|	one_of_assertion																				#NewOneOf
   				|	required_assertion																				#NewRequired
   				|	if_then_else_assertion			   																#NewIfThenElse
   				|	multiple_of_assertion																			#NewMultipleOf
   				|	enum_assertion_assertion																		#NewEnum
   				|	unique_items_assertion																			#NewUniqueItems
				|	pattern_assertion																				#NewPattern
				|	contains_assertion				   																#NewContains
				|	const_assertion			         																#NewConst
				|	items_assertion					   																#NewItems
				|	assertion_list																					#NewAssertionList
				|   properties																						#NewProperties
				|	def_assertion																					#NewDef
				|	ref_assertion																					#NewRef
				|	propertyNames_assertion																			#NewPropertyNames
				|   propertyExNames_assertion                                                                       #NewPropertyExNames
				|	annotations																						#NewAnnotations
				|	not_multiple_of_assertion																		#NewNotMultipleOf
				|	not_pattern_assertion																			#NewNotPattern
				|	repeated_items_assertion																		#NewRepeatedItems
				|	additional_pattern_required																		#NewAdditionalPatternRequired
				|	pattern_required																				#NewPatternRequired
	;

	
type_assertion : 'type''(' (TYPE | NULL) ')'																		#ParseTypeAssertion;	

between_assertion : 'bet''(' json_value ',' json_value ')'															#ParseBetweenAssertion;		

xbetween_assertion : 'xbet''(' json_value ',' json_value ')'														#ParseXBetweenAssertion;

length_assertion : 'length''('json_value','json_value')'															#ParseLengthAssertion;

bet_items_assertion : 'betItems''('json_value','json_value')'														#ParseBetItemsAssertion;

between_properties_assertion : 'pro''('json_value','json_value')'													#ParseBetProAssertion;

multiple_of_assertion : 'mof''('json_value')'																		#ParseMultipleOf;		

not_multiple_of_assertion : 'notMof''('json_value')'																#ParseNotMultipleOf;	

not_assertion : 'not''(' assertion ')'																				#ParseNot;

all_of_assertion : 'allOf''[' assertion (',' assertion)* ']'														#ParseAllOf;	

one_of_assertion : 'oneOf''[' assertion (',' assertion)* ']'														#ParseOneOf;

any_of_assertion : 'anyOf''[' assertion (',' assertion)* ']'														#ParseAnyOf;

required_assertion : 'req''[' STRING (',' STRING)* ']'																#ParseRequired;

enum_assertion_assertion : 'enum[' json_value (',' json_value)* ']'													#ParseEnum;											

if_then_else_assertion : 'ifThenElse''(' assertion ';' assertion ';' assertion ')'									#ParseIfThenElse
						|	'ifThen''(' assertion ';' assertion')'													#ParseIfThen
						;
						
unique_items_assertion : 'uniqueItems'																				#ParseUniqueItems;

repeated_items_assertion : 'repeatedItems'																			#ParseRepeatedItems;

pattern_assertion : 'pattern''(' STRING ')'																			#ParsePattern;

not_pattern_assertion : 'notPattern''(' STRING ')'																	#ParseNotPattern;

items_assertion : 'items''(' assertion (',' assertion)*';'')'														#ParseOnlyItems
					| 'items''(' (assertion (',' assertion)*)?';'assertion')'										#ParseAdditionalItems
					;

contains_assertion : 'contains''(' json_value ',' json_value ';' assertion	')'										#ParseContains;

properties : 'props''[' (STRING ':' assertion (','STRING ':' assertion)*)? ';' assertion ']'						#ParseAdditionalProperties
				| 'props''[' (STRING ':' assertion (','STRING ':' assertion)*)? ';'']'								#ParseProperties;

const_assertion : 'const''(' json_value ')'																			#ParseConst;

def_assertion: 'def'STRING'=' assertion	(',' 'def'STRING'=' assertion)*												#ParseDef
					| 'rootdef''=' assertion (',' 'def'STRING'=' assertion)*										#ParseDefRoot;

ref_assertion: 'ref''(' STRING ')'																						#ParseRef;

propertyNames_assertion: 'names''(' assertion ')'																				#ParsePropertyNames;

propertyExNames_assertion: 'exNames''(' assertion ')'																				#ParsePropertyExNames;

annotations: 'annotations''['STRING':'STRING (','STRING':'STRING)*	']'												#ParseAnnotations; //non implementato in JSON_to_Grammatica

pattern_required: 'pattReq''[' STRING ':' assertion (',' STRING ':' assertion)* ']'									#ParsePatternRequired;

additional_pattern_required: 'addPattReq''(' '['(STRING (',' STRING))*']' ':' assertion ')'							#ParseAdditionalPatternRequired;


json_value :  			NULL																						#NullValue
				|		INT 																						#IntValue
				|		STRING																						#StringValue
				|		DOUBLE																						#DoubleValue
				|		'['(json_value(',' json_value)*)?']'														#ArrayValue
				|		BOOLEAN																						#BooleanValue
				|		'{' (STRING ':' json_value (',' STRING ':' json_value)*)? '}'								#JsonObjectValue
				;


NULL : 'null';
TYPE : 'obj' | 'str' | 'num' | 'int' | 'arr' | 'bool' | 'numNotInt';
INT : '-'?[0-9]+; // Define token INT as one or more digits
DOUBLE: '-'?[0-9]+'.'[E0-9]+;
WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out
STRING : '"' .*? '"';
BOOLEAN : 'true' | 'false';

fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;

fragment UNICODE
   : 'u' HEX HEX HEX HEX
   | 'x' HEX HEX
   ;

fragment HEX
   : [0-9a-fA-F]
   ;
fragment SAFECODEPOINT
   : ~ ["\\\u0000-\u001F]
   ;