package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Hashtable;

public class VisitanteGenerador {

    Registro reg;
    Hashtable<String,String> labels;
     /* Para guardar la salida. */
    private StringBuilder builder;
    Random ran;

    public VisitanteGenerador(){
        reg = new Registro();
        labels = new Hashtable<String,String>();
        ran = new Random();
    }
    /**
     * Metodo que llenara la tabla de simbolos
     * @param n Nodo de tipo Asignacion(EQ), como hijo izquierdo
     * siempre tiene un identificador
     */
    public void geVisitAsign(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
        String[] siguientes = reg.getNsiguientes(1);

        String objetivo = reg.getObjetivo();
        reg.setObjetivo(siguientes [0]);
        
        if (hd.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hd);                  // Se visita a este nodo ahora
        }

        System.out.println("#assign \n.text\nsw " + objetivo + " " + hi.getNombre());
    }

  

    
    public void geVisitAdd(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
        
        // Genera el codigo (si hay) del hijo derecho
        reg.setObjetivo(siguientes[0]);
        if (hi.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hi);                  // Se visita a este nodo ahora
        }
        reg.setObjetivo(siguientes[1]);
        if (hd.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hd);                  // Se visita a este nodo ahora
        }

        System.out.println("#add\n.text\n lw " + siguientes[0] + " " + hi.getValor().ival +
                                      "\n lw " + siguientes[1] + " " + hd.getValor().ival);
 
        String opcode =  "add";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }

    public void geVisitSub(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
        
        // Genera el codigo (si hay) del hijo derecho
        reg.setObjetivo(siguientes[0]);
        if (hi.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hi);                  // Se visita a este nodo ahora
        }
        reg.setObjetivo(siguientes[1]);
        if (hd.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hd);                  // Se visita a este nodo ahora
        }

        System.out.println("#sub\n.text\n lw " + siguientes[0] + " " + hi.getValor().ival +
                                      "\n lw " + siguientes[1] + " " + hd.getValor().ival);
 
        String opcode =  "sub";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }

     public void geVisitMul(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
        
        // Genera el codigo (si hay) del hijo derecho
        reg.setObjetivo(siguientes[0]);
        if (hi.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hi);                  // Se visita a este nodo ahora
        }
        reg.setObjetivo(siguientes[1]);
        if (hd.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hd);                  // Se visita a este nodo ahora
        }

        System.out.println("#mult\n.text\n lw " + siguientes[0] + " " + hi.getValor().ival +
                                       "\n lw " + siguientes[1] + " " + hd.getValor().ival);
 
        String opcode =  "mult";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }
     public void geVisitDiv(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
        
        // Genera el codigo (si hay) del hijo derecho
        reg.setObjetivo(siguientes[0]);
        if (hi.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hi);                  // Se visita a este nodo ahora
        }
        reg.setObjetivo(siguientes[1]);
        if (hd.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hd);                  // Se visita a este nodo ahora
        }

        System.out.println("#div\n.text\n lw " + siguientes[0] + " " + hi.getValor().ival +
                                "\n lw " + siguientes[1] + " " + hd.getValor().ival);
 
        String opcode =  "div";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }
  
    public void geVisitMod(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
  
        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(3);
  
         // Genera el codigo (si hay) del hijo derecho
        reg.setObjetivo(siguientes[0]);
        if (hi.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hi);                  // Se visita a este nodo ahora
        }
        reg.setObjetivo(siguientes[1]);
        if (hd.hijos !=  null){           // Tiene hijos aún, entonces iteramos sobre ellos
            geVisit(hd);                  // Se visita a este nodo ahora
        }

        System.out.println("#mod\n.text\n lw " + siguientes[0] + " " + hi.getValor().ival +
                                "\n lw " + siguientes[1] + " " + hd.getValor().ival);
 
        String opcode =  "div";
  
        System.out.println(opcode + " " + siguientes[0] + ", " +
                            siguientes[1] );
        opcode =  "mfhi";
  
        System.out.println(opcode + " " + siguientes[2]);

    } 

    public void geVisitComp(Nodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
  
        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
  
        reg.setObjetivo(siguientes[0]);
        reg.setObjetivo(siguientes[1]);

        System.out.println(".text\n lw " + siguientes[0] + " " + hi.getValor().ival +
                                "\n lw " + siguientes[1] + " " + hd.getValor().ival);
 
        String opcode =  "mult";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);

    }
    public void geVisitPrint(Nodo n){
        Nodo hi = n.getPrimerHijo();

        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(1);
        
        // Genera el codigo (si hay) del unico hijo
        reg.setObjetivo(siguientes[0]);
        // Para darle un valor unico a cada etiqueta le daremos un valor aleatorio
        // Reduciendo la probabilidad que tengan la misma.
        int id = ran.nextInt(100);
        String label = "cad" + id;

        if (hi.getType().getId() == 4){           // Si es de tipo cadena
            System.out.println("#print\n.data\n " + label +": .asciiz \"" + hi.getValor().sval + "\"" );
            System.out.println("\nla $a0, " + label + "\nli $v0, 4 \nsyscall");
        }else if(hi.getType().getId() == 1){      // Si es de tipo entero
            System.out.println("#print\n.data\n " + label +": .asciiz " + hi.getValor().ival  );
            System.out.println("\nla $a0, " + label + "\nli $v0, 1 \nsyscall");

        }
        // No hace falta meter al hash pues solo se usara para imprimir ese label                 
        
        
    }

    public void geVisitCadena(Nodo n){
        // Como hemos visto una cadena no recorremos su nodo
        // Ni necesitamos registros pues solo crea un label
        // Para darle un valor unico a cada etiqueta le daremos un valor aleatorio
        // Reduciendo la probabilidad que tengan la misma.
        int id = ran.nextInt(100);
        String label = "cad" + id;
        System.out.println("#cad\n.data\n " + label +": .asciiz \"" + n.getValor().sval + "\"" );
        //metemos al hash el label y el valor de la cadena, para luego ver si está declarada y regresar el label
        labels.put(n.getValor().sval,label);
    }

    public void geVisitInt(Nodo n){
        // Como hemos visto una cadena no recorremos su nodo
        // Ni necesitamos registros pues solo crea un label
        // Para darle un valor unico a cada etiqueta le daremos un valor aleatorio
        // Reduciendo la probabilidad que tengan la misma.
        int id = ran.nextInt(100);
        String label = "int" + id;
        System.out.println("#int\n.data\n " + label +": .word " + n.getValor().ival );
        //metemos al hash el label y el valor del int como cadena, para luego ver si está declarada y regresar el label
        labels.put(String.valueOf(n.getValor().ival),label);
    }

     public void geVisitBol(Nodo n){
        String label = "";
        if(n.getValor().bval){
            label = "True "; 
            System.out.println("#bool\n.data\n " + label +":.word " + 1);
        }else {
            label = "False";
            System.out.println("#bool\n.data\n " + label + ":.word " + 0 );

        }
        //metemos al hash el label y el valor del int como cadena, para luego ver si está declarada y regresar el label
        labels.put(String.valueOf(n.getValor().ival),label);
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
            case ENTERO: geVisitInt(n); break;
            case REAL:  break;
            case BOOLEANO: geVisitBol(n); break;
            case CADENA: geVisitCadena(n); break;

            case MAS:geVisitAdd(n);break;
            case MENOS:geVisitSub(n); break;
            
            case POR:geVisitMul(n);break;
            case DIV:geVisitDiv(n);break;
            case DIVENTERA:geVisitMul(n);break;
            case MODULO:geVisitMod(n);break;

            case EQ: geVisitAsign(n);break;
            
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