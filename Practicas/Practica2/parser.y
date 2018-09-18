
%{
	import java.io.*;
%}
%token NUMBER, ADD, SUB, MULT, DIV
%type<sval> EXPR, TERM, FACTOR


%%
start:	{System.out.println("OK");}
	| EXPR {System.out.println("OK");}



EXPR: TERM | EXPR ADD TERM 
		   | EXPR SUB TERM 
TERM: FACTOR | TERM MULT FACTOR
			 | TERM DIV FACTOR

FACTOR: NUMBER
	   | SUB NUMBER


 


%%

private Letras alexico;

// Regresar L
private int yylex(){
	int yyl_return = -1;

	try{
		yyl_return = alexico.yylex();

	}catch(IOException e){
		System.out.println("Error de IO." + e);
	}
	return yyl_return;
}

public void yyerror(String error){
	System.err.println("Error: " +  error);
	System.exit(2);
}

public Parser(Reader r){
	alexico = new Letras(r);

	
}

public static void main(String[] args){
	try{
	Parser yyparser = new Parser(new FileReader(args[0]));
	yyparser.yyparse();
	} catch(FileNotFoundException e){
	System.out.println("File: " + args[0] + "No encontrado" );
	}
}