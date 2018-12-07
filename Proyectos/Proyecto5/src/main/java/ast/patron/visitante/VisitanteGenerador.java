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
     */
    public void geVisitId(Nodo n){
        String name=n.getNombre();
        int tipo;
        if(TablaSimbolos.containsKey(name)){
            TablaSimbolos.lookUp(name);
        }else{
            System.err.print("Error identificador no asignado");
            tipo = 0;
        }
        //return tipo;
    }
    /**
     * Metodo que llenara la tabla de simbolos
     * @param n Nodo de tipo Asignacion(EQ), como hijo izquierdo
     * siempre tiene un identificador
     */
    public void generateVisitAsign(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        //int h_type2 = geVisit(h.getLast());
        //TablaSimbolos.insert(h.getFirst().getNombre(),h_type2);
    }
    public void geVisitAdd(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
  
        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
  
        reg.setObjetivo(siguientes[0]);
        reg.setObjetivo(siguientes[1]);

        System.out.println(".text\n lw " + siguientes[0] + " " + hi.getValor());

        String opcode =  "add";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }

    public void geVisitSub(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
  
        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
  
        reg.setObjetivo(siguientes[0]);
        reg.setObjetivo(siguientes[1]);

        System.out.println(".text\n lw " + siguientes[0] + " " + hi.getValor());

        String opcode =  "sub";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }
    public void geVisitMul(Nodo n){
        LinkedList<Nodo> mul = n.hijos.hijos;
        //int mul_type1 = geVisit(mul.getFirst());
        //int mul_type2 = geVisit(mul.getLast());
        //int tipo = OperadoresTipo.getTypeMul(mul_type1,mul_type2);
        //System.out.print(tipo + "|");
        //return tipo;
    }
    public void geVisitMod(Nodo n){
        LinkedList<Nodo> mod = n.hijos.hijos;
        //int mod_type1 = geVisit(mod.getFirst());
        //int mod_type2 = geVisit(mod.getLast());
        //int tipo = OperadoresTipo.getTypeMod(mod_type1,mod_type2);
        //System.out.print(tipo +"|");
        //return tipo;
    }
    public void geVisitComp(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        //int h_type1 = geVisit(h.getFirst());
        //int h_type2 = geVisit(h.getLast());
        //int tipo = OperadoresTipo.getTypeComp(h_type1,h_type2);
        //System.out.print(tipo +"|");
        //return tipo;
    }    
    public void geVisitPrint(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        //int h_type1 = geVisit(h.getFirst());
        //if(h_type1!=4) System.err.println("error_semantico: PRINT");
    }

    public void geVisitWhile(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        //int h_type1 = geVisit(h.getFirst());
        //geVisit(h.getLast());
        //if(h_type1!=3) System.err.println("error_semantico: WHILE");
    }
    public void geVisitIf(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        //int h_type1 = geVisit(h.getFirst());
        //geVisit(h.getLast());
        //if(h_type1!=3) System.err.println("error_semantico: IF");
    }
    public void geVisitElse(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        //int h_type1 = geVisit(h.getFirst());
        //geVisit(h.get(1));
        //geVisit(h.getLast());
        //if(h_type1!=3) System.err.println("error_semantico: IF..ELSE");
    }
    public void geVisitBlock(Nodo n){
        LinkedList<Nodo> h = n.hijos.hijos;
        for(Nodo nodo:h) geVisit(nodo);
    }



    public void geVisit(Nodo n){
        switch(n.getOperador()){
            case IDENTIFICADOR: geVisitId(n);break;
            case ENTERO: break;
            case REAL:  break;
            case BOOLEANO:  break;
            case CADENA:  break;

            case MAS:geVisitAdd(n);break;
            case MENOS:geVisitSub(n); break;
            
            case POR:geVisitMul(n);break;
            case DIV:geVisitMul(n);break;
            case DIVENTERA:geVisitMul(n);break;
            case MODULO:geVisitMul(n);break;

            case LE: geVisitComp(n);break;
            case GR: geVisitComp(n);break;
            case LEQ: geVisitComp(n);break;
            case GRQ: geVisitComp(n);break;
            case EQUALS: geVisitComp(n);break;
            case DIFF: geVisitComp(n);break;
            case AND: geVisitComp(n);break;
            case OR: geVisitComp(n);break;
            case NOT: geVisitComp(n);break;

            case EQ: generateVisitAsign(n);break;
            
            case PRINT: geVisitPrint(n); break;
            case WHILE: geVisitWhile(n); break;

            case IF: geVisitIf(n); break;
            case ELSE: geVisitElse(n); break;

            case BLOQUE: geVisitBlock(n); break;
            case RAIZ: geVisitBlock(n); break;

            default: break;
        }
    }

  }