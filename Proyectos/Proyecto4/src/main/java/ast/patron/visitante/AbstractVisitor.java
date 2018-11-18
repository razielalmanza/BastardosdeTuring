package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class AbstractVisitor{

    public void abVisit(IntHoja n){}
    public void abVisit(FloatHoja n){}
    public void abVisit(BooleanHoja n){}
    public void abVisit(StringHoja n){}
    public void abVisit(AddNodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        Tipo add_type1 = add.getFirst().getType();
        Tipo add_type2 = add.getLast().getType();
        int tipo = OperadoresTipo.getTypeAdd(add_type1,add_type2);
        System.out.println(tipo);
    }
    // public void visit(Nodo n){abVisit(n);}
}