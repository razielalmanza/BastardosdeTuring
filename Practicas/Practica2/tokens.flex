// tokens.flex
// ANal lexico
%%
%class Letras
%public 
%unicode
%byaccj
 
%%
\+ 	{return Parser.ADD; }
\- 	{return Parser.SUB; }
\%	{return Parser.DIV; }
\*  {return Parser.MULT; }
[0-9]+ {return Parser.NUMBER;}
\s  {}
[^]	{System.out.println("ERROR: Token no definido.");
	System.exit(1);}
