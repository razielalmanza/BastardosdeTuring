/********************************************************************************                                                     **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;

%%

%public
%class Alexico
%unicode
%standalone

/* El identificador no debe ser la cadena vacia */
IDENTIFICADOR = ([a-zA-Z]|'_')([a-zA-Z]|[0-9]|'_')*
BOOLEANO = 'True'|'False'
ENTERO = [1-9][0-9]*|0+
REAL = '.'[0-9]+|{ENTERO}'.'[0-9]|{ENTERO}'.'
CADENA = '\"'[.--('\\'|'\"')]*'\"'
PALABRA_RESERVADA = 'and'|'or'|'not'|'while'|'if'|'else'|'elif'|'print'
OPERADOR = '+'|'-'|''|'*'|'//'|'%'|'<'|'>'|'>='|'<='|'='|'!'|'='

%%
#.* { System.out.println("COMENTARIO"); }
{ENTERO} { System.out.println("ENTERO"); }