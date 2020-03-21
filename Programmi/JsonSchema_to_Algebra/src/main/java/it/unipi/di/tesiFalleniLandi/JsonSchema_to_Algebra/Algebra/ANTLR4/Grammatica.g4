grammar Grammatica;

@header {package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;
}

assertion_list : '{' assertion (',' assertion)* '}'		#list							
	;

assertion : 		type_assertion								#NewTypeAssertion 						
				|	assertion_list 				         		#NewList
   				|	between_assertion	         				#NewBetweenAssertion			
   				|	not_assertion								#NewNot
   				| 	xbetween_assertion							#NewXBetweenAssertion
   				|	bet_items_assertion							#NweBetweenItems
   				|	length_assertion							#NewLength
   				|	between_properties_assertion				#NewBetweenProperties
   				|	all_of_assertion							#NewAllOf
   				|	any_of_assertion							#NewAnyOf
   				|	one_of_assertion							#NewOneOf
   				|	required_assertion							#NewRequired
   				|	if_then_else_assertion			   			#NewIfThenElse
   				/* 
   				|	bet_items_assertion					#betweenItems
   				|	length_assertion						#Length
   				|	between_properties_assertion		#BetweenProperties
   				|	multiple_of_assertion				#multipleOf
   				|	any_of_assertion						#anyOf
   				|	one_of_assertion						#oneOf
   				
   				|	unique_items_assertion				#UniqueItems
				|	pattern_assertion					   	#Pattern
				|	items_assertion					   	#Items
				|	contains_assertion				   	#Contains
				|	enum_assertion_assertion				#Enum
				
				|	const_assertion			         	#Const
				
				*/
	;

	
type_assertion : ('Obj' | 'Null' | 'Str' | 'Num' | 'Int' | 'Arr' | 'Bool')						#ParseTypeAssertion;	

between_assertion : 'bet<' numeric_value ',' numeric_value '>'									#ParseBetweenAssertion;		

xbetween_assertion : 'xbet<' numeric_value ',' numeric_value '>'								#ParseXBetweenAssertion;

length_assertion : 'length<'numeric_value','numeric_value'>'									#ParseLengthAssertion;

bet_items_assertion : 'betitems<'numeric_value','numeric_value'>'								#ParseBetItemsAssertion;

between_properties_assertion : 'pro<'numeric_value','numeric_value'>'							#ParseBetProAssertion;

not_assertion : '_NOT(' assertion_list ')'														#ParseNot;

all_of_assertion : '_AND(' assertion_list ')'													#ParseAllOf;	

one_of_assertion : '_XOR(' assertion_list ')'													#ParseOneOf;

any_of_assertion : '_OR(' assertion_list ')'													#ParseAnyOf;

required_assertion : 'req([' ID (',' ID)* '])'													#ParseRequired;

if_then_else_assertion : '(' assertion_list '=>' assertion_list '|' assertion_list ')'			#ParseIfThenElse
						|	'(' assertion_list '=>' assertion_list ')'							#ParseIfThen
						;

numeric_value :  	NULL		#NullValue
				|		INT 	#NumericValue;

/* 

multiple_of_assertion : 'mof<'numeric_value'>';

unique_items_assertion : 'uniqueItems';

pattern_assertion : 'pattern(' ID ')';

items_assertion :   'items(;' assertion_list ')' 
                  | 'items(' assertion_list ('*' assertion_list)* ';' assertion_list ')';

contains_assertion : 'contains<' numeric_value ',' numeric_value '> ' assertion_list;

enum_assertion_assertion : 'enum(' ID (',' ID)* ')';

const_assertion : 'const(' JSON_VALUE ')';

*/

//JSON_VALUE : INT | ID;

NULL : 'null';
INT : [0-9]+ ; // Define token INT as one or more digits
ID : [a-zA-Z0-9_]+;
WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out






STRING
   : '"' (ESC | SAFECODEPOINT)* '"'
   ;


fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;


fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;

fragment HEX
   : [0-9a-fA-F]
   ;
fragment SAFECODEPOINT
   : ~ ["\\\u0000-\u001F]
   ;