package ast.patron.compuesto;
import ast.patron.visitante.*;
import ast.patron.tipos.*;

public class StringHoja extends Hoja{
    public StringHoja(String i){
	    valor = new Variable(i);
        tipo = Tipo.STRING;
        name = i;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}