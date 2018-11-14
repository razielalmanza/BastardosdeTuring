package ast.patron.compuesto;
import ast.patron.visitante.*;

public class BooleanHoja extends Hoja
{
    public BooleanHoja(boolean i){
	    valor = new Variable(i);
        tipo = 3;
        name = Boolean.toString(i);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}