package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NodoOperador extends Compuesto {
    Operador operador;
    public NodoOperador(Nodo aux, String value){
        this.agregaHijoPrincipio(aux);
        name = value;
    }
    public NodoOperador(Nodo aux, String value,Operador op){
        this.agregaHijoPrincipio(aux);
        name = value;
        operador = op;
    }
    public NodoOperador(String value){name = value;}
   
    public NodoOperador(String value,Operador op){
        name = value;
        operador = op;
    }
   
    public Operador getOperador(){return operador;}
    public void accept(Visitor v){v.visit(this);}
}