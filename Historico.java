import java.util.ArrayList;
import java.util.List;

public class Historico {
    private List<String> historico = new ArrayList<>();
    
    public void salvar(String expressao, double resultado) {
        historico.add(expressao + " = " + resultado);
    }

    public void exibir() {
        System.out.println("Histórico da Calculadora:");
        for (String entrada : historico) {
            System.out.println(entrada);
        }
    }
}
