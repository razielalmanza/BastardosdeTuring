package lexico;

public class Test {

    private static final String ARCHIVO = "src/main/resources/test.txt";

    public static void main(String[] args) {
        AnalizadorLexico al = new AnalizadorLexico(ARCHIVO);
        al.analiza();
    }
}