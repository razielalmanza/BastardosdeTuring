package ast.patron.compuesto;
import ast.patron.visitante.*;

public class FloatHoja extends Hoja
{
    public FloatHoja(int i){
	    valor = new Variable(i);
	    tipo = 2;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }