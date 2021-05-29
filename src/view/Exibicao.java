package view;

import controller.Cadastro;
import static view.CoresTerminal.*;
import java.util.Scanner;
import model.Usuario;
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
            try {
                cadastro.setSolicitacaoMural(qtdUsuario, qtdAmigo, mensagem);
                System.out.println("UNIKUT - Mensagem enviada com sucesso!");
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
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
        if (opcao.charAt(0) == 's' || opcao.charAt(0) == 'S') {
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

    public static void exibirAmigos(String amigo) {
        System.out.println(amigo);
        System.out.println(ANSI_GREEN + "UNIKUT - Você tem o amigo " + amigo + ANSI_RESET);
    }

    public static void exibirMatch(String match) {
        System.out.println(match);
        System.out.println(ANSI_GREEN + "UNIKUT - Você deu Match com " + match + ANSI_RESET);
    }

    public static void resultadoMatch(String resultado){
        System.out.println(resultado);
    }

    public static char perguntaMatch(){
        System.out.print("Voce deseja dar Match nesse usuario? S - para sim ou N - para não: ");
        char decisao = i.next().charAt(0);
        return decisao;
    }

    public static String informaLogin(){
        System.out.println("UNIKUT - Informe o login do amigo que deseja aceitar:");
        String amigo = in.next();
        in.nextLine();
        return amigo;
    }

    public static void exibirAmigosPendentes(String amigosPendentes) {
        System.out.println(amigosPendentes);
        System.out.println(ANSI_GREEN + "UNIKUT - Você deu Match com " + amigosPendentes + ANSI_RESET);
    }

    public static void exibirMsg(boolean resul, int qtdAmigo, int qtdUsuario, String senhaPadrao, Cadastro cadastro) throws Exception {
        int opcaoMensagemSecreta;
        String mensagem;
        Scanner in = new Scanner(System.in);
        if (resul == true) {
            System.out.println("=============================");
            System.out.println("Digite a mensagem:");
            System.out.print("-> ");
            mensagem = in.nextLine();
            System.out.println("Mensagem será secreta? 1-Sim  2-Não");
            opcaoMensagemSecreta = in.nextInt();
            in.nextLine();

            while (opcaoMensagemSecreta != 1 && opcaoMensagemSecreta != 2) {
                System.out.println("Opçao inválida!");
                System.out.println("Mensagem será secreta? 1-Sim  2-Não");

            }

            if (opcaoMensagemSecreta == 1) {
                if (senhaPadrao == null) {
                    String auxSenha;
                    System.out.println("Digite a senha da mensagem: ");
                    auxSenha = in.nextLine();
                    cadastro.setMsgSecreta(qtdUsuario, auxSenha);
                    try {
                        cadastro.setMensagensSecretaPadrao(qtdUsuario, qtdAmigo, mensagem);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    try {
                        cadastro.setMensagensSecreta(qtdUsuario, qtdAmigo, mensagem, senhaPadrao);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            } else {
                try {
                    cadastro.setMensagem(qtdUsuario, qtdAmigo, mensagem);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } else {
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        }

    }

}
