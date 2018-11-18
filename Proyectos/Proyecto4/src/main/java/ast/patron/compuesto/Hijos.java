package ast.patron.compuesto;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.AbstractCollection;
import java.lang.ArrayStoreException;

/* Extends Abstract Collection. */
public class Hijos<N extends Nodo> extends AbstractCollection {

    public LinkedList<N> hijos;
    /*(1)***********CONSTRUCTORES*************/
    public Hijos(N l){
 	hijos = new LinkedList<N>();
	hijos.addFirst(l);
    }
    public Hijos() {hijos = new LinkedList<>();}
    /***************************************/
    public Iterator iterator(){return hijos.iterator();}
    /*(2)**********GETTERS**************/
    public LinkedList<N> getAll(){return hijos;}
    public int size() {return hijos.size();}
    public Nodo getPrimerHijo() {return hijos.getFirst();}
    public Nodo getUltimoHijo() {return hijos.getLast();}
    /*********************************/

    public void agregaHijoPrincipio(N l) {hijos.addFirst(l);}
    public void agregaHijoFinal(N r) {hijos.add(r);}
}
