/********************************************************************************                                                     **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;
import java.util.Stack;

%%
%public
%class Alexico
%unicode
%standalone

%{
    
    /* Para guardar la secuencia de tokens. */
    private StringBuilder builder = new StringBuilder();
    /* Pila que guarda el numero de identaciones por bloque*/
    private Stack<Integer> pila_global = new Stack<>();

    /**
    * Añade una nueva representanción de un token al {@link StringBuilder}.
    * @param type La cadena con el tipo de token.
    */
    private void nextSymbol(final String type) {
        builder.append(type);
    }

    /**
    * Añade una nueva representación de un token al {@link StringBuilder}.
    * @param type La cadena con el tipo de token.
    * @param value La cadena con el valor del token.
    */
    private void nextSymbol(final String type, final String value) {
        final String tokenWithValue = String.format("%s(%s)", type, value);
        builder.append(tokenWithValue);
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
     * Devuelve el contador de identacion en el bloque actual
     * @return la cadena del numero de espacios en el bloque de identaci&oacute;n actual
     */
    private String getSpace(){
        return Integer.toString(pila_global.peek());
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
                nextSymbol("IDENTA",Integer.toString(bloque_actual));
        }else{
            int bloque_anterior = pila_global.peek();
            if(bloque_actual > bloque_anterior){ //creo que posteriormente sera un mayor
                pila_global.push(bloque_actual);
                nextSymbol("IDENTA",Integer.toString(bloque_actual));
            }else{
                do{
                    nextSymbol("DEIDENTA",Integer.toString(bloque_anterior));
                    pila_global.pop();
                    if(!pila_global.empty())
                        bloque_anterior = pila_global.peek();
                    else bloque_anterior = 0;
                }
                while(bloque_actual < bloque_anterior);
            }
        }
    }
%}

%eof{
    /* Imprimimos al final la secuencia de tokens. */
    System.out.println(builder.toString());
%eof}

/* ---- Expresiones regulares. ----*/
IDENTIFICADORES_RAW = [\w_][\w\d_]*
IDENTIFICADOR = {IDENTIFICADORES_RAW}
BOOLEANO = True|False
ENTERO = (([1-9][0-9]*)|0+)
REAL = \.[0-9]+|{ENTERO}\.\d|{ENTERO}\.
CADENA = \"(\\.|[^\\\"])*\"
PALABRA_RESERVADA = and|or|not|while|if|else|elif|print
OPERADOR = \+|-|\*|\%|<|>|>=|<=|=|\!|\+=
SEPARADOR = :
LINE_TERMINATOR = \r|\n|\r\n

%state IDENTA

%%
/*---- Macros y acciones. ----*/
<YYINITIAL>{
    #.* { System.out.println("COMENTARIO"); }
    {BOOLEANO}          { nextSymbol("BOOLEAN", yytext()); }
    {ENTERO}            { nextSymbol("ENTERO", yytext()); }
    {REAL}              { nextSymbol("REAL", yytext()); }
    {CADENA}            { nextSymbol("CADENA", yytext()); }
    {PALABRA_RESERVADA} { nextSymbol("RESERVADA", yytext()); }
    {OPERADOR}          { nextSymbol("OPERADOR", yytext()); }
    {IDENTIFICADOR}     { nextSymbol("IDENTIFICADOR", yytext()); }
    {SEPARADOR}         { nextSymbol("SEPARADOR", yytext()); }
    /* Abre nuevo contexto de identacion para esto se creara una pila qu guarde el
       numero de identaciones en el nuevo bloque que estamos creando.*/ 
    {LINE_TERMINATOR}   { nextSymbol("SALTO\n"); newIdenta(); yybegin(IDENTA); }
}

<IDENTA>{
    \s                  { pushIdenta(); }
    \S                  { 
    isIdenta();
    yypushback(1); 
    yybegin(YYINITIAL);
    }
}