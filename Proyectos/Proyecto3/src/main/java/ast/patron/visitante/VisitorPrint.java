package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.LinkedList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;


public class VisitorPrint implements Visitor
{

    public void visit(IntHoja n){
    System.out.print("[Hoja Entera] valor: " + n.getValor().ival);
    }

    public void visit(FloatHoja n){
		System.out.print("[Hoja Flotante] valor: " + n.getValor().ival);
    }
    public void visit(BooleanHoja n){
		System.out.print("[Hoja Booleana] valor: " + n.getValor().ival);
    }
    public void visit(StringHoja n){
		System.out.print("[Hoja Cadena] valor: " + n.getValor().ival);
    }
   
    public void visit(Nodo n){
        /*System.out.print("{\"");
        System.out.print("" + n.getNombre());
        Hijos h2 = n.hijos;
        if(h2!=null){
            LinkedList<Nodo> lista=h2.hijos;
            if(lista!=null){
                System.out.print(":");
                System.out.print("{");
                for(Nodo h:lista){
                    visit(h);
                    System.out.print(",");
                }
                System.out.print("}");
            }
        }*/
        printJson(n);
    }

    private void printJson(Nodo n) {
        JSONObject raiz = new JSONObject();
        raiz.put(n.getClass().getName(), n.getNombre());
        Hijos h2 = n.hijos;
        if(h2 != null && h2.hijos != null) {
            for(Nodo hijo : h2.hijos) {
                raiz.put(hijo.getNombre(), printJsonRecursivo(hijo));
            }
        }
        System.out.println(raiz);
    }

    private JSONObject printJsonRecursivo(Nodo n) {
        JSONObject json = new JSONObject();
        json.put(n.getClass().getName(), n.getNombre());
        Hijos h2 = n.hijos;
        if(h2 != null) {
            for(Nodo hijo : h2.hijos) {
                json.put(hijo.getNombre(), (hijo));
            }
        } 
        return json;
    }

}
