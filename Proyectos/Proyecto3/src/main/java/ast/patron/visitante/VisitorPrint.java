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
		System.out.print("[Hoja Flotante] valor: " + n.getValor().ival);
    }
    public void visit(BooleanHoja n){
		System.out.print("[Hoja Booleana] valor: " + n.getValor().ival);
    }
    public void visit(StringHoja n){
		System.out.print("[Hoja Cadena] valor: " + n.getValor().ival);
    }
   
    public void visit(Nodo n){


    }

}
