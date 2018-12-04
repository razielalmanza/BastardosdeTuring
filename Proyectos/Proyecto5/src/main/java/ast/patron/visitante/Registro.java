package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;

public class Registro{

    int objetivoEntero;

    // Todos los registros enteros disponibles
    String[] E_registros = {"$t0", ... ,"$t9"};
  
  
    public void setObjetivo(int o){
        objetivoEntero = o % E_registros.length;
    }
  
    public void setObjetivo(String o){
        int nvo_objetivo = Arrays.asList(E_registros).indexOf(o);
        setObjetivo(nvo_objetivo);
    }
  
    public int getObjetivo(){
        return objetivoEntero;
    }
  
    /* Regresa los n registos siguientes "disponibles" */
    public String[] getNsiguientes(int n){
        String[] siguientes = new String[n];
  
        for(int i = 0; i < n; i++){
            siguientes[i] = E_registros[(objetivoEntero + i) % E_registros.length];
        }
        return siguientes;
    }

}