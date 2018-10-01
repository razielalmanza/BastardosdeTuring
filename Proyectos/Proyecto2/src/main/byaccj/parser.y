// parser.y
%{

import java.io.*;

%}

%token<sval> SALTO IDENTA DEIDENTA IDENTIFICADOR ENTERO CADENA REAL BOOLEANO
%type<sval> S start

%%
start:   {System.out.println("[OK] " + $$);}
     | S {System.out.println("[OK] " + $$);}

S: A S B {$$ = $3+ $2 +$1;}
 | A B   {$$ = $2 + $1 ;}
 ;
%%

private Letras alexico;

// Regresar átomos
private int yylex() {
  int yyl_return = -1;

  try{
    yyl_return = alexico.yylex();

  }catch (IOException e){
    System.err.println("Error de IO." + e);
  }
  return yyl_return;
}

public void yyerror (String error){
  System.err.println("[ERROR] " +error);
  System.exit(2);
}

public Parser(Reader r){
  alexico = new Letras(r,this);
}

public static void main(String args[]){
  try{
   Parser yyparser = new Parser(new FileReader(args[0]));
   yyparser.yyparse();
  }catch(FileNotFoundException e){
    System.err.println("El Archivo " + args[0] + " no existe.");
  }

}
