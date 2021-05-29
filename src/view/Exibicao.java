package view;

import controller.Cadastro;
import java.util.Scanner;
import static model.Contas.in;
import static view.CoresTerminal.*;

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

    public static void mensagem(String mensagem) {
        pularLinha();
        System.out.print(ANSI_BLUE + mensagem + ANSI_RESET);
        pularLinha();
    }
    public static void mensagemAmigo(String mensagem) { //amigo
        pularLinha();
        System.out.print(ANSI_YELLOW + mensagem + ANSI_RESET);
        pularLinha();
    }

    public static void mensagemHora(String mensagem) {
        System.out.print(ANSI_PURPLE + mensagem + ANSI_RESET);
    }

    public static void pularLinha() {
        System.out.println("");
    }

    public static int tratamentoSenha() {
        //tratando exceçao de senha errada
        System.out.println("Senha errada, 1- Tentar novamente ou 2 - Pular mensagem oculta");
        int opcaoSenhaSecreta = in.nextInt();
        in.nextLine();
        switch (opcaoSenhaSecreta) {
            case 1:
                System.err.println("Tentando novamente");
                break;
            case 2:
                System.err.println("Mensagem continuará oculta");
                break;
            default:
                System.out.println("Ops, sua opção é invalida, tente novamente");
                System.out.println("1- Tentar novamente ou 2 - Pular mensagem oculta");
                opcaoSenhaSecreta = in.nextInt();
                in.nextLine();
        }
        return opcaoSenhaSecreta;
    }

    public static void solicitacaoSecretaMensagem() {
        System.out.print("A mensagem é secreta! digite a senha: ");
    }

    public static void mensagemSecreta(String nome) {
        System.out.println(ANSI_RED + "[Mens. Secreta]" + ANSI_RESET + ANSI_BLUE + nome + ANSI_RESET);
    }

    public static void mensagemSecretaAmigo(String nome) {
        System.out.print(ANSI_RED + "[Mens. Secreta]" + ANSI_RESET + ANSI_YELLOW + nome + ANSI_RESET);
    }


}
