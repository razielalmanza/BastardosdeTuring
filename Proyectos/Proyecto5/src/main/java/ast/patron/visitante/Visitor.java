package ast.patron.visitante;
import ast.patron.compuesto.*;

public interface Visitor
{
    public void visit(IntHoja n);
    public void visit(FloatHoja n);
    public void visit(BooleanHoja n);
    public void visit(StringHoja n);
    public void visit(Nodo n);
}
