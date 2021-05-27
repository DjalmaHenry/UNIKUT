package view;

import controller.Cadastro;
import static view.CoresTerminal.ANSI_GREEN;
import static view.CoresTerminal.ANSI_RESET;
import java.util.Scanner;
import model.Usuario;

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
