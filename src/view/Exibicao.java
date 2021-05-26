package view;

import static view.CoresTerminal.ANSI_GREEN;
import static view.CoresTerminal.ANSI_RESET;

public class Exibicao {

    public static void exibirAmigos(String amigo) {
        System.out.println(amigo);
        System.out.println(ANSI_GREEN + "UNIKUT - Você tem o amigo " + amigo + ANSI_RESET);
    }
    
    public static void exibirMatch(String match) {
        System.out.println(match);
        System.out.println(ANSI_GREEN + "UNIKUT - Você deu Match com " + match + ANSI_RESET);
    }
    
    public static void exibirAmigosPendentes(String amigosPendentes) {
        System.out.println(amigosPendentes);
        System.out.println(ANSI_GREEN + "UNIKUT - Você deu Match com " + amigosPendentes + ANSI_RESET);
    }
}
