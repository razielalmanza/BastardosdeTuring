package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class AbstractVisitor{
    /**
     * Accedera a la tabla de simbolos para conocer el valor
     * de la variable, en caso de que no haya sido asignada lanzara error
     * @param n nodo tipo identificador
     * @return el tipo de la variable
     */
    public int abVisitId(Nodo n){
        String name=n.getNombre();
        int tipo;
        if(TablaSimbolos.containsKey(name)){
            tipo = TablaSimbolos.lookUp(name);
        }else{
            System.err.print("Error identificador no asignado");
            tipo = 0;
        }
        return tipo;
    }
    /**
     * Metodo que llenara la tabla de simbolos
     * @param n Nodo de tipo Asignacion(EQ), como hijo izquierdo
     * siempre tiene un identificador
     */
    public void abstractVisitAsign(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type2 = abVisit(h.getLast());
        TablaSimbolos.insert(h.getFirst().getNombre(),h_type2);
    }
    public int abVisitAdd(Nodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        int add_type1 = abVisit(add.getFirst());
        int add_type2 = abVisit(add.getLast());
        int tipo = OperadoresTipo.getTypeAdd(add_type1,add_type2);
        System.out.print(tipo + "|");
        return tipo;
    }
    public int abVisitMul(Nodo n){
        LinkedList<Nodo> mul = n.hijos.hijos;
        int mul_type1 = abVisit(mul.getFirst());
        int mul_type2 = abVisit(mul.getLast());
        int tipo = OperadoresTipo.getTypeMul(mul_type1,mul_type2);
        System.out.print(tipo + "|");
        return tipo;
    }
    public int abVisitMod(Nodo n){
        LinkedList<Nodo> mod = n.hijos.hijos;
        int mod_type1 = abVisit(mod.getFirst());
        int mod_type2 = abVisit(mod.getLast());
        int tipo = OperadoresTipo.getTypeMod(mod_type1,mod_type2);
        System.out.print(tipo +"|");
        return tipo;
    }
    public int abVisitComp(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = abVisit(h.getFirst());
        int h_type2 = abVisit(h.getLast());
        int tipo = OperadoresTipo.getTypeComp(h_type1,h_type2);
        System.out.print(tipo +"|");
        return tipo;
    }
    public void abVisitPrint(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = abVisit(h.getFirst());
        if(h_type1!=4) System.err.println("error_semantico: PRINT");
    }

    public void abVisitWhile(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = abVisit(h.getFirst());
        if(h_type1!=3) System.err.println("error_semantico: WHILE");
    }
    public void abVisitIf(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = abVisit(h.getFirst());
        if(h_type1!=3) System.err.println("error_semantico: IF");
    }
    public void abVisitElse(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = abVisit(h.getFirst());
        if(h_type1!=3) System.err.println("error_semantico: IF..ELSE");
    }
    public void abVisitBlock(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        for(Nodo nodo:h) abVisit(nodo);
    }


    public int abVisit(Nodo n){
        int tipo;
        switch(n.getOperador()){
            case IDENTIFICADOR: tipo=abVisitId(n);break;
            case ENTERO:tipo = 1; break;
            case REAL: tipo = 2; break;
            case BOOLEANO: tipo = 3; break;
            case CADENA: tipo = 4; break;

            case MAS:tipo = abVisitAdd(n);break;
            case MENOS:tipo = abVisitAdd(n); break;
            
            case POR:tipo = abVisitMul(n);break;
            case DIV:tipo = abVisitMul(n);break;
            case DIVENTERA:tipo = abVisitMul(n);break;
            case MODULO:tipo = abVisitMul(n);break;

            case LE: tipo = abVisitComp(n);break;
            case GR: tipo = abVisitComp(n);break;
            case LEQ: tipo = abVisitComp(n);break;
            case GRQ: tipo = abVisitComp(n);break;
            case EQUALS: tipo = abVisitComp(n);break;
            case DIFF: tipo = abVisitComp(n);break;
            case AND: tipo = abVisitComp(n);break;
            case OR: tipo = abVisitComp(n);break;
            case NOT: tipo = abVisitComp(n);break;

            case EQ: tipo =0; abstractVisitAsign(n);break;
            
            case PRINT: abVisitPrint(n);tipo=0; break;
            case WHILE: abVisitWhile(n);tipo=0; break;

            case IF: abVisitIf(n);tipo=0; break;
            case ELSE: abVisitElse(n);tipo=0; break;

            case BLOQUE: abVisitBlock(n);tipo=0; break;
            case RAIZ: abVisitBlock(n);tipo=0; break;

            default: tipo=0; break;
        }
        return tipo;
    }
}