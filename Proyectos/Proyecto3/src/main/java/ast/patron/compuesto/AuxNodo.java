package ast.patron.compuesto;
import ast.patron.visitante.*;

public class AuxNodo extends Compuesto {
    
    public AuxNodo(Nodo aux, String value){
        this.agregaHijoPrincipio(aux);
        //valor = new Variable("+");
        name = value;
    }

    public AuxNodo(String value){
        name = value;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}