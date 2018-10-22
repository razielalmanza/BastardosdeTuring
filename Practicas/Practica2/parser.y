
%{
	import java.io.*;
%}
%token NUMBER, ADD, SUB, MULT, DIV
%type<sval> EXPR, TERM, FACTOR, EXPR2, TERM2, FACTOR2


%%
start:	{System.out.println("OK");}
	| EXPR2 {System.out.println("OK");}

EXPR: TERM | EXPR ADD TERM 
		   | EXPR SUB TERM 
TERM: FACTOR | TERM MULT FACTOR
			 | TERM DIV FACTOR
FACTOR: NUMBER
	   | SUB NUMBER

EXPR2: TERM2 | TERM2 ADD EXPR2
			 | TERM2 SUB EXPR2
TERM2: FACTOR2 | FACTOR2 MULT TERM2
			   | FACTOR2 DIV TERM2
FACTOR2: NUMBER | SUB NUMBER

%%

private Letras alexico;

// Regresar L
private int yylex(){
	int yyl_return = -1;
	try {
		yyl_return = alexico.yylex();
	} catch(IOException e){
		System.out.println("Error de IO." + e);
	}
	return yyl_return;
}

public void yyerror(String error){
	System.err.println("Error: " +  error);
	System.exit(2);
}

/* Constructor normal. */
public Parser(Reader r){
	alexico = new Letras(r);
}

/* Constructor para depuración. */
public Parser(Reader r, boolean debug) {
	alexico = new Letras(r);
	yydebug = debug;	
}

public static void main(String[] args){
	try{
		Parser yyparser;
		if(args.length == 2) {
			yyparser = new Parser(new FileReader(args[0]), Boolean.parseBoolean(args[1]));
		} else {
			yyparser = new Parser(new FileReader(args[0])); 
		}
		yyparser.yydebug = true;
		yyparser.yyparse();
	} catch(FileNotFoundException e){
		System.out.println("File: " + args[0] + "No encontrado" );
	}
}