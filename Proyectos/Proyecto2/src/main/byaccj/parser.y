// parser.y
%{

import java.io.*;

%}

%token<sval> SALTO IDENTA DEIDENTA IDENTIFICADOR ENTERO CADENA REAL BOOLEANO
             AND OR NOT WHILE IF ELSE PRINT ADD SUB MULT DIV LT BT LTE BTE EQUAL
             NOT INC SEPARADOR  ASIG DIST DIVE POWER PAR_O PAR_C MOD
%type<sval> file_input stmt simple_stmt compound_stmt
            small_stmt expr_stmt print_stmt test if_stmt
            while_stmt suite stmt or_test and_test not_test
            comparison expr factor comp_op term atom power
            stmt_aux not_test_2 or_2 and_2


%%
start:   {System.out.println("O");}
     | file_input {System.out.println("[OK] " );}

/*file_input : file_input SALTO | file_input stmt  ; */
file_input : SALTO | stmt | file_input
 stmt :  simple_stmt  |  compound_stmt ;
 simple_stmt : small_stmt SALTO ;
 small_stmt :  expr_stmt  |  print_stmt ;
 expr_stmt :  test | EQUAL test ;
 print_stmt : PRINT  test ;

 compound_stmt :  if_stmt  |  while_stmt ;
 if_stmt : IF  test  SEPARADOR   suite | IF  test  SEPARADOR   suite ELSE SEPARADOR   suite ;
 while_stmt : WHILE  test  SEPARADOR   suite ;
 suite :  simple_stmt  | SALTO IDENTA  stmt_aux DEIDENTA ;
 stmt_aux: stmt_aux stmt | stmt

 test :  or_test ;
 or_test :  and_test | and_test or_2 ;
 or_2: or_2 OR and_test |  ;
 and_test : not_test | not_test  and_2 ;
 and_2: and_2 AND not_test | ;

 not_test : not_test_2 comparison ;
 not_test_2: not_test_2 NOT | ;
 comparison :  expr comparison_2 ;
 comparison_2: comparison_2 comp_op expr | ;
 comp_op : LT|BT|EQUAL|BTE|LTE|DIST ;
 expr : expr  ADD term| expr SUB term| term ;

 term :  term MULT factor|term DIV factor|term MOD factor|term DIVE factor | factor ;
 factor : ADD  factor | SUB factor |  power  ;
 power :  atom | atom POWER  factor  ;
 atom : IDENTIFICADOR | ENTERO | CADENA 
    | REAL | BOOLEANO | PAR_O  test  PAR_C ;
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
   yyparser.yydebug = true;
   yyparser.yyparse();
  }catch(FileNotFoundException e){
    System.err.println("El Archivo " + args[0] + " no existe.");
  }

}
