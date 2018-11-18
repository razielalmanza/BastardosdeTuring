package ast.patron.compuesto;
import ast.patron.visitante.*;
import ast.patron.tipos.*;

public class BooleanHoja extends Hoja{
    public BooleanHoja(boolean i){
	    valor = new Variable(i);
        tipo = Tipo.BOOLEAN;
        name = Boolean.toString(i);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}