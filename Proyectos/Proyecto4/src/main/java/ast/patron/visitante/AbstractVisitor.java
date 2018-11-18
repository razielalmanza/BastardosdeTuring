package ast.patron.visitante;
import ast.patron.compuesto.*;

public class AbstractVisitor implements Visitor{

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

    }
}