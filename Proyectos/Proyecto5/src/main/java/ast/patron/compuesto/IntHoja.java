package ast.patron.compuesto;
import ast.patron.visitante.*;
import ast.patron.tipos.*;

public class IntHoja extends Hoja
{
    public IntHoja(int i){
        tipo = Tipo.INTEGER;
	    valor = new Variable(i);
        name = ""+i;
        operador = Operador.ENTERO;
    }
    public Operador getOperador(){return operador;}

    public void accept(Visitor v){
     	v.visit(this);
    }

}
