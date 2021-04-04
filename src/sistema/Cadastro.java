package sistema;

import java.util.Scanner;
import java.util.Random;

public class Cadastro {

    private Usuario[] usuarios;
    private int qtd;

    public Cadastro() {
        usuarios = new Usuario[100];
        this.qtd = 0;
    }

    public void cadastrarUsuario(String login) {
        Usuario u = new Usuario(login, "", "");
        int achouUsuario;
        achouUsuario = buscarUsuario(u);
        if (achouUsuario != -1) { // login ja encontrado no vetor.
            System.out.println("===================================================");
            System.err.println("O Usuário já se encontra cadastrado!");
            System.out.println("Informe um login diferente!");
            System.out.println("===================================================");
        } else { // login não encontrado, processo de cadastro...
            Scanner in = new Scanner(System.in);
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
                        System.out.println("===================================================");
                        System.out.println("Seu nome foi definido como 'Convidado!'");
                        System.out.println("===================================================");
                        break;
                    default:
                        System.out.println("===================================================");
                        System.err.println("Opção invalida!");
                        System.out.println("===================================================");
                }

            } while (op != 1 && op != 2);
            usuarios[this.qtd] = new Usuario(login, senha, nome);
            this.qtd++; // usuario cadastrado.
            System.out.println("===================================================");
            System.out.println("Usuário cadastrado!");
            System.out.println("===================================================");
        }
    }

    private int buscarUsuario(Usuario u) {
        int i = 0;
        if (this.qtd == 0) {
            return -1;
        }
        for (i = 0; i < this.qtd; i++) {
            if (usuarios[i].compareTo(u) == 0) {
                return i; // retorna posição do usuario no vetor.
            }
        }
        return -1; // login não encontrado.
    }

    public Usuario procurarUsuario(String login, String senha) {
        int achouUsuario;
        Usuario u = new Usuario(login, "", "");
        achouUsuario = buscarUsuario(u);
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

    public void alterarDados(Usuario u) {
        Scanner in = new Scanner(System.in);
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
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    u.alterarNome(novoNome);
                    System.out.println("Nome alterado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    u.alterarSenha(novaSenha);
                    System.out.println("Senha alterado com sucesso!");
                    break;
                case 3:
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    u.alterarNomeSenha(novoNome, novaSenha);
                    System.out.println("Nome e senha alterado com sucesso!");
                    break;
                case 4:
                    System.out.println("Voltando ao menu...");
                    return;
                default:
                    System.err.println("Opção inválida!");
                    break;
            }

        } while (op != 4);
    }

    public void exibeListaAmigosPendentes(Usuario u) {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(u);
        if (usuarios[qtdUsuario].getQtdListaAmigosPendentes() == 0) {
            System.err.println("Lista de amigos pendentes vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                System.out.println(usuarios[qtdUsuario].getListaAmigosPendentes(i));
            }
        }
    }

    public void exibeListaAmigos(Usuario u) {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(u);
        if (usuarios[qtdUsuario].getQtdListaAmigos() == 0) {
            System.err.println("Lista de amigos vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigos(); i++) {
                System.out.println(usuarios[qtdUsuario].getListaAmigos(i));
            }
        }
    }

    public void aceitaAmigos(Usuario u) {
        Scanner in = new Scanner(System.in);
        int qtdUsuario;
        qtdUsuario = buscarUsuario(u);
        if (usuarios[qtdUsuario].getQtdListaAmigosPendentes() == 0) {
            return;
        } else {
            String amigo;
            System.out.println("Informe o login do amigo que deseja aceitar:");
            amigo = in.next();
            in.nextLine();
            Usuario usu = new Usuario(amigo);
            int qtdAmigo = buscarUsuario(usu);
            String eu = u.getLogin();
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
                System.out.println("UNIKUT - Pedido aceito com sucesso!");
            }
        }
    }

    public void adicaoAmigos(Usuario u, String amigo) {
        int qtdUsuario, qtdAmigo;
        String eu = u.getLogin();
        qtdUsuario = buscarUsuario(u);
        Usuario usu = new Usuario(amigo);
        qtdAmigo = buscarUsuario(usu);
        if (qtdAmigo == -1) {
            System.err.println("Erro, este usuário não existe!");
        } else if (u.getLogin().compareTo(amigo) == 0) {
            System.err.println("Erro, você não pode adicionar você mesmo!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[qtdUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já encontra-se na lista de pendentes!");
                    return;
                }
            }
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigos(); i++) {
                if (usuarios[qtdUsuario].getListaAmigos(i).compareTo(amigo) == 0) {
                    System.err.println("Erro, esse usuário já encontra-se na sua lista de amizade.");
                    return;
                }
            }
            usuarios[qtdUsuario].setListaAmigosPendentes(amigo);
            usuarios[qtdAmigo].setListaAmigosPendentes(eu);
            System.out.println("UNIKUT - Pedido de amizade enviado com sucesso!");
        }
    }
}
