/********************************************************************************                                                     **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
package lexico;

%%

%public
%class Alexico
%unicode
%standalone

DIGITO = [0-9]

%%
#.* { System.out.println("COMENTARIO"); }
{DIGITO} { System.out.println("DIGITO"); }