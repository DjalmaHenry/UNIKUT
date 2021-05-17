package view;

import controller.Cadastro;
import java.util.Scanner;
import model.Usuario;
import static view.CoresTerminal.*;

public class Logado {
    
    private static Scanner in = new Scanner(System.in);
    
    public static void logado(Usuario user, Cadastro cadastro) {
        System.out.println("___________________________________________________");
        System.out.println(ANSI_GREEN + "Você logou em sua conta!" + ANSI_RESET);
        System.out.println("___________________________________________________");
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
                    cadastro.alteraDados(user); //parei aqui, ainda precisa arrumar esse metodo
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
                case 8:
                    //enviar mensagem para mural
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.enviarSolicitacaoMural(user, amigo, in);
                    break;
                case 9:
                    //mural pendentes
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.solicitacaoMural(user, amigo);
                    break;
                case 10:
                    //mostrar mural
                    cadastro.exibeMural();
                    break;
                case 11:
                    cadastro.exibirMatch(user);
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

    public static void logadoAdmin(Usuario user, Cadastro cadastro) {
        System.out.println("___________________________________________________");
        System.out.println(ANSI_GREEN + "Você logou em sua conta admin!" + ANSI_RESET);
        System.out.println("___________________________________________________");
        int op;
        String login, amigo;
        do {
            menuLogAdmin();
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
                case 8:
                    //enviar mensagem para mural
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.enviarSolicitacaoMural(user, amigo, in);
                    break;
                case 9:
                    //mural pendentes
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    cadastro.solicitacaoMural(user, amigo);
                    break;
                case 10:
                    //mostrar mural
                    cadastro.exibeMural();
                    break;
                case 11:
                    cadastro.exibirMatch(user);
                    break;
                case 12:
                    System.out.println("Informe o login da conta que deseja alterar:");
                    login = in.next();
                    in.nextLine();
                    Usuario userAltera = cadastro.procurarUsuario(login);
                    if (userAltera != null) {
                        cadastro.alterarDados(userAltera);
                    } else {
                        System.err.println("UNIKUT - Usuário não cadastrado!");
                    }
                    break;
                case 13:
                    System.out.println("Informe o login da conta que deseja excluir:");
                    login = in.next();
                    in.nextLine();
                    Usuario userExclui = new Usuario(login, "", "");
                    cadastro.excluiConta(userExclui);
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
    
    public static void menuLog() {
        System.out.println("UNIKUT - MENU:");
        System.out.println("1 - Sair da conta.");
        System.out.println("2 - Alterar perfil.");
        System.out.println("3 - Adicionar amigo.");
        System.out.println("4 - Aceitar pedido de amizade.");
        System.out.println("5 - Ver lista de amizades.");
        System.out.println("6 - Ver histórico de mensagens.");
        System.out.println("7 - Enviar mensagem para amigo.");
        System.out.println("8 - Enviar mensagem para mural.");
        System.out.println("9 - Mural pendentes.");
        System.out.println("10 - Mostrar mural.");
        System.out.println("11 - Exibir Matchs.");
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }

    public static void menuLogAdmin() {
        System.out.println("UNIKUT - MENU:");
        System.out.println("1 - Sair da conta.");
        System.out.println("2 - Alterar meu perfil.");
        System.out.println("3 - Adicionar amigo.");
        System.out.println("4 - Aceitar pedido de amizade.");
        System.out.println("5 - Ver lista de amizades.");
        System.out.println("6 - Ver histórico de mensagens.");
        System.out.println("7 - Enviar mensagem para amigo.");
        System.out.println("8 - Enviar mensagem para mural.");
        System.out.println("9 - Mural pendentes.");
        System.out.println("10 - Mostrar mural.");
        System.out.println("11 - Exibir Matchs.");
        System.out.println(ANSI_BLUE + "12 - Alterar perfil de uma conta." + ANSI_RESET);
        System.out.println(ANSI_BLUE + "13 - Excluir uma conta." + ANSI_RESET);
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }
}
