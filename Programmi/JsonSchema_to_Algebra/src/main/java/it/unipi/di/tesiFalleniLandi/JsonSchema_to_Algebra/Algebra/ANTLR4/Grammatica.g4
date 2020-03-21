grammar Grammatica;

@header {package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;
}
/* 
assertion_list : '{' assertion (',' assertion)* '}'					#list							
	;
*/

assertion : 		type_assertion								#NewTypeAssertion 						
				|	assertion ',' assertion 					#NewList
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
   				|	multiple_of_assertion						#NewMultipleOf
   				|	enum_assertion_assertion					#NewEnum
   				|	unique_items_assertion						#NewUniqueItems
				|	pattern_assertion							#NewPattern
				|	contains_assertion				   			#NewContains
				|	const_assertion			         			#NewConst
				|	items_assertion					   			#NewItems
	;

	
type_assertion : 'type(' TYPE ')'																#ParseTypeAssertion;	

between_assertion : 'bet<' numeric_value ',' numeric_value '>'									#ParseBetweenAssertion;		

xbetween_assertion : 'xbet<' numeric_value ',' numeric_value '>'								#ParseXBetweenAssertion;

length_assertion : 'length<'numeric_value','numeric_value'>'									#ParseLengthAssertion;

bet_items_assertion : 'betitems<'numeric_value','numeric_value'>'								#ParseBetItemsAssertion;

between_properties_assertion : 'pro<'numeric_value','numeric_value'>'							#ParseBetProAssertion;

multiple_of_assertion : 'mof<'numeric_value'>'													#ParseMultipleOf;													

not_assertion : '_NOT(' assertion ')'															#ParseNot;

all_of_assertion : '_AND(' assertion ')'														#ParseAllOf;	

one_of_assertion : '_XOR(' assertion ')'														#ParseOneOf;

any_of_assertion : '_OR(' assertion ')'															#ParseAnyOf;

required_assertion : 'req([' STRING (',' STRING)* '])'											#ParseRequired;

enum_assertion_assertion : 'enum(' numeric_value (',' numeric_value)* ')'						#ParseEnum;											

if_then_else_assertion : '(' assertion '=>' assertion '|' assertion ')'							#ParseIfThenElse
						|	'(' assertion '=>' assertion ')'									#ParseIfThen
						;
						
unique_items_assertion : 'uniqueItems'															#ParseUniqueItems;

pattern_assertion : 'pattern(' STRING ')'														#ParsePattern;

items_assertion :   'items(;' assertion ')' 													#ParseItems
                  | 'items(' assertion ('*' assertion)* ';' assertion ')'						#ParseItemsArray
                  ;

contains_assertion : 'contains<' numeric_value ',' numeric_value '>' assertion					#ParseContains;

const_assertion : 'const(' numeric_value ')'													#ParseConst;

numeric_value :  		NULL																	#NullValue
				|		INT 																	#NumericValue
				|		STRING																	#StringValue
				;

TYPE : 'Obj' | 'Null' | 'Str' | 'Num' | 'Int' | 'Arr' | 'Bool';
NULL : 'null';
INT : [0-9]+ ; // Define token INT as one or more digits
WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out
STRING : '"' (ESC | ~["\\])* '"';
//ID : [a-zA-Z0-9_]+;

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