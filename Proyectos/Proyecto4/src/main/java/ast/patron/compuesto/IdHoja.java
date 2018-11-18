package ast.patron.compuesto;
import ast.patron.visitante.*;
import ast.patron.tipos.*;

public class IdHoja extends Hoja{
    public IdHoja(String i){
	    valor = new Variable(i);
        tipo = Tipo.NODEFINIDO;
        name = i;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
