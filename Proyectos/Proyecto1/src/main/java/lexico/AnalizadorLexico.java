package lexico;

import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class AnalizadorLexico {

    Alexico lexer;
    
    public AnalizadorLexico(String archivo) {
        try {
            Reader lector = new FileReader(archivo);
            lexer = new Alexico(lector);
        } catch(FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage() + "No se encontró el archivo.");
        }
    }

    public void analiza() {
        try {
            lexer.yylex();
        } catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
