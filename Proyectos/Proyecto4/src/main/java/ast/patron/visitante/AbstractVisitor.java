package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class AbstractVisitor{

    public int abVisit(IntHoja n){return 1;}
    public int abVisit(FloatHoja n){return 2;}
    public int abVisit(BooleanHoja n){return 3;}
    public int abVisit(StringHoja n){return 4;}
    public int abVisit(AddNodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        int add_type1 = abVisit(add.getFirst());
        int add_type2 = abVisit(add.getLast());
        int tipo = OperadoresTipo.getTypeAdd(add_type1,add_type2);
        System.out.println(tipo);
        return tipo;
    }
    public int abVisit(Nodo n){
        switch(n.getOperador()){
            case ENTERO:
            System.out.println("entro"); 
            n=(IntHoja)n; break;
            case MAS: n=(AddNodo)n; break;
        }
        return abVisit(n);
    }
}