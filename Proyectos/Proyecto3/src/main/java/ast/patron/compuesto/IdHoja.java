package ast.patron.compuesto;
import ast.patron.visitante.*;

public class IdHoja extends Hoja
{
    public IdHoja(String i){
	    valor = new Variable(i);
        tipo = 1;
        name = ""+i;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
