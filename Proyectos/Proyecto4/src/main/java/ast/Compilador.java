//import Parser;
package ast;
import java.io.*;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;


public class Compilador{

    Parser parser;
    Nodo raízAST;
    VisitorPrint v_print;
    AbstractVisitor v_abst;

    Compilador(Reader fuente){
        parser = new Parser(fuente);
        v_print = new VisitorPrint();
        v_abst = new AbstractVisitor();
    }

    public void ConstruyeAST(boolean debug){
        //parser.yydebug = debug;
        parser.yyparse(); // análisis léxico, sintáctio y constucción del AST
        raízAST = parser.raíz;
    }

    public void imprimeAST(){parser.raíz.accept(v_print);}
    public void analisisSemantico(){raízAST.semanticAccept(v_abst);}

    public static void main(String[] args){
            String archivo = "src/main/resources/test.p";
        try{
            Reader a = new FileReader(archivo);
            Compilador c  = new Compilador(a);
            c.ConstruyeAST(true);
            c.imprimeAST();
            c.analisisSemantico();
        }catch(FileNotFoundException e){
            System.err.println("El archivo " + archivo +" no fue encontrado. ");
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Uso: java Compilador [archivo.p]: ");
        }
    }
}
