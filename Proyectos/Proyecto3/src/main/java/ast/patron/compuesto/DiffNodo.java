package ast.patron.compuesto;
import ast.patron.visitante.*;

public class DiffNodo extends Compuesto {
    
    public DiffNodo(Nodo add, String value){
	    valor = new Variable(add);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
