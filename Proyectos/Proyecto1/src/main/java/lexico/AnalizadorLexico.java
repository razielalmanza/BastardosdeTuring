package lexico;

import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase para Analizador Léxico.
 */
public class AnalizadorLexico {

    /* La clase generada por JFlex a través de nuestro archivo Atomos.jflex */
    private Alexico lexer;
    /* Constructor. */
    public AnalizadorLexico(String archivo) {
        try {
            Reader lector = new FileReader(archivo);
            lexer = new Alexico(archivo, lector);
        } catch(FileNotFoundException fnfe) {
            final String message = fnfe.getMessage();
            System.err.println(message + "No se encontró el archivo.");
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
