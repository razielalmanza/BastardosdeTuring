package ast.patron.tipos;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;
import java.util.Hashtable;

public class TablaSimbolos{
    Hashtable<String,String> tabla_simbolos = 
    new Hashtable<String,String>();

    public void agregaVariable(String nombre,String tipo){
        tabla_simbolos.put(nombre,tipo);
    }

    public String lookUp(String name){
        return tabla_simbolos.get(name);
    }
}