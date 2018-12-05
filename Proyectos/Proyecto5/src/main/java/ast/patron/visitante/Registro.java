package ast.patron.visitante;
import ast.patron.compuesto.*;
import ast.patron.tipos.*;
import java.util.LinkedList;
import java.util.Arrays;

public class Registro{

    int objetivoEntero;

    // Todos los registros enteros disponibles
    String [] E_registros = {"$t0","$t1","$t2","$t3", "$t4","$t5","$t6","$t7","$t8","$t9"};
  
  
    public void setObjetivo(int o){
        objetivoEntero = o % E_registros.length;
    }
  
    public void setObjetivo(String o){
        int nvo_objetivo = Arrays.asList(E_registros).indexOf(o);
        setObjetivo(nvo_objetivo);
    }
  
    public String getObjetivo(){
        return E_registros[objetivoEntero];
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