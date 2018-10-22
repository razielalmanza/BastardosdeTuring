/** Componente. La clase genéria Nodo.
 * @author Diana Montes
 */
package ast.patron.compuesto;
import ast.patron.visitante.*;

public class Nodo
{
    Hijos hijos;
    Variable valor;
    int tipo;
    String name;

    public Hijos getHijos(){
	return hijos;
    }

    public Nodo getHijo(){
	return null;
    }

    public void setHijo(Nodo c){
    }

    public Nodo getUltimoHijo(){
	return null;
    }

    public Nodo getPrimerHijo(){
	return null;
    }

    public void agregaHijoFinal(Nodo r){

    }

    public void agregaHijoPrincipio(Nodo r){

    }

    public Variable getValor(){
	return valor;
    }

    public int getType(){
	return tipo;
    }

    public String getNombre(){
	return name;
    }

    public void setValor(Variable nuevo){
	valor = nuevo;
    }

    public void setTipo(int nuevo){
	tipo = nuevo;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }

}
