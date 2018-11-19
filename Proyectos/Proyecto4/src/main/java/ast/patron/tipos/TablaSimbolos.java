package ast.patron.tipos;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;
import java.util.Hashtable;

public class TablaSimbolos{
    Hashtable<String,Variable> tabla_simbolos = 
    new Hashtable<String,Variable>();
    public Variable lookUp(String name){
        return tabla_simbolos.get(name);
    }
    public void insert(String nombre,Variable tipo){
        tabla_simbolos.put(nombre,tipo);
    }
}