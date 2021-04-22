package unikut;

import java.util.Scanner;
import static unikut.CoresTerminal.*;

public class Aplicacao {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        int op;
        String login, senha;

        
        do {
            menuDesLog();
            op = in.nextInt();
            in.nextLine();
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
                    Usuario user = cadastro.procurarUsuario(login, senha);
                    //pós login \/
                    if (user != null) {
                        System.out.println("___________________________________________________");
                        System.out.println(ANSI_GREEN + "Você logou em sua conta!" + ANSI_RESET);
                        System.out.println("___________________________________________________");
                        logado(cadastro, in, user);
                    } else {
                        System.err.println("UNIKUT - Login ou senha incorretos!");
                    }
                    break;
                case 0:
                    System.out.println("UNIKUT - Desligando... Volte sempre!");
                    break;
                default:
                    System.err.println("UNIKUT - Erro, opção inválida.");
            }
        } while (op != 0);
    }

    public static void logado(Cadastro cadastro, Scanner in, Usuario user) {
        int op;
        System.out.println("Bem vindo, " + user.getNome() + ".");
        do {
            menuLog();
            op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println("UNIKUT - Saindo da conta...");
                    break;
                case 2:
                    //Alteração de perfil
                    cadastro.alterarDados(user);
                    break;
                case 3:
                    //Procurar e adicionar um amigo novo
                    String amigo;
                    System.out.println("UNIKUT - Adicionar amigos:");
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.adicaoAmigos(user, amigo);
                    break;
                case 4:
                    //Ver pedidos e aceitar pedidos de amizade
                    System.out.println("Aceitar amigos pendentes:");
                    cadastro.exibeListaAmigosPendentes(user);
                    cadastro.aceitaAmigos(user);
                    break;
                case 5:
                    //ver lista de amizades
                    cadastro.exibeListaAmigos(user);
                    break;
                case 6:
                    //Exibir histórico de mensagens
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.historicoMensagens(user, amigo);
                    break;
                case 7:
                    //Enviar mensagem
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.enviarMensagem(user, amigo, in);
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
        System.out.println("UNIKUT - MENU:");
        System.out.println("1 - Criar uma conta.");
        System.out.println("2 - Logar em uma conta.");
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }

    public static void menuLog() {
        System.out.println("UNIKUT - MENU:");
        System.out.println("1 - Sair da conta.");
        System.out.println("2 - Alterar perfil.");
        System.out.println("3 - Adicionar amigo.");
        System.out.println("4 - Aceitar pedido de amizade.");
        System.out.println("5 - Ver lista de amizades.");
        System.out.println("6 - Ver histórico de mensagens.");
        System.out.println("7 - Enviar mensagem.");
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }
}
