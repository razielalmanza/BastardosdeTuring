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
IDENTIFICADOR = ([a-zA-Z]|_)([a-zA-Z]|[0-9]|_)*
BOOLEANO = True|False
ENTERO = (([1-9][0-9]*)|0)
REAL = '.'[0-9]+|{ENTERO}'.'[0-9]|{ENTERO}'.'
/* Omitir los opradores " y \ (Una opcion es usar contextos) */
CADENA = "\"".*"\""
PALABRA_RESERVADA = and|or|not|while|if|else|elif|print
OPERADOR = "+"|"-"|"\\"|"*"|"%"|"<"|">"|">="|"<="|"="|"!"

%%
/* reemplaza la aparicion de la cadena en el orden en el macro fue declarado*/
#.* { System.out.println("COMENTARIO"); }
{IDENTIFICADOR}     { System.out.println("IDENTIFICADOR"); }
{BOOLEANO}          { System.out.println("BOOLEANO"); }
{ENTERO}            { System.out.println("ENTERO"); }
{REAL}              { System.out.println("REAL"); }
{CADENA}            { System.out.println("CADENA"); }
{PALABRA_RESERVADA} { System.out.println("PALABRA_RESERVADA"); }
{OPERADOR}          { System.out.println("OPERADOR"); }