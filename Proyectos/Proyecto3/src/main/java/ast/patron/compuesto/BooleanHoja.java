package ast.patron.compuesto;
import ast.patron.visitante.*;

public class BooleanHoja extends Hoja
{
    public BooleanHoja(int i){
	    valor = new Variable(i);
	    tipo = 3;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }