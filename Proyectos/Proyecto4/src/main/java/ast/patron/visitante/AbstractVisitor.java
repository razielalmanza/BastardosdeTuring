package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class AbstractVisitor{

    public int abVisitInt(IntHoja n){
        System.out.println("entra?");
        return 1;}
    public int abVisit(FloatHoja n){return 2;}
    public int abVisit(BooleanHoja n){return 3;}
    public int abVisit(StringHoja n){return 4;}
    public int abVisitAdd(AddNodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        int add_type1 = abVisit(add.getFirst());
        int add_type2 = abVisit(add.getLast());
        int tipo = OperadoresTipo.getTypeAdd(add_type1,add_type2);
        System.out.println(tipo);
        return tipo;
    }
    public int abVisit(Nodo n){
        int tipo;
        switch(n.getOperador()){
            case ENTERO:
            //n=(IntHoja)n; 
            //tipo = abVisitInt(n);
            tipo = 1; 
            break;
            case MAS: 
            n=(AddNodo)n; 
            tipo = abVisit(n);
            break;
            default: tipo=0;
        }
        return tipo;
    }
}