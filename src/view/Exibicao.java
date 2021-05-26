package view;

import controller.Cadastro;
import java.util.Scanner;

public class Exibicao {
    
    public static Scanner in = new Scanner(System.in);

    public static void printaMural(String mensagem) {
        System.out.println(mensagem);
    }

    public static void mensagemMural(Cadastro cadastro, int qtdUsuario, int qtdAmigo) {
        String mensagem;
        String option = "sim";
        while (option.charAt(0) == 's' || option.charAt(0) == 'S') {
            System.out.println("=============================");
            System.out.println("Digite a mensagem para o mural:");
            System.out.print("-> ");
            mensagem = in.nextLine();
            cadastro.setSolicitacaoMural(qtdUsuario, qtdAmigo, mensagem);
            System.out.println("UNIKUT - Mensagem enviada com sucesso!");
            System.out.println("Deseja enviar outra mensagem? [Sim/Não]");
            option = in.next();
        }
    }

    public static String printaSolicitacaoMural(String solicitacao) {
        String opcao;
        System.out.println(solicitacao);
        System.out.println("Deseja adicionar essa mensagem ao mural público? [Sim/Não]");
        System.out.print("-> ");
        opcao = in.next();
        if(opcao.charAt(0) == 's' || opcao.charAt(0) == 'S'){
            System.out.println("UNIKUT - Mensagem postada no mural com sucesso!");
        } else {
            System.err.println("UNIKUT - Mensagem recusada com sucesso!");
        }
        return opcao;
    }
}
