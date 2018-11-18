package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NodoOperador extends Compuesto {
    Operador operador;
    public NodoOperador(Nodo aux, String value){
        this.agregaHijoPrincipio(aux);
        name = value;
    }
    public NodoOperador(String value){name = value;}
    public void accept(Visitor v){v.visit(this);}
}