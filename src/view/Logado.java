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
                    alteraDados(user, cadastro);
                    break;
                case 3:
                    //Procurar e adicionar um amigo novo
                    String amigo;
                    System.out.println("UNIKUT - Adicionar amigos:");
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.adicaoAmigos(user, amigo);
                        System.out.println(ANSI_GREEN + "UNIKUT - Pedido de amizade enviado com sucesso!" + ANSI_RESET);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    //Ver pedidos e aceitar pedidos de amizade
                    try {
                    System.out.println("Aceitar amigos pendentes:");
                    cadastro.pedidosAmizades(user);
                    System.out.println(ANSI_GREEN + "UNIKUT - Pedido aceito com sucesso!" + ANSI_RESET);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
                case 5:
                    //ver lista de amizades
                    try {
                    System.out.println("Lista de Amigos: ");
                    cadastro.listaAmizades(user);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
                case 6:
                    //Exibir histórico de mensagens
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.historicoMensagens(user, amigo);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 7:
                    //Enviar mensagem
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.enviarMensagem(user, amigo, in, cadastro);
                        System.out.println(ANSI_GREEN + "Mensagem enviada!" + ANSI_RESET);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    //enviar mensagem para mural
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.enviaSolicitacaoMural(cadastro, user, amigo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    //mural pendentes
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.solicitacaoMural(user, cadastro, amigo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    //mostrar mural
                    System.out.println("MURAL:");
                    try {
                        cadastro.exibirMural(cadastro);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    ///////////////////////////////////////////////////////////
                    try {
                    cadastro.exibirMatch(user);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                //////////////////////////////////////////////////////////
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
                    alteraDados(user, cadastro);
                    break;
                case 3:
                    //Procurar e adicionar um amigo novo
                    System.out.println("UNIKUT - Adicionar amigos:");
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.adicaoAmigos(user, amigo);
                        System.out.println(ANSI_GREEN + "UNIKUT - Pedido de amizade enviado com sucesso!" + ANSI_RESET);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    //Ver pedidos e aceitar pedidos de amizade
                    try {
                    System.out.println("Aceitar amigos pendentes:");
                    cadastro.pedidosAmizades(user);
                    System.out.println(ANSI_GREEN + "UNIKUT - Pedido aceito com sucesso!" + ANSI_RESET);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
                case 5:
                    //ver lista de amizades
                    try {
                    System.out.println("Lista de Amigos: ");
                    cadastro.listaAmizades(user);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
                case 6:
                    //Exibir histórico de mensagens
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.historicoMensagens(user, amigo);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 7:
                    //Enviar mensagem
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.enviarMensagem(user, amigo, in, cadastro);
                        System.out.println(ANSI_GREEN + "Mensagem enviada!" + ANSI_RESET);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    //enviar mensagem para mural
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.enviaSolicitacaoMural(cadastro, user, amigo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    //mural pendentes
                    System.out.println("Informe o login do amigo:");
                    amigo = in.next();
                    in.nextLine();
                    try {
                        cadastro.solicitacaoMural(user, cadastro, amigo);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    //mostrar mural
                    System.out.println("MURAL:");
                    try {
                        cadastro.exibirMural(cadastro);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                    cadastro.exibirMatch(user);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
                case 12:
                    System.out.println("Informe o login da conta que deseja alterar:");
                    login = in.next();
                    in.nextLine();
                    Usuario userAltera = cadastro.procurarUsuario(login);
                    if (userAltera != null) {
                        alteraDados(userAltera, cadastro);
                    } else {
                        System.err.println("UNIKUT - Usuário não cadastrado!");
                    }
                    break;
                case 13:
                    System.out.println("Informe o login da conta que deseja excluir:");
                    login = in.next();
                    in.nextLine();
                    try {
                        cadastro.excluiConta(login);
                        System.out.println(ANSI_GREEN + "UNIKUT - Usuário excluido com sucesso!" + ANSI_RESET);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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

    public static void alteraDados(Usuario user, Cadastro cadastro) {
        int op;
        String novoNome,
                novaSenha;
        do {
            System.out.println("Menu de opções\n" + "1 - Alterar nome\n" + "2 - Alterar senha\n"
                    + "3 - Alterar nome e senha\n" + "4- Voltar ao menu anterior");

            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1:
                    // altera nome
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    cadastro.alteraNome(user, novoNome);
                    System.out.println(ANSI_GREEN + "Nome alterado com sucesso!" + ANSI_RESET);
                    break;
                case 2:
                    // altera senha
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    cadastro.alteraSenha(user, novaSenha);
                    System.out.println(ANSI_GREEN + "Senha alterado com sucesso!" + ANSI_RESET);
                    break;
                case 3:
                    // altera nome e senha
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    cadastro.alteraDados(user, novoNome, novaSenha);
                    System.out.println(ANSI_GREEN + "Nome e senha alterado com sucesso!" + ANSI_RESET);
                    break;
                case 4:
                    // encerra e volta ao menu anterior
                    System.out.println("Voltando ao menu...");
                    return;
                default:
                    // verificação de numeros fora do menu
                    System.err.println("Opção inválida!");
                    break;
            }

        } while (op != 4);
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
