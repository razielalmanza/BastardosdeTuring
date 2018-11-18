package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.LinkedList;
import java.util.Iterator;

public class VisitorPrint implements Visitor
{

    public void visit(IntHoja n){
    System.out.print("[Hoja Entera] valor: " + n.getValor().ival);
    }
    public void visit(FloatHoja n){
		System.out.print("[Hoja Flotante] valor: " + n.getNombre());
    }
    public void visit(BooleanHoja n){
		System.out.print("[Hoja Booleana] valor: " + n.getValor().bval);
    }
    public void visit(StringHoja n){
		System.out.print("[Hoja Cadena] valor: " + n.getValor().sval);
    }
   
    public void visit(Nodo n){
        System.out.print("{\"");
        System.out.print("" + n.getNombre());
        System.out.print("\":");
        Hijos h2 = n.hijos;
        if(h2!=null){
            LinkedList<Nodo> lista=h2.hijos;
            if(lista!=null){
                for(Nodo h:lista){
                    visit(h);
                    System.out.print(",");
                }
            }
        }
        System.out.print("}");
    }

}
