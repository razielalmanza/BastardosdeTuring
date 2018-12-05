package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class VisitanteGenerador {
    Registro reg = new Registro();
  
    public void visit(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
  
        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
  
        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0]);
        //hi.accept(this);
  
        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1]);
        //hd.accept(this);

        System.out.println(".text\n lw " + siguientes[0] + " " + hi.getValor());

        String opcode =  "sub";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }

     /**
     * Accedera a la tabla de simbolos para conocer el valor
     * de la variable, en caso de que no haya sido asignada lanzara error
     * @param n nodo tipo identificador
     * @return el tipo de la variable
     */
    public int geVisitId(Nodo n){
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
    public void generateVisitAsign(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type2 = geVisit(h.getLast());
        TablaSimbolos.insert(h.getFirst().getNombre(),h_type2);
    }
    public int geVisitAdd(Nodo n){
        LinkedList<Nodo> add = n.hijos.hijos;
        int add_type1 = geVisit(add.getFirst());
        int add_type2 = geVisit(add.getLast());
        int tipo = OperadoresTipo.getTypeAdd(add_type1,add_type2);
        //System.out.print(tipo + "|");
        return tipo;
    }
    public int geVisitMul(Nodo n){
        LinkedList<Nodo> mul = n.hijos.hijos;
        int mul_type1 = geVisit(mul.getFirst());
        int mul_type2 = geVisit(mul.getLast());
        int tipo = OperadoresTipo.getTypeMul(mul_type1,mul_type2);
        //System.out.print(tipo + "|");
        return tipo;
    }
    public int geVisitMod(Nodo n){
        LinkedList<Nodo> mod = n.hijos.hijos;
        int mod_type1 = geVisit(mod.getFirst());
        int mod_type2 = geVisit(mod.getLast());
        int tipo = OperadoresTipo.getTypeMod(mod_type1,mod_type2);
        //System.out.print(tipo +"|");
        return tipo;
    }
    public int geVisitComp(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = geVisit(h.getFirst());
        int h_type2 = geVisit(h.getLast());
        int tipo = OperadoresTipo.getTypeComp(h_type1,h_type2);
        //System.out.print(tipo +"|");
        return tipo;
    }    
    public void geVisitPrint(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = geVisit(h.getFirst());
        if(h_type1!=4) System.err.println("error_semantico: PRINT");
    }

    public void geVisitWhile(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = geVisit(h.getFirst());
        geVisit(h.getLast());
        if(h_type1!=3) System.err.println("error_semantico: WHILE");
    }
    public void geVisitIf(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = geVisit(h.getFirst());
        geVisit(h.getLast());
        if(h_type1!=3) System.err.println("error_semantico: IF");
    }
    public void geVisitElse(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        int h_type1 = geVisit(h.getFirst());
        geVisit(h.get(1));
        geVisit(h.getLast());
        if(h_type1!=3) System.err.println("error_semantico: IF..ELSE");
    }
    public void geVisitBlock(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        for(Nodo nodo:h) geVisit(nodo);
    }



    public int geVisit(Nodo n){
        int tipo;
        switch(n.getOperador()){
            case IDENTIFICADOR: tipo=geVisitId(n);break;
            case ENTERO:tipo = 1; break;
            case REAL: tipo = 2; break;
            case BOOLEANO: tipo = 3; break;
            case CADENA: tipo = 4; break;

            case MAS:tipo = geVisitAdd(n);break;
            case MENOS:tipo = geVisitAdd(n); break;
            
            case POR:tipo = geVisitMul(n);break;
            case DIV:tipo = geVisitMul(n);break;
            case DIVENTERA:tipo = geVisitMul(n);break;
            case MODULO:tipo = geVisitMul(n);break;

            case LE: tipo = geVisitComp(n);break;
            case GR: tipo = geVisitComp(n);break;
            case LEQ: tipo = geVisitComp(n);break;
            case GRQ: tipo = geVisitComp(n);break;
            case EQUALS: tipo = geVisitComp(n);break;
            case DIFF: tipo = geVisitComp(n);break;
            case AND: tipo = geVisitComp(n);break;
            case OR: tipo = geVisitComp(n);break;
            case NOT: tipo = geVisitComp(n);break;

            case EQ: tipo =0; generateVisitAsign(n);break;
            
            case PRINT: geVisitPrint(n);tipo=0; break;
            case WHILE: geVisitWhile(n);tipo=0; break;

            case IF: geVisitIf(n);tipo=0; break;
            case ELSE: geVisitElse(n);tipo=0; break;

            case BLOQUE: geVisitBlock(n);tipo=0; break;
            case RAIZ: geVisitBlock(n);tipo=0; break;

            default: tipo=0; break;
        }
        return tipo;

    }

  }