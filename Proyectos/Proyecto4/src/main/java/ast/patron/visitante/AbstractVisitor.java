package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class AbstractVisitor{

    public int abVisitInt(Nodo n){return 1;}
    public int abVisitFloat(Nodo n){return 2;}
    public int abVisitBoolean(Nodo n){return 3;}
    public int abVisitString(Nodo n){return 4;}
    public int abVisitAdd(Nodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        int add_type1 = abVisit(add.getFirst());
        int add_type2 = abVisit(add.getLast());
        int tipo = OperadoresTipo.getTypeAdd(add_type1,add_type2);
        System.out.println(tipo);
        return tipo;
    }
    public int abVisitMul(Nodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        int add_type1 = abVisit(add.getFirst());
        int add_type2 = abVisit(add.getLast());
        int tipo = OperadoresTipo.getTypeMul(add_type1,add_type2);
        System.out.println(tipo);
        return tipo;
    }
    public int abVisit(Nodo n){
        int tipo;
        switch(n.getOperador()){
            case ENTERO:tipo = abVisitInt(n);break;
            case MAS:tipo = abVisitAdd(n);break;
            case MENOS:tipo = abVisitAdd(n); break;
            case POR:
            tipo = abVisitMul(n);break;
            default: tipo=0;
        }
        return tipo;
    }
}