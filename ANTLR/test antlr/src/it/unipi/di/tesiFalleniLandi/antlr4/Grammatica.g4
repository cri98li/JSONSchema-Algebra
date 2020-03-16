grammar Grammatica;

assertion_list : '{' s (',' s)* '}'		#list							
	;

s : 	value 			#TypeAssertion
	|	assertion_list 	#newList
   | between         #betweenAssertion
	;

value : INT
      | ID;	

between : 'bet<'value','value'>';

INT : [0-9]+ ; // Define token INT as one or more digits
ID : [a-zA-Z]+ ;
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