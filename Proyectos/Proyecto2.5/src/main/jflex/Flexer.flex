/********************************************************************************
**  @author Diana Montes                                               	       **
**  @about Proyecto 3: Construcción del AST.	                               **
*********************************************************************************/
package ast;
import ast.patron.compuesto.*;
import java.util.Stack;
import java.util.Arrays;

import java.util.Stack;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.IOException;
%%
%public
%class Flexer
%byaccj
%line
%unicode
//%standalone
/********************************************************************************                                                     **
**  @about Proyecto 1: Analizador léxico para p, subconjunto de Python.        **
*********************************************************************************/
%{
    
    /* Para guardar la secuencia de tokens. */
    private StringBuilder builder = new StringBuilder();
    /* Pila que guarda el numero de identaciones por bloque*/
    private Stack<Integer> pila_global = new Stack<>();
    /* Contador del número de línea actual.*/
    private int no_linea = 1;
    /* Verifica si existe un error de identacion. */
    private boolean error_identa = false;
    /* El nombre del archivo. */
    private String fileName;
     private Parser yyparser;

    public Flexer(final String archivo, final Reader reader) {
        this(reader);
        String[] directorios = archivo.split("/");  
        fileName = directorios[directorios.length-1];
        if(fileName.contains(".")) {
            fileName = fileName.split("\\.")[0];
        }
    }
      public Flexer(java.io.Reader r, Parser parser){
    	   this(r);
    	   yyparser = parser;
    }


    /**
    * Añade una nueva representanción de un token al {@link StringBuilder}.
    * @param type La cadena con el tipo de token.
    */
    private void nextSymbol(final String type) {
        builder.append(type);
    }

    /**
    * Añade una nueva representación de un token al {@link StringBuilder}.
    * @param type La cadena con el tipo de token.
    * @param value La cadena con el valor del token.
    */
    private void nextSymbol(final String type, final String value) {
        final String tokenWithValue = String.format("%s(%s)", type, value);
        builder.append(tokenWithValue);
    }

    /**
     * Crea un nuevo bloque de identaci&oacute; (representado como un nuevo)
     * elemento en la pila, con el entero de valor 0 (porque llevamos 0 
     * identaciones en el momento que se crea).
     */
    private void newIdenta(){
        pila_global.push(0);
    }

    /**
     * Incrementa el contador de espacios del bloque actual
     */
    private void pushIdenta(){
        int count = pila_global.pop();
        pila_global.push(++count);
    }

    /**
     * Verifica si la siguiente linea pertenece al mismo bloque de identaci&oacute;n
     * esto con el fin de reconocer si es un atomo IDENTA o no, esto se vera reflejado
     * en la pila con un nuevo elmento en el caso de que si fuera una nueva identaci&oacute;n
     * @return el entero que identificara si el atomo es identa o deidenta
     */
    private int isIdenta(){
        int valor = 2;
        int bloque_actual = pila_global.pop();
        if(pila_global.empty()){
            pila_global.push(bloque_actual);
        }else{
            int bloque_anterior = pila_global.peek();
            if(bloque_actual > bloque_anterior){ 
                pila_global.push(bloque_actual);
                nextSymbol("IDENTA",Integer.toString(bloque_actual));
                valor = 0;
        }else if(bloque_actual < bloque_anterior){
            do{
                nextSymbol("DEIDENTA",Integer.toString(bloque_anterior));
                valor = 1;
                pila_global.pop();
                if(!pila_global.empty())
                    bloque_anterior = pila_global.peek();
                    //aqui tambien asumimos que el primer bloque inicia en 0
                else bloque_anterior = 0; 
            } while(bloque_actual < bloque_anterior);
                if(bloque_actual != bloque_anterior) error_identa = true;
            }
        }return valor;
    }

    /**
     * Reporta el error ocurrido.
     * @param type El tipo de error, 0: cadena, 1: Identación 2: Lexema
     */
    private void reportError(int type){
        switch(type){
            case 0:
                nextSymbol("\nERROR de cadena en la linea: ");
                break;
            case 1:
                nextSymbol("\nERROR de Identación en la linea: ");
                break;
            case 2:
                nextSymbol("\nERROR Lexema no encontado en la linea: ");
                break;
        }

        nextSymbol("" + no_linea);
    }
    /**
     * @return si ocurrio un error de identacion
     */
    private boolean errorIdenta(){
        return error_identa;
    }
%}

%eof{
    /* Escribimos el resutlado en out y se imprimen. */
    final BufferedWriter writer;
    final FileWriter fileWriter;
    final String fileString = "out/" + fileName + ".plx";
    try {
        final String content = builder.toString();
        final File file = new File(fileString);
        file.getParentFile().mkdirs();
        fileWriter = new FileWriter(file);
        writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close();
        fileWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error al escribir el archivo.");
    }
    System.out.println(builder.toString());
%eof}

