package unikut;

import java.util.Scanner;
import static unikut.CoresTerminal.*;

public class Cadastro {


    public static final Scanner in = new Scanner(System.in);
    private Usuario[] usuarios;
    private int qtd;

    public Cadastro() {
        usuarios = new Usuario[100];
        this.qtd = 0;
    }

    public void cadastrarUsuario(String login) {
        Usuario userAux = new Usuario(login, "", "");
        int achouUsuario;
        achouUsuario = buscarUsuario(userAux);
        if (achouUsuario != -1) { // login ja encontrado no vetor.
            System.err.println("UNIKUT - O Usuário já se encontra cadastrado!");
            System.out.println("Informe um login diferente!");
        } else { // login não encontrado, processo de cadastro...
            String senha, nome = "";
            int op;
            System.out.print("Informe sua senha: ");
            senha = in.nextLine();
            System.out.println("Deseja inserir um nome na sua conta?");
            do {
                System.out.print("Digite 1 - para sim ou 2 - para não: ");
                op = in.nextInt();
                in.nextLine();
                switch (op) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        nome = in.nextLine();
                        break;
                    case 2:
                        nome = "Convidado";
                        System.out.println(ANSI_GREEN + "UNIKUT - Seu nome foi definido como 'Convidado!'" + ANSI_RESET);
                        break;
                    default:
                        System.err.println("Opção invalida!");
                }

            } while (op != 1 && op != 2);
            usuarios[this.qtd] = new Usuario(login, senha, nome);
            this.qtd++; // usuario cadastrado.
            System.out.println(ANSI_GREEN + "UNIKUT - Usuário cadastrado!" + ANSI_RESET);

        }
    }

    private int buscarUsuario(Usuario user) {
        int i = 0;
        if (this.qtd == 0) {
            return -1;
        }
        for (i = 0; i < this.qtd; i++) {
            if (usuarios[i].compareTo(user) == 0) {
                return i; // retorna posição do usuario no vetor.
            }
        }
        return -1; // login não encontrado.
    }

    public Usuario procurarUsuario(String login, String senha) {
        int achouUsuario;
        Usuario userAux = new Usuario(login, "", "");
        achouUsuario = buscarUsuario(userAux);
        if (achouUsuario >= 0) {
            if (senha.equals(usuarios[achouUsuario].getSenha())) {
                return usuarios[achouUsuario]; // retorna o usuario.
            } else {
                return null; // senha incorreta.
            }
        } else {
            return null; // login não encontrado.
        }
    }

    public void alterarDados(Usuario user) {
        int op;
        String novoNome, novaSenha;
        do {
            System.out.println("Menu de opções\n"
                    + "1 - Alterar nome\n"
                    + "2 - Alterar senha\n"
                    + "3 - Alterar nome e senha\n"
                    + "4- Voltar ao menu anterior");

            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1:
                    //altera nome
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    user.alterarNome(novoNome);
                    System.out.println(ANSI_GREEN + "Nome alterado com sucesso!" + ANSI_RESET);
                    break;
                case 2:
                    //altera senha
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    user.alterarSenha(novaSenha);
                    System.out.println(ANSI_GREEN + "Senha alterado com sucesso!" + ANSI_RESET);
                    break;
                case 3:
                    //altera nome e senha
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    user.alterarNomeSenha(novoNome, novaSenha);
                    System.out.println(ANSI_GREEN + "Nome e senha alterado com sucesso!" + ANSI_RESET);
                    break;
                case 4:
                    //encerra e volta ao menu anterior
                    System.out.println("Voltando ao menu...");
                    return;
                default:
                    //verificação de numeros fora do menu
                    System.err.println("Opção inválida!");
                    break;
            }

        } while (op != 4);
    }

    public void exibeListaAmigosPendentes(Usuario user) {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(user);
        if (usuarios[qtdUsuario].getQtdListaAmigosPendentes() == 0) {
            System.err.println("Lista de amigos pendentes vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                System.out.println(usuarios[qtdUsuario].getListaAmigosPendentes(i));
            }
        }
    }

    public void exibeListaAmigos(Usuario user) {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(user);
        if (usuarios[qtdUsuario].getQtdListaAmigos() == 0) {
            System.err.println("Lista de amigos vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigos(); i++) {
                System.out.println(usuarios[qtdUsuario].getListaAmigos(i));
            }
        }
    }

    public void aceitaAmigos(Usuario user) {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(user);
        if (usuarios[qtdUsuario].getQtdListaAmigosPendentes() == 0) {
            return;
        } else {
            String amigo;
            System.out.println("Informe o login do amigo que deseja aceitar:");
            amigo = in.next();
            in.nextLine();
            Usuario userAux = new Usuario(amigo);
            int qtdAmigo = buscarUsuario(userAux);
            String eu = user.getLogin();
            boolean achou = false;
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[qtdUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    achou = true;
                }
            }
            if (achou == false) {
                System.err.println("Erro, amigo não encontrado na lista de pendentes!");
            } else {
                usuarios[qtdUsuario].setListaAmigos(amigo);
                usuarios[qtdAmigo].setListaAmigos(eu);
                System.out.println(ANSI_GREEN + "UNIKUT - Pedido aceito com sucesso!" + ANSI_RESET);

            }
        }
    }

    public void adicaoAmigos(Usuario user, String amigo) {
        int qtdUsuario, qtdAmigo;
        String eu = user.getLogin();
        qtdUsuario = buscarUsuario(user);
        Usuario userAux = new Usuario(amigo);
        qtdAmigo = buscarUsuario(userAux);
        if (qtdAmigo == -1) {
            System.err.println("Erro, este usuário não existe!");
        } else if (user.getLogin().compareTo(amigo) == 0) {
            System.err.println("Erro, você não pode adicionar você mesmo!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[qtdUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já encontra-se na lista de pendentes!");
                    return;
                }
            }
            for (int i = 0; i < usuarios[qtdAmigo].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[qtdAmigo].getListaAmigosPendentes(i).compareTo(usuarios[qtdUsuario].getLogin()) == 0) {
                    System.err.println("Erro, pedido de amizade já enviado!");
                    return;
                }
            }
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigos(); i++) {
                if (usuarios[qtdUsuario].getListaAmigos(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já encontra-se na lista de amizades!");
                    return;
                }
            }
            usuarios[qtdAmigo].setListaAmigosPendentes(eu);
            System.out.println(ANSI_GREEN + "UNIKUT - Pedido de amizade enviado com sucesso!" + ANSI_RESET);

        }
    }

    public void historicoMensagens(Usuario user, String amigo) {
        int i = 0, j = 0;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        if (resultado == true) {
            System.out.println("=============================");
            System.out.println("Histórico de mensagens:");
            if (usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) == 0 && usuarios[qtdAmigo].getQtdMensagens(qtdUsuario) == 0) {
                System.err.println("Histórico de mensagens vázio!");
            } else {
                while (i < usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) || j < usuarios[qtdAmigo].getQtdMensagens(qtdUsuario)) {
                    if (usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) == null) {
                        System.out.println(ANSI_PURPLE + usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) + ANSI_RESET);
                        System.out.println(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET);
                        i++;
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) == null) {
                        System.out.println(ANSI_PURPLE + usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET);
                        j++;
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) < 0) {
                        System.out.println(ANSI_PURPLE + usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) + ANSI_RESET);
                        System.out.println(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET);
                        i++;
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) > 0) {

                        System.out.println(ANSI_PURPLE + usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) + ANSI_RESET);
                        System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET);
                        j++;
                    }
                }
            }
        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }
    }

    public void enviarMensagem(Usuario user, String amigo, Scanner in) {
        String mensagem;
        boolean option = true;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        if (resultado == true) {
            while (option != false) {
                System.out.println("=============================");
                System.out.println("Digite a mensagem:");
                System.out.print("-> ");
                mensagem = in.nextLine();
                usuarios[qtdUsuario].setMensagens(qtdAmigo, mensagem);
                System.out.println("Deseja enviar outra mensagem? [true/false]");

                option = in.nextBoolean();
                in.nextLine();
            }
        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }

    }
}
