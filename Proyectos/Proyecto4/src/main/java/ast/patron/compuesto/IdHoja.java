package ast.patron.compuesto;
import ast.patron.visitante.*;
import ast.patron.tipos.*;

public class IdHoja extends Hoja{
	Operador operador =Operador.IDENTIFICADOR;
    public IdHoja(String i){
	    valor = new Variable(i);
        tipo = Tipo.NODEFINIDO;
        name = i;
    }

    public Operador getOperador(){return operador;}

    public void accept(Visitor v){
     	v.visit(this);
    }
}
