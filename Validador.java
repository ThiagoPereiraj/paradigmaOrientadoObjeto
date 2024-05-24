public class Validador {
    public boolean validarExpressao(String expressao) {
        return expressao.matches("[0-9\\+\\-\\*/\\.]+");
    }
}