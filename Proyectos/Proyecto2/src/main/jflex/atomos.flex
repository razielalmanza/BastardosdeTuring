// tokens.flex

package asintactico;

%%
%class Letras
%public
%unicode
%byaccj

%{
    private Parser yyparser;

    /** Nuevo constructor
    * @param FileReader r
    * @param parser parser - parser
    */
    public Letras(java.io.Reader r, Parser parser){
    	   this(r);
    	   yyparser = parser;
    }
%}


%%
a    {yyparser.yylval = new ParserVal(yytext()); return Parser.A;}
b    {yyparser.yylval = new ParserVal(yytext()); return Parser.B;}
"\n" {}
[^] {System.out.println("ERROR: Token no reconocido.");
     System.exit(1);}