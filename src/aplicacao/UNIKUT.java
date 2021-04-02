package aplicacao;

import sistema.*;
import java.util.Scanner;

public class UNIKUT {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        int op;
        String login, senha;
        while (true) {
            menuDesLog();
            op = in.nextInt();
            switch (op) {
                case 1:
                    //criação de conta
                    System.out.print("Digite o login: ");
                    login = in.nextLine();
                    cadastro.cadastrarUsuario(login);
                    //pós criação \/
                    break;
                case 2:
                    //login em conta
                    System.out.print("Digite o seu login: ");
                    login = in.nextLine();
                    System.out.print("Digite a sua senha: ");
                    senha = in.nextLine();
                    Usuario  user = cadastro.procurarUsuario(login, senha);
                    //pós login \/
                    if (user != null) {
                    logado(cadastro, in, user);
                    }else {
                        System.out.println("=====================================");
                        System.err.println("Login ou senha incorretos!");
                        System.out.println("=====================================");
                    }
                    break;
                case 0:
                    System.out.println("UNIKUT - Desligando... Volte sempre!");
                    System.exit(0);
                    break;
                default:
                    System.err.println("UNIKUT - Erro, opção inválida.");
            }
        }
    }

    public static void logado(Cadastro cadastro, Scanner in, Usuario user) {
        int op;
        do {
            menuLog();
            op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println("UNIKUT - Saindo da conta...");
                    break;
                case 2:
                    //Alteração de perfil
                    break;
                case 3:
                    //Procurar e adicionar um amigo novo
                    break;
                case 4:
                    //Ver pedidos e aceitar pedidos de amizade
                    break;
                case 5:
                    //Exibir histórico de mensagens
                    break;
                case 6:
                    //Enviar mensagem
                    break;
                case 0:
                    System.out.println("UNIKUT - Desligando... Volte sempre!");
                    System.exit(0);
                    break;
                default:
                    System.err.println("UNIKUT - Erro, opção inválida.");
            }
        } while (op != 1);
    }

    public static void menuDesLog() {
        System.out.println("=============================");
        System.out.println("MENU:");
        System.out.println("1 - Criar uma conta.");
        System.out.println("2 - Logar em uma conta.");
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }

    public static void menuLog() {
        System.out.println("=============================");
        System.out.println("MENU:");
        System.out.println("1 - Sair da conta.");
        System.out.println("2 - Alterar perfil.");
        System.out.println("3 - Adicionar amigo.");
        System.out.println("4 - Aceitar pedido de amizade.");
        System.out.println("5 - Ver histórico de mensagens.");
        System.out.println("6 - Enviar mensagem.");
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }
}
