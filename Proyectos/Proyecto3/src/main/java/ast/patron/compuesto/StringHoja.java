package ast.patron.compuesto;
import ast.patron.visitante.*;

public class StringHoja extends Hoja
{
    public StringHoja(String i){
	    valor = new Variable(i);
	    tipo = 4;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}