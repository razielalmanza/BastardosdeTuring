/** Componente. La clase genéria Nodo.
 * @author Diana Montes
 */
package ast.patron.compuesto;
import ast.patron.visitante.*;

public class Nodo {
    public Hijos hijos; //lista de hijos
    Variable valor; 
    /**
     * TIPOS:
     * NoDefinido: 0
     * Integer:    1
     * Double:     2
     * Boolean:    3
     * String:     4
     */
    int tipo;
    String name;

    /*(1)**********GETTERS**************/
    public Hijos getHijos(){return hijos;}
    public Nodo getHijo(){return null;}

    public void setHijo(Nodo c){}

    public Nodo getUltimoHijo(){return null;}
    public Nodo getPrimerHijo(){return null;}
    public Variable getValor(){return valor;}

    public int getType(){return tipo;}

    public String getNombre(){return name;}
    /***********************************/

    public void agregaHijoFinal(Nodo r){}
    public void agregaHijoPrincipio(Nodo r){}

    /*(3)**********SETTERS**************/
    public void setValor(Variable nuevo){valor = nuevo;}
    public void setTipo(int nuevo){tipo = nuevo;}
    /***********************************/

    public void accept(Visitor v){v.visit(this);}
    public void semantic_accept(AbstractVisitor a){a.visit(this);}

}
