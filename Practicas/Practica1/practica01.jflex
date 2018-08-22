// Práctica 1 del curso de compiladores.

%%

%class Practice01
%unicode
%standalone

ENTEROS = (([1-9][0-9]*)|0)
IDENTIFICADOR = ([a-z]|_)([a-z]|[0-9]|_)*
COMENTARIO = #.*

%%
{COMENTARIO} { System.out.println("COMENTARIO"); }
{ENTEROS} { System.out.println("ENTERO"); }
{IDENTIFICADOR} { System.out.println("IDENTIFICADOR"); }