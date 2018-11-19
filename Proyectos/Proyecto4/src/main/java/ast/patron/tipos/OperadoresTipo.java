package ast.patron.tipos;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;

public class OperadoresTipo{

    public int type;

    static String[] typenames = {"No definido","Integer","Double",
                                "Boolean","String"};

    public static int getTypeAdd(int t1,int t2){return addTypes[t1][t2];}
    public static int getTypeMul(int t1,int t2){return divMultTypes[t1][t2];}
    public static int getTypeMod(int t1,int t2){return modTypes[t1][t2];}
    
    public static int[][] addTypes = {
        {0,0,0,0,0},
        {0,1,2,1,0},
        {0,2,2,2,0},
        {0,1,2,3,0},
        {0,0,0,0,0}
    };

    public static int[][] divMultTypes = {
        {0,0,0,0,0},
        {0,1,2,0,0},
        {0,2,2,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0}
    };

    public static int[][] modTypes = {
        {0,0,0,0,0},
        {0,1,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0},
        {0,0,0,0,0}
    };

}