import java.util.Scanner;

public class Calculadora {
    private Historico historico = new Historico();
    private Validador validador = new Validador();
    private Parser parser = new Parser();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        String continuar;
        do {
            processarExpressao();
            continuar = perguntarSeContinua();
        } while (continuar.equals("s"));
        exibirHistorico();
        scanner.close();
    }

    private void processarExpressao() {
        System.out.println("Digite a expressão matemática:");
        String expressao = scanner.nextLine();
        if (validador.validarExpressao(expressao)) {
            try {
                double resultado = parser.calcularExpressao(expressao);
                System.out.println("Resultado: " + resultado);
                historico.salvar(expressao, resultado);
            } catch (Exception e) {
                System.out.println("Erro ao calcular a expressão: " + e.getMessage());
            }
        } else {
            System.out.println("Expressão inválida.");
        }
    }

    private String perguntarSeContinua() {
        System.out.println("Deseja digitar outra expressão? (s/n):");
        return scanner.nextLine().trim().toLowerCase();
    }

    private void exibirHistorico() {
        historico.exibir();
    }
}
