import java.util.Stack;

public class Parser {

    public double calcularExpressao(String expressao) throws Exception {
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();
        int i = 0;

        while (i < expressao.length()) {
            char ch = expressao.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expressao.length() && (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                    sb.append(expressao.charAt(i++));
                }
                numeros.push(Double.parseDouble(sb.toString()));
                continue;
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(ch)) {
                    double num2 = numeros.pop();
                    double num1 = numeros.pop();
                    char operador = operadores.pop();
                    numeros.push(calcular(num1, num2, operador));
                }
                operadores.push(ch);
            }

            i++;
        }

        while (!operadores.isEmpty()) {
            double num2 = numeros.pop();
            double num1 = numeros.pop();
            char operador = operadores.pop();
            numeros.push(calcular(num1, num2, operador));
        }

        if (numeros.size() != 1) {
            throw new Exception("Erro na avaliação da expressão.");
        }

        return numeros.pop();
    }

    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private double calcular(double num1, double num2, char operador) throws ArithmeticException {
        switch (operador) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Divisão por zero.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador desconhecido: " + operador);
        }
    }
}
