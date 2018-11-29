/********************************************************************************
**  @author Diana Montes                                               	       **
**  @about Proyecto 3: Construcción del AST.	                               **
*********************************************************************************/
package ast;
import ast.patron.compuesto.*;
import java.util.Stack;
import java.util.Arrays;
%%
%public
%class Flexer
%byaccj
%line
%state INDENTA CODIGO DEINDENTA
%unicode
%{
    /* Pila que guarda el numero de identaciones por bloque*/
    private Stack<Integer> pila_global = new Stack<>();
    /* Verifica si existe un error de identacion. */
    private boolean error_identa = false;

    /** Variables auxiliares para
    * manejar la indentación.*/
    static Stack<Integer> pila = new Stack<Integer>();
    static Integer actual = 0;
    static String cadena = "";
    static int dedents = 0;
    static int indents = 0;

    private Parser yyparser;

    public int line(){
        return yyline+1;
    }

    /** Nuevo constructor
    * @param FileReader r
    * @param Parser parser
    */
    public Flexer(java.io.Reader r, Parser parser){
    	   this(r);
    	   this.yyparser = parser;
    }

    /** Función que maneja los niveles de indetación
    * @param int espacios - nivel de indetación actual.
    * @return void
    */
    public void indentacion(int espacios){
        if(pila.empty()){ //ponerle un cero a la pila si esta vacia
             pila.push(new Integer(0));
        }

        Integer tope = pila.peek();

        if(tope != espacios){
	    //Se debe emitir un DEINDENTA por cada nivel mayor al actual
            if(tope > espacios){
                while(pila.peek() > espacios &&  pila.peek()!=0 ){
                    pila.pop();
                    dedents += 1;
                }
                if(pila.peek() == espacios){
		    yybegin(DEINDENTA);
                }else{
		    System.out.println("Error de indentación. Línea "+(yyline+1));
		    System.exit(1);
		}
                return;
            }
   	    //El nivel actual de indentación es mayor a los anteriores.
            pila.push(espacios);
	    yybegin(CODIGO);
            indents = 1;
        }else yybegin(CODIGO);
    }
%}
ENTERO = (([1-9][0-9]*)|0+)
REAL = \.[0-9]+|{ENTERO}\.\d|{ENTERO}\.
SALTO                   =       "\n"
IDENTIFICADOR   = 	([:letter:] | "_" )([:letter:] | "_" | [0-9])*
CADENA = \"(\\.|[^\\\"])*\"
CADENA_MAL = \"(\\.|[^\\\"])*
SEPARADOR  		=       ("(" | ")" | ":"  | ";" )
COMENTARIO 		=     	"#".*
BOOLEAN		        = True | False
%%
{COMENTARIO}                              {}
<YYINITIAL>{
  (" " | "\t" )+[^" ""\t""#""\n"]         { System.out.println("Error de indentación. Línea "+(yyline+1));
					    System.exit(1);}
  {SALTO}                                 {}
  [^" ""\t"]                              { yypushback(1); yybegin(CODIGO);}
}
<DEINDENTA>{
  .                                       { yypushback(1);
  					    if(dedents > 0){
						dedents--;
						return Parser.DEINDENTA;
  					    }
					    yybegin(CODIGO);}
}
<CODIGO>{
  {CADENA}            { return Parser.CADENA; }
  "+"					  { return Parser.MAS;}
  "-"					  { return Parser.MENOS;}
  "*"					  { return Parser.POR;}
  "**"					  { return Parser.POTENCIA;}
  "/"					  { return Parser.DIV;}
  "//"					  { return Parser.DIVENTERA;}
  "%"					  { return Parser.MODULO;}
  "<"				          { return Parser.LE;}
  ">"				          { return Parser.GR;}
  "<="                                    { return Parser.LEQ;}
  ">="                                    { return Parser.GRQ;}
  "=="                                    { return Parser.EQUALS;}
  "!="                                    { return Parser.DIFF;}
  "="                                     { return Parser.EQ;}
  "("                                     { return Parser.PA;}
  ")"                                     { return Parser.PC;}
  ":"                                     { return Parser.DOBLEPUNTO;}
  "and"                                   { return Parser.AND;}
  "not"                                   { return Parser.NOT;}
  "while"                                 { return Parser.WHILE;}
  "for"                                   { return Parser.FOR;}
  "elif"                                  { return Parser.ELIF;}
  "or"                                    { return Parser.OR;}
  "else"                                  { return Parser.ELSE;}
  "if"                                    { return Parser.IF;}
  "print"				  { return Parser.PRINT;}
  {CADENA_MAL}        { /*reportError(0);*/ }
  {SALTO}				  { yybegin(INDENTA); actual=0; return Parser.SALTO;}
  {REAL}				  { yyparser.yylval=new FloatHoja(Double.parseDouble(yytext())); return Parser.REAL;}
  {ENTERO}				{ yyparser.yylval=new IntHoja(Integer.parseInt(yytext())); return Parser.ENTERO; }
  {BOOLEAN}       { yyparser.yylval=new BooleanHoja(Boolean.parseBoolean(yytext())); return Parser.BOOLEANO;}
  {IDENTIFICADOR}	  { yyparser.yylval=new IdHoja(yytext());return Parser.IDENTIFICADOR; }
  " "					  { }
}
<INDENTA>{
  {SALTO}                                 { actual = 0;}
  " "				          { actual++;}
  \t					  { actual += 4;}
  .					  { yypushback(1);
					    this.indentacion(actual);
					    if(indents == 1){
					      indents = 0;
					      return Parser.INDENTA;
					    }
					  }
}
<<EOF>>                                   { this.indentacion(0);
					    if(dedents > 0){
					      dedents--;
					      return Parser.DEINDENTA;
					    }else{
                                              return 0;
				            }
					  }
[^]					  { System.out.println("Error de sintáxis: caractér inválido: " + yytext() + "\nLínea "+(yyline+1));
					    System.exit(1); }
