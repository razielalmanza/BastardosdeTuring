package ast.patron.compuesto;
import ast.patron.visitante.*;

public class MultNodo extends Compuesto {
    
    public MultNodo(Nodo mult, String value){
        this.agregaHijoPrincipio(mult);
        //valor = new Variable("+");
        name = "*";
    }

    public MultNodo(){name="*";}

    public void accept(Visitor v){
     	v.visit(this);
    }

    // public void accept_semantico(){

    // }
}
