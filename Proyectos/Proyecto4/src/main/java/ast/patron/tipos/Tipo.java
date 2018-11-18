package ast.patron.tipos;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;

public enum Tipo{
    NODEFINIDO(0),
    INTEGER(1),
    DOUBLE(2),
    BOOLEAN(3),
    STRING(4);
    private final int tipo;
    private Tipo(int t){
        this.tipo = t;
    }
}