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
    
    private StringBuilder builder = new StringBuilder();

    private void nextSymbol(final String type) {
        builder.append(type);
    }

    private void nextSymbol(final String type, final String value) {
        final String tokenWithValue = String.format("%s(%s)", type, value);
        builder.append(tokenWithValue);
    }
%}

%eof{
    System.out.println(builder.toString());
%eof}

/* El identificador no debe ser la cadena vacia */
IDENTIFICADORES_RAW = [\w_][\w\d_]*
IDENTIFICADOR = {IDENTIFICADORES_RAW}
BOOLEANO = True|False
ENTERO = (([1-9][0-9]*)|0+)
REAL = \.[0-9]+|{ENTERO}\.\d|{ENTERO}\.
CADENA = \"(\\.|[^\\\"])*\"
PALABRA_RESERVADA = and|or|not|while|if|else|elif|print
OPERADOR = \+|-|\*|\%|<|>|>=|<=|=|\!
SEPARADOR = :

%%
/* reemplaza la aparicion de la cadena en el orden en el macro fue declarado*/
#.* { System.out.println("COMENTARIO"); }
{BOOLEANO}          { nextSymbol("BOOLEAN", yytext()); }
{ENTERO}            { nextSymbol("ENTERO", yytext()); }
{REAL}              { nextSymbol("REAL", yytext()); }
{CADENA}            { nextSymbol("CADENA", yytext()); }
{PALABRA_RESERVADA} { nextSymbol("RESERVADA", yytext()); }
{OPERADOR}          { nextSymbol("OPERADOR", yytext()); }
{IDENTIFICADOR}     { nextSymbol("IDENTIFICADOR", yytext()); }
{SEPARADOR}         { nextSymbol("SEPARADOR", yytext()); }
