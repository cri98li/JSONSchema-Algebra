grammar Grammatica;

@header {package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Algebra.ANTLR4;
}

assertion_list : '{' assertion (',' assertion)* '}'		#list							
	;

assertion : 		type_assertion 						#TypeAssertion
				|	assertion_list 				         #newList
   				|	between_assertion         			#betweenAssertion
   				| 	xbetween_assertion					#xBetweenAssertion
   				|	bet_items_assertion					#betweenItems
   				|	length_assertion						#Length
   				|	between_properties_assertion		#BetweenProperties
   				|	multiple_of_assertion				#multipleOf
   				|	all_of_assertion						#allOf
   				|	any_of_assertion						#anyOf
   				|	one_of_assertion						#oneOf
   				|	not_assertion							#Not
   				|	required_assertion					#Required
   				|	unique_items_assertion				#UniqueItems
				|	pattern_assertion					   	#Pattern
				|	items_assertion					   	#Items
				|	contains_assertion				   	#Contains
				|	enum_assertion_assertion				#Enum
				|	if_then_else_assertion			   	#ifThenElse
				|	const_assertion			         	#Const
	;

numeric_value : INT | 'null';

type_assertion : 'Obj' | 'Null' | 'Str' | 'Num' | 'Int' | 'Arr';

between_assertion : 'bet<'numeric_value','numeric_value'>';

xbetween_assertion : 'xbet<'numeric_value','numeric_value'>';

length_assertion : 'length<'numeric_value','numeric_value'>';

bet_items_assertion : 'betitems<'numeric_value','numeric_value'>';

between_properties_assertion : 'pro<'numeric_value','numeric_value'>';

multiple_of_assertion : 'mof<'numeric_value'>';

all_of_assertion : '_AND(' assertion_list ')';

one_of_assertion : '_XOR(' assertion_list ')';

any_of_assertion : '_OR(' assertion_list ')';

not_assertion : '_NOT(' assertion_list ')';

required_assertion : 'req([' ID (',' ID)* '])';

unique_items_assertion : 'uniqueItems';

pattern_assertion : 'pattern(' ID ')';

items_assertion :   'items(;' assertion_list ')' 
                  | 'items(' assertion_list ('*' assertion_list)* ';' assertion_list ')';

contains_assertion : 'contains<' numeric_value ',' numeric_value '> ' assertion_list;

enum_assertion_assertion : 'enum(' ID (',' ID)* ')';

if_then_else_assertion : assertion_list '=>' assertion_list '|' assertion_list;

const_assertion : 'const(' JSON_VALUE ')';



JSON_VALUE : INT | ID;







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