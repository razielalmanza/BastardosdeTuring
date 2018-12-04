package ast.patron.compuesto;
import ast.patron.visitante.*;

public class DiffNodo extends Compuesto {
    
    public DiffNodo(Nodo diff, String value){
	    this.agregaHijoPrincipio(diff);
        valor = new Variable("-");
        name = "-";
        operador = Operador.MENOS;
    }

    public DiffNodo(){name="-";}

    public void accept(Visitor v){
     	v.visit(this);
    }
}
