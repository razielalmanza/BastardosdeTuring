package ast.patron.compuesto;
import ast.patron.visitante.*;

public class FloatHoja extends Hoja
{
    public FloatHoja(double i){
        
	    valor = new Variable(i);
        //tipo = 2;
        name = Double.toString(i);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }


}