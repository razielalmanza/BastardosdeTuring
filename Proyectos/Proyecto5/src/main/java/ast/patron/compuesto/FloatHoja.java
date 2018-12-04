package ast.patron.compuesto;
import ast.patron.visitante.*;

public class FloatHoja extends Hoja
{
	Operador operador =Operador.REAL;
    public FloatHoja(double i){
        
	    valor = new Variable(i);
        //tipo = 2;
        name = Double.toString(i);
    }
    public Operador getOperador(){return operador;}

    public void accept(Visitor v){
     	v.visit(this);
    }


}