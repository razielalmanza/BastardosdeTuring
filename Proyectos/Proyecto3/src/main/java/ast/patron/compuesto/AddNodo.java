package ast.patron.compuesto;
import ast.patron.visitante.*;

public class AddNodo extends Compuesto {
    
    public AddNodo(Nodo add, String value){
	    valor = new Variable(add);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
