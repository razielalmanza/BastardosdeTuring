package ast.patron.tipos;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;

public class OperadoresTipo{

    public int type;

    static String[] typenames = {"No definido","Integer","Double",
                                "Boolean","String"};


    public static int[][] addTypes = {
        {1,2,1,0},
        {2,2,2,0},
        {1,2,3,0},
        {0,0,0,0}
    };

    public static int[][] diffTypes = {
        {1,2,1,0},
        {2,2,2,0},
        {1,2,3,0},
        {0,0,0,0}
    };

    public static int[][] divMultTypes = {
        {1,2,0,0},
        {2,2,0,0},
        {0,0,0,0},
        {0,0,0,0}
    };

}