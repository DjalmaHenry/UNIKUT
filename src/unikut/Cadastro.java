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

    public void cadastrarUsuario(String login, String nome) {
        Usuario userAux = new Usuario(login, "", "");
//        int achouUsuario;
//        achouUsuario = buscarUsuario(userAux);
//        if (achouUsuario != -1) { // login ja encontrado no vetor.
//            System.err.println("UNIKUT - O Usuário já se encontra cadastrado!");
//            System.out.println("Informe um login diferente!");
//        } else { // login não encontrado, processo de cadastro...
//            String senha, nome = "";
//            int op;
//            System.out.print("Informe sua senha: ");
//            senha = in.nextLine();
//            System.out.println("Deseja inserir um nome na sua conta?");
//            do {
//                System.out.print("Digite 1 - para sim ou 2 - para não: ");
//                op = in.nextInt();
//                in.nextLine();
//                switch (op) {
//                    case 1:
//                        System.out.print("Informe o nome: ");
//                        nome = in.nextLine();
//                        break;
//                    case 2:
//                        nome = "Convidado";
//                        System.out.println(ANSI_GREEN + "UNIKUT - Seu nome foi definido como 'Convidado!'" + ANSI_RESET);
//                        break;
//                    default:
//                        System.err.println("Opção invalida!");
//                }
//
//            } while (op != 1 && op != 2);
        usuarios[this.qtd] = new Usuario(login, "", nome);
        this.qtd++; // usuario cadastrado.
//            System.out.println(ANSI_GREEN + "UNIKUT - Usuário cadastrado!" + ANSI_RESET);
//
//        }
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
            System.out.println("Menu de opções\n" + "1 - Alterar nome\n" + "2 - Alterar senha\n"
                    + "3 - Alterar nome e senha\n" + "4- Voltar ao menu anterior");

            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1:
                    // altera nome
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    user.alterarNome(novoNome);
                    System.out.println(ANSI_GREEN + "Nome alterado com sucesso!" + ANSI_RESET);
                    break;
                case 2:
                    // altera senha
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    user.alterarSenha(novaSenha);
                    System.out.println(ANSI_GREEN + "Senha alterado com sucesso!" + ANSI_RESET);
                    break;
                case 3:
                    // altera nome e senha
                    System.out.println("Digite seu NOVO nome: ");
                    novoNome = in.nextLine();
                    System.out.println("Digite sua NOVA senha: ");
                    novaSenha = in.nextLine();
                    user.alterarNomeSenha(novoNome, novaSenha);
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
        System.out.println("QTdAmigo: " + qtdAmigo);
        int qtdUsuario = buscarUsuario(user);
        System.out.println("QTdUsuario: " + qtdUsuario);
        String senhaParaTestar;
        int opcaoSenhaSecreta = -1;
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        if (resultado == true) {
            System.out.println("=============================");
            System.out.println("Histórico de mensagens:");
            if (usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) == 0 && usuarios[qtdAmigo].getQtdMensagens(qtdUsuario) == 0) {
                System.err.println("Histórico de mensagens vázio!");
            } else {
                while (i < usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) || j < usuarios[qtdAmigo].getQtdMensagens(qtdUsuario)) {
//____________________________________________
                    if (usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) == null) {
                        System.out.println("CASO 1111111111111");
                        //hora
                        System.out.println(ANSI_PURPLE + usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) + ANSI_RESET);
                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, i) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 0) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 2) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 0) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 2) + "   2 2");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 0) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 2) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, i) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 0) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 2) + "      4");
//                        System.out.println(" I = " + i);
                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                            System.out.println("");
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ANSI_RESET);
                            System.out.println("");
                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();
                                System.out.println(senhaParaTestar + "op senhaparatestar");

                                // tentando esse usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) != null
                                // original -> usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i
                                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i).compareTo(senhaParaTestar) == 0) {
                                    System.out.println(ANSI_BLUE
                                            + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET); // codigo q já estava
                                    break;
                                    // mostrando senha pq resultados batem
                                } else {

                                    System.out.println("Senha errada, 1- Tentar novamente ou 2 - Pular mensagem oculta");
                                    opcaoSenhaSecreta = in.nextInt();
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
                                            break;
                                    }

                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            System.out.println(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": "
                                    + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET); // codigo que já estava
                        }

                        i++;
                        //____________________________________________
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) == null) {
                        //hora
                        //resolvido-solicita  a senha apenas para quem recebe
                        System.out.println("CASO 222222222222222222");
                        System.out.println(ANSI_PURPLE + usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) + ANSI_RESET);
                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, i) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 0) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 2) + "  1 1");
//
//                        System.out.println(i);
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 0) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 2) + "   2 2");
//
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 0) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 2) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, i) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 0) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 2) + "      4");
//                        System.out.println(" I = " + i);
// ORIGINAL if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                        // anteriror usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i)

                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) != null);
                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) == null);
                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j) != null) {
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ANSI_RESET);
                            System.out.println("");
                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();
                                System.out.println(senhaParaTestar + "op senhaparatestar");

                                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i).compareTo(senhaParaTestar) == 0) {
                                    System.out.println(ANSI_YELLOW + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET); // codigo q já estava
                                    break;
                                    // mostrando senha pq resultados batem
                                } else {
                                    // tratando exceçao de senha errada
                                    System.out
                                            .println("Senha errada, 1- Tentar novamente ou 2 - Pular mensagem oculta");
                                    opcaoSenhaSecreta = in.nextInt();
                                    in.nextLine();
                                    //while (opcaoSenhaSecreta != 1 || opcaoSenhaSecreta != 2) {
                                    switch (opcaoSenhaSecreta) {
                                        case 1:
                                            System.out.println("Tentando novamente");
                                            break;
                                        case 2:
                                            System.err.println("Mensagem continuará oculta");
                                            break;
                                        default:
                                            System.out.println("Opção inválida, tente novamente");
                                            opcaoSenhaSecreta = in.nextInt();
                                            in.nextLine();

                                    }

                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET);// codigo que já estava
                        }

                        j++;

//____________________________________________
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) < 0) {
                        //hora
                        System.out.println("CASO 333333333333333333");
                        System.out.println(ANSI_PURPLE + usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) + ANSI_RESET);

                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, i) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 0) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 2) + "  1 1");
//
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 0) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 2) + "   2 2");
//
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 0) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 2) + "       3");
//
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, i) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 0) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 2) + "      4");
                        System.out.println(" I = " + i);
                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ANSI_RESET);
                            System.out.print("");
                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();
                                System.out.println(senhaParaTestar + "op senhaparatestar");

                                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i).compareTo(senhaParaTestar) == 0) {
                                    System.out.println(ANSI_BLUE + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET); // codigo q já estava
                                    break;
// mostrando senha pq resultados batem
                                } else {
                                    // tratando exceçao de senha errada
                                    System.out
                                            .println("Senha errada, 1- Tentar novamente ou 2 - Pular mensagem oculta");
                                    opcaoSenhaSecreta = in.nextInt();
                                    in.nextLine();
                                    //while (opcaoSenhaSecreta != 1 || opcaoSenhaSecreta != 2) {
                                    switch (opcaoSenhaSecreta) {
                                        case 1:
                                            System.out.println("Tentando novamente");
                                            break;
                                        case 2:
                                            System.err.println("Mensagem continuará oculta");
                                            break;
                                        default:
                                            System.out.println("Opção inválida, tente novamente");
                                            opcaoSenhaSecreta = in.nextInt();

                                    }

                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            System.out.println(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET);// codigo que já estava
                        }

                        i++;

//____________________________________________                        
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) > 0) {
                        //hora
                        System.out.println("CASO 444444444444444444444");
                        System.out.println(ANSI_PURPLE + usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) + ANSI_RESET);
                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, i) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 0) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdAmigo, 2) + "  1 1");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 0) + "   2 2");
//                        System.out.println(usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, 2) + "   2 2");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 0) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, 2) + "       3");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, i) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 0) + "      4");
//                        System.out.println(usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdUsuario, 2) + "      4");
//                        System.out.println(" I = " + i);

                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ANSI_RESET);
                            System.out.print("");

                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();
                                System.out.println(senhaParaTestar + "op senhaparatestar");

                                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, i).compareTo(senhaParaTestar) == 0) {

                                    System.out.println(ANSI_YELLOW + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET); // codigo q já estava
                                    // mostrando senha pq resultados batem
                                    break;
                                } else {
                                    // tratando exceçao de senha errada
                                    System.out
                                            .println("Senha errada, 1- Tentar novamente ou 2 - Pular mensagem oculta");
                                    opcaoSenhaSecreta = in.nextInt();
                                    in.nextLine();
                                    //while (opcaoSenhaSecreta != 1 || opcaoSenhaSecreta != 2) {
                                    switch (opcaoSenhaSecreta) {
                                        case 1:
                                            System.out.println("Tentando novamente");
                                            break;
                                        case 2:
                                            System.err.println("Mensagem continuará oculta");
                                            break;
                                        default:
                                            System.out.println("Opção inválida, tente novamente");
                                            opcaoSenhaSecreta = in.nextInt();
                                            in.nextLine();

                                    }

                                }

                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);

                        } else { // caso seja null é pq é mensagem normal
                            System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET);// codigo que já estava
                        }

                        j++;
                    }
                }
            }
        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }
    }

    public void enviarMensagem(Usuario user, String amigo, Scanner in) {
        String mensagem, senha;
        int opcaoMensagemAdicionais = -1;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        int opcaoMensagemSecreta;
        String senhaPadrao = usuarios[qtdUsuario].getSenhaPadrao();
        // falta ajeitar
        if (resultado == true) {
            //   do {
            System.out.println("=============================");
            System.out.println("Digite a mensagem:");
            System.out.print("-> ");
            mensagem = in.nextLine();
            System.out.println("mensagem" + mensagem);
            System.out.println("Mensagem seja secreta? 1-Sim  2-Não");
            opcaoMensagemSecreta = in.nextInt();
            in.nextLine();
            System.out.println(opcaoMensagemSecreta);
            System.out.println(opcaoMensagemSecreta + "opMensagem");
            //while (opcaoMensagemSecreta != 1 || opcaoMensagemSecreta != 2) {

            if (opcaoMensagemSecreta == 1) {
                if (senhaPadrao == null) {
                    String auxSenha;
                    System.out.println("Digite a senha da mensagem: ");
                    auxSenha = in.nextLine();
                    usuarios[qtdUsuario].setSenhaPadrao(auxSenha);
                    System.out.println(usuarios[qtdUsuario].getSenhaPadrao() + "variavel senha");
                    usuarios[qtdUsuario].setMensagensSecreta(qtdAmigo, mensagem, usuarios[qtdUsuario].getSenhaPadrao());
                } else {
                    usuarios[qtdUsuario].setMensagensSecreta(qtdAmigo, mensagem, senhaPadrao);
                }
            } else if (opcaoMensagemSecreta == 2) {

                usuarios[qtdUsuario].setMensagens(qtdAmigo, mensagem);
            } else {
                System.err.println("Opção inválida ! 1 - Sim ou 2 - não");
                System.out.print("Digite novamente: ");
                opcaoMensagemSecreta = in.nextInt();
                in.nextLine();
                System.out.println(opcaoMensagemSecreta + "opMensagem");
            }
            //}

//                System.out.println("Deseja enviar outra mensagem? 1-Sim  2-Não");
//                opcaoMensagemAdicionais = in.nextInt();
//                in.nextLine();
//                System.out.println(opcaoMensagemAdicionais + "opc mens adicionais 1111");
//
//                while (opcaoMensagemAdicionais != 1 || opcaoMensagemAdicionais != 2) {
//                    System.err.println("Opção inválida ! 1 - Sim ou 2 - não");
//                    opcaoMensagemAdicionais = in.nextInt();
//                    in.nextLine();
//                    System.out.println(opcaoMensagemAdicionais + "opc mens adicionais 2222");
//                }
//
//            } while (opcaoMensagemAdicionais != 2);
        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }

    }
}