/* ---- Expresiones regulares. ----*/
IDENTIFICADORES_RAW = [\w_][\w\d_]*
IDENTIFICADOR = {IDENTIFICADORES_RAW}
BOOLEANO = True|False
ENTERO = (([1-9][0-9]*)|0+)
REAL = \.[0-9]+|{ENTERO}\.\d|{ENTERO}\.
CADENA = \"(\\.|[^\\\"])*\"
CADENA_MAL = \"(\\.|[^\\\"])*
//PALABRA_RESERVADA = and|or|not|while|if|else|elif|print
//OPERADOR = \+|-|\*|\%|<|>|>=|<=|=|\!|\+=
SEPARADOR = :
LINE_TERMINATOR = \r|\n|\r\n

%state IDENTA
%state ATOMOS
%state ERROR

%%
/*---- Macros y acciones. ----*/
<YYINITIAL>{
    .                   {System.out.print("INITIAL");nextSymbol("\n"); newIdenta(); yypushback(1); yybegin(IDENTA);}
}

<ATOMOS>{
    #.*                 { System.out.println("COMENTARIO"); }
    {BOOLEANO}          { nextSymbol("BOOLEAN", yytext());  return Parser.BOOLEANO; }
    {ENTERO}            { nextSymbol("ENTERO", yytext());   return Parser.ENTERO;}
    {REAL}              { nextSymbol("REAL", yytext());     return Parser.REAL;}
    {CADENA}            { nextSymbol("CADENA", yytext());   return Parser.CADENA;}
    // {PALABRA_RESERVADA} { nextSymbol("RESERVADA", yytext());  return Parser.PALABRA_RESERVADA;}
    // atomos de palabras reservadas
    and                 {nextSymbol("AND", yytext());       return Parser.AND;}
    or                  {nextSymbol("OR", yytext());        return Parser.OR;}
    not                 {nextSymbol("NOT", yytext());       return Parser.NOT;}
    while               {nextSymbol("WHILE", yytext());     return Parser.WHILE;}
    "if"                  {nextSymbol("IF", yytext()); System.out.println("IFFF");    return Parser.IF;}
    else                {nextSymbol("ELSE", yytext());      return Parser.ELSE;}
    //elif               {nextSymbol("ELIF", yytext());     return Parser.;}
    print               {nextSymbol("PRINT", yytext());      return Parser.PRINT;}
    //{OPERADOR}          { nextSymbol("OPERADOR", yytext());       return Parser.OPERADOR;}
    // atomos de operadores
    \+                  {nextSymbol("ADD", yytext());       return Parser.ADD;}
    -                   {nextSymbol("SUB", yytext());       return Parser.SUB;}
    \*                  {nextSymbol("MULT", yytext());      return Parser.MULT;}
    \*\*                {nextSymbol("POWER", yytext());     return Parser.POWER;}
    \%                  {nextSymbol("MOD", yytext());       return Parser.MOD;}
    \/                  {nextSymbol("DIV", yytext());       return Parser.DIV;}
    \/\/                {nextSymbol("DIVE", yytext());      return Parser.DIVE;}
    \<                  {nextSymbol("LT", yytext());        return Parser.LT;}
    >                   {nextSymbol("BT", yytext());        return Parser.BT;}
    \>=                 {nextSymbol("LTE", yytext());       return Parser.LTE;}
    \<=                 {nextSymbol("BTE", yytext());       return Parser.BTE;}
    =                   {nextSymbol("ASIG", yytext());      return Parser.ASIG;}
    ==                  {nextSymbol("EQUAL", yytext());     return Parser.EQUAL;}
    \!                  {nextSymbol("NOT", yytext());       return Parser.NOT;}
    \+=                 {nextSymbol("INC", yytext());       return Parser.INC;}
    \!=                 {nextSymbol("DIST", yytext());      return Parser.DIST;}
    \(                  {nextSymbol("PAR_O", yytext());     return Parser.PAR_O;}
    \)                  {nextSymbol("PAR_C", yytext());     return Parser.PAR_C;}

    {IDENTIFICADOR}     { nextSymbol("IDENTIFICADOR", yytext()); System.out.println("IDDD");   return Parser.IDENTIFICADOR;}
    {SEPARADOR}         { nextSymbol("SEPARADOR", yytext());        return Parser.SEPARADOR;}
    {CADENA_MAL}        { reportError(0); }
    /* Abre nuevo contexto de identacion para esto se creara una pila qu guarde el
       numero de identaciones en el nuevo bloque que estamos creando.*/ 
    {LINE_TERMINATOR}   { nextSymbol("SALTO\n"); no_linea++; newIdenta(); yybegin(IDENTA); return Parser.SALTO;}
    \s                  {/* Ignore */}
    [^]                 {reportError(2); nextSymbol(", generado por la cadena: "+yytext()+" "); yybegin(ERROR); }
}

<IDENTA>{
    \s                  { System.out.print("333");pushIdenta(); }
    \S                  { System.out.print("222");
        switch(isIdenta()){
            case 0: 
                return Parser.IDENTA;
            case 1:
                return Parser.DEIDENTA;

        }
        if(errorIdenta()){
            reportError(1);
            yybegin(ERROR);
        }else{
            yypushback(1); 
            yybegin(ATOMOS);
        }
    }
}

<ERROR>{
    [^]  { /* Detiene la ejecucion */ }
}
