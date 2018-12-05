package ast.patron.visitante;
import ast.patron.compuesto.*;


public class VisitanteGenerador {
    Registro reg = new Registro();
  
    public void visit(DiffNodo n){
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
  }