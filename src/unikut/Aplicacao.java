package unikut;

import java.util.Scanner;
import static unikut.CoresTerminal.*;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cadastro cadastro = new CadastroAdmin();
        CadastroAdmin cadastroAux;
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
                    //criação de conta admin
                    String combinacao = "admin123";
                    System.out.println("Informe a chave de registro de admin:");
                    senha = in.nextLine();
                    if (senha.compareTo(combinacao) == 0) {
                        System.out.print("Digite o login: ");
                        login = in.nextLine();
                        cadastroAux = (CadastroAdmin) cadastro;
                        cadastroAux.cadastrarUsuarioAdmin(login);
                        cadastro = (Cadastro) cadastroAux;
                    } else {
                        System.err.println("UNIKUT - Chave de registro inválida!");
                    }
                    break;
                case 3:
                    //login em conta
                    System.out.print("Digite o seu login: ");
                    login = in.nextLine();
                    System.out.print("Digite a sua senha: ");
                    senha = in.nextLine();
                    Usuario user = cadastro.procurarUsuario(login, senha);
                    //pós login \/
                    if (user != null) {
                        boolean admin = cadastro.getAdmin(user);
                        if (admin == true) {
                            System.out.println("___________________________________________________");
                            System.out.println(ANSI_GREEN + "Você logou em sua conta admin!" + ANSI_RESET);
                            System.out.println("___________________________________________________");
                            cadastroAux = (CadastroAdmin) cadastro;
                            logadoAdmin(cadastroAux, in, user);
                            cadastro = (Cadastro) cadastroAux;
                        } else {
                            System.out.println("___________________________________________________");
                            System.out.println(ANSI_GREEN + "Você logou em sua conta!" + ANSI_RESET);
                            System.out.println("___________________________________________________");
                            logado(cadastro, in, user);
                        }
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

    public static void logadoAdmin(CadastroAdmin cadastro, Scanner in, Usuario user) {
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

    public static void menuDesLog() {
        System.out.println("UNIKUT - MENU:");
        System.out.println("1 - Criar uma conta.");
        System.out.println("2 - Criar uma conta admin.");
        System.out.println("3 - Logar em uma conta.");
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
