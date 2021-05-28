package view;

import static model.Contas.in;
import static view.CoresTerminal.*;


public class Exibicao {

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
