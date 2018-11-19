package ast.patron.tipos;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;
import java.util.Hashtable;
public class TablaSimbolos{
    static Hashtable<String,Integer> tabla_simbolos = new Hashtable<String,Integer>();
    public static Integer lookUp(String name){return tabla_simbolos.get(name);}
    public static void insert(String nombre,Integer tipo){
        tabla_simbolos.put(nombre,tipo);
    }
}