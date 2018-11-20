package ast.patron.compuesto;
import ast.patron.visitante.*;
import ast.patron.tipos.*;
public class BooleanHoja extends Hoja{
	
	Operador operador =Operador.BOOLEANO;
    
    public BooleanHoja(boolean i){
	    valor = new Variable(i);
        tipo = Tipo.BOOLEAN;
        name = Boolean.toString(i);
    }
    public Operador getOperador(){return operador;}


    public void accept(Visitor v){v.visit(this);}
}