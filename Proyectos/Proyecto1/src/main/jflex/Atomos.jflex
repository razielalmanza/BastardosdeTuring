/********************************************************************************                                                     **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;

%%
%public
%class Alexico
%unicode
%standalone

%{
    
    /* Para guardar la secuencia de tokens. */
    private StringBuilder builder = new StringBuilder();
    /* Contador de espacios */
    private int space = 0;

    /**
    * Añade una nueva represtanción de un token al {@link StringBuilder}.
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
     * Incrementa el contador de espacios del bloque actual
     */
    private void pushIdenta(){
        space++;
    }

    /**
     * Devuelve el contador de identacion en el bloque actual
     * @return la cadena del numero de espacios en el bloque de identaci&oacute;n actual
     */
    private String getSpace(){
        return Integer.toString(space);
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
OPERADOR = \+|-|\*|\%|<|>|>=|<=|=|\!
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
    {LINE_TERMINATOR}   { yybegin(IDENTA); }
}

<IDENTA>{
    \s                  { pushIdenta(); }
    \S                  { nextSymbol("SALTOIDENTA",getSpace()); yybegin(YYINITIAL);}
}