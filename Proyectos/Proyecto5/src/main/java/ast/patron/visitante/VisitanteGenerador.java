public class VisitanteGenerador {
    Registros reg = new Registros();
  
    public void visit(DifNodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();
  
        String objetivo = reg.getObjetivo();
        String[] siguientes = reg.getNsiguientes(2);
  
        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguiente[0]);
        hi.accept(this);
  
        // Genero el código del subárbol derecho
        reg.setObjetivo(siguiente[1]);
        hd.accept(this);
  
        String opcdode =  "sub";
  
        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
  }