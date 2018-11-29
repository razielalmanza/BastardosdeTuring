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
%state INDENTA CADENA CODIGO DEINDENTA
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


    /**
     * Crea un nuevo bloque de identaci&oacute; (representado como un nuevo)
     * elemento en la pila, con el entero de valor 0 (porque llevamos 0 
     * identaciones en el momento que se crea).
     */
    private void newIdenta(){
        pila_global.push(0);
    }
    /**
     * Incrementa el contador de espacios del bloque actual
     */
    private void pushIdenta(){
        int count = pila_global.pop();
        pila_global.push(++count);
    }
    /**
     * Verifica si la siguiente linea pertenece al mismo bloque de identaci&oacute;n
     * esto con el fin de reconocer si es un atomo IDENTA o no, esto se vera reflejado
     * en la pila con un nuevo elmento en el caso de que si fuera una nueva identaci&oacute;n
     */
    private void isIdenta(){
        int bloque_actual = pila_global.pop();
        if(pila_global.empty()){
            pila_global.push(bloque_actual);
        }else{
            int bloque_anterior = pila_global.peek();
            if(bloque_actual > bloque_anterior){ 
                pila_global.push(bloque_actual);
                //nextSymbol("IDENTA",Integer.toString(bloque_actual));
        }else if(bloque_actual < bloque_anterior){
            do{
                //nextSymbol("DEIDENTA",Integer.toString(bloque_anterior));
                pila_global.pop();
                if(!pila_global.empty())
                    bloque_anterior = pila_global.peek();
                    //aqui tambien asumimos que el primer bloque inicia en 0
                else bloque_anterior = 0; 
            } while(bloque_actual < bloque_anterior);
                if(bloque_actual != bloque_anterior) error_identa = true;
            }
        }
    }

%}
PUNTO			=	\.
DIGIT           	=       [0-9]
CERO             	=        0+
ENTERO			= 	{CERO} | {DIGIT}+
REAL			= 	{ENTERO}? {PUNTO} {ENTERO}?
SALTO                   =       "\n"
IDENTIFIER       	= 	([:letter:] | "_" )([:letter:] | "_" | [0-9])*
CHAR_LITERAL   	        = 	([:letter:] | [:digit:] | "_" | "$" | " " | "#" |
                                {OPERADOR} | {SEPARADOR}) | "\\"
OPERADOR  		=       ("+" | "-" | "*" | "**" | "/" | "//" | "%" |
			         "<" | ">" | "<=" | "+=" | "-=" | ">=" | "==" | "!=" | "<>" | "=" )
SEPARADOR  		=       ("(" | ")" | ":"  | ";" )
COMENTARIO 		=     	"#".*
BOOLEAN		        =	("True" | "False")
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
<CADENA>{
  {CHAR_LITERAL}+   { yyparser.yylval=new StringHoja(yytext());cadena = yytext();}
  \"					  { yybegin(CODIGO);
                                            cadena = "";
					    return Parser.CADENA;}
  {SALTO}				  { System.out.println("Unexpected newline. Line "+(yyline+1));
					     System.exit(1);}
}
<CODIGO>{
  \"            { yybegin(CADENA); }
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
  {SALTO}				  { yybegin(INDENTA); actual=0; return Parser.SALTO;}
  {REAL}				  { yyparser.yylval=new FloatHoja(Double.parseDouble(yytext())); return Parser.REAL;}
  {ENTERO}				{ yyparser.yylval=new IntHoja(Integer.parseInt(yytext())); return Parser.ENTERO; }
  {BOOLEAN}       { yyparser.yylval=new BooleanHoja(Boolean.parseBoolean(yytext())); return Parser.BOOLEANO;}
  {IDENTIFIER}	  { yyparser.yylval=new IdHoja(yytext());return Parser.IDENTIFICADOR; }
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
