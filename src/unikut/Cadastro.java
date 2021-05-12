package unikut;

import java.util.Scanner;

import static unikut.CoresTerminal.*;

public class Cadastro {

    public static final Scanner in = new Scanner(System.in);
    protected Usuario[] usuarios;
    protected boolean[] admin;
    protected String[] autorMural;
    protected String[] mural;
    protected int qtdMural;
    protected int qtd;

    public Cadastro() {
        usuarios = new Usuario[100];
        admin = new boolean[100];
        autorMural = new String[1000];
        mural = new String[1000];
        this.qtdMural = 0;
        this.qtd = 0;
    }

    public boolean getAdmin(Usuario user) {
        int qtdUsuario = buscarUsuario(user);
        return admin[qtdUsuario];
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

    protected int buscarUsuario(Usuario user) {

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

    public void historicoMensagens(Usuario user, String amigo) {
        int i = 0, j = 0;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
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
                    if (usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) == null) {
                        //hora
                        System.out.println(ANSI_PURPLE + usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) + ANSI_RESET);
                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto

                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                            System.out.println("");
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ANSI_RESET);
                            System.out.println("");
                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();

                                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i).compareTo(senhaParaTestar) == 0) {
                                    System.out.println(ANSI_BLUE
                                            + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET); // codigo q já estava
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
                        System.out.println(ANSI_PURPLE + usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) + ANSI_RESET);
                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j) != null) {
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ANSI_RESET);
                            System.out.println("");
                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();

                                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j).compareTo(senhaParaTestar) == 0) {
                                    System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET); // codigo q já estava
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

                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET);// codigo que já estava
                        }

                        j++;

//____________________________________________
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) < 0) {
                        //hora
                        System.out.println(ANSI_PURPLE + usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) + ANSI_RESET);

                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ANSI_RESET);
                            System.out.print("");
                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();

                                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i).compareTo(senhaParaTestar) == 0) {
                                    System.out.println(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET); // codigo q já estava
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

                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            System.out.println(ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET);// codigo que já estava
                        }

                        i++;

//____________________________________________                        
                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) > 0) {
                        //hora
                        System.out.println(ANSI_PURPLE + usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) + ANSI_RESET);
                        // mensagemV
                        // confirmando se tem senha | caso tenha solicita e verificar | caso nao já
                        // mostra direto
                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j) != null) { // se for diferente de null é pq é secreta, caso não já printa a msg direto
                            System.err.print("[Mens. Secreta]");
                            System.out.print(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ANSI_RESET);
                            System.out.print("");

                            do {
                                System.out.print("A mensagem é secreta! digite a senha: ");
                                senhaParaTestar = in.nextLine();

                                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j).compareTo(senhaParaTestar) == 0) {

                                    System.out.println(ANSI_YELLOW + usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j) + ANSI_RESET); // codigo q já estava
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

        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        int opcaoMensagemSecreta;
        String senhaPadrao = usuarios[qtdUsuario].getSenhaPadrao();
        if (resultado == true) {

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
                    usuarios[qtdUsuario].setSenhaPadrao(auxSenha);
                    usuarios[qtdUsuario].setMensagensSecreta(qtdAmigo, mensagem, usuarios[qtdUsuario].getSenhaPadrao());
                } else {
                    usuarios[qtdUsuario].setMensagensSecreta(qtdAmigo, mensagem, senhaPadrao);
                }
            } else {
                usuarios[qtdUsuario].setMensagens(qtdAmigo, mensagem);
            }

        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }
    }

    public void adicaoAmigos(Usuario user, String amigo) {
        int posicaoUsuario, posicaoAmigo;
        String eu = user.getLogin();
        posicaoUsuario = buscarUsuario(user); // buscar usuario na lista.
        Usuario userAux = new Usuario(amigo); // criação de um usuario para o amigo a ser add.
        posicaoAmigo = buscarUsuario(userAux); // buscar amigo a ser add.
        if (posicaoAmigo == -1) { // amigo não se encontra na lista.
            System.err.println("Erro, este usuário não existe!");
        } else if (user.getLogin().compareTo(amigo) == 0) { // tentativa de adicionar a si mesmo.
            System.err.println("Erro, você não pode adicionar você mesmo!");
        } else {
            // verificação de amigo a ser add, se ja se encontra na lista de pendentes.
            for (int i = 0; i < usuarios[posicaoUsuario].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[posicaoUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já encontra-se na lista de pendentes!");
                    return;
                }
            }

            // verificação de pedido de amizade ja enviado.
            for (int i = 0; i < usuarios[posicaoAmigo].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[posicaoAmigo].getListaAmigosPendentes(i).compareTo(usuarios[posicaoUsuario].getLogin()) == 0) {
                    System.err.println("Erro, pedido de amizade já enviado!");
                    return;
                }
            }
            // verificação de amigo ja exitir na lista de amigos.
            for (int i = 0; i < usuarios[posicaoUsuario].getQtdListaAmigos(); i++) {
                if (usuarios[posicaoUsuario].getListaAmigos(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já encontra-se na lista de amizades!");
                    return;
                }
            }
            //processo de adição de amigo
            usuarios[posicaoAmigo].setListaAmigosPendentes(eu);
            System.out.println(ANSI_GREEN + "UNIKUT - Pedido de amizade enviado com sucesso!" + ANSI_RESET);
            usuarios[posicaoUsuario].executarMatch(amigo);
            adicionarMatch(usuarios[posicaoUsuario], usuarios[posicaoAmigo]);
        }
    }

    private void adicionarMatch(Usuario user, Usuario amigo) {
        Scanner i = new Scanner(System.in);
        char decisao;
        String nomeUser, nomeAmigo;
        nomeUser = user.getNome();
        nomeAmigo = amigo.getNome();
        System.out.print("Voce deseja dar Match nesse usuario? S - para sim ou N - para não: ");
        decisao = i.next().charAt(0);
        decisao = Character.toUpperCase(decisao);
        int posicaoUserMatch = 0;
        int posicaoAmigoMatch = 0;
        if (decisao == 'S') {
            for (int j = 0; j < user.getQtdMatch(); j++) {
                if (user.getnomesMatch(j).equals(nomeAmigo)) {
                    posicaoUserMatch = j;
                    break;
                }
            }
            user.setDecisaoMatch(true, posicaoUserMatch);
            System.out.println(ANSI_GREEN + "UNIKUT - Match salvo como sim para: " + nomeAmigo + ANSI_RESET);
        } else {
            System.err.println("UNIKUT - Voce não deu Match para: " + nomeAmigo);
            user.setDecisaoMatch(false, posicaoUserMatch);
        }

        for (int j = 0; j < amigo.getQtdMatch(); j++) {
            if (amigo.getnomesMatch(j).equals(nomeUser)) {
                posicaoAmigoMatch = j;
                break;
            }
        }
        // verificação se o match nos dois esta esta de acordo.
        if (user.getDecisaoMatch(posicaoUserMatch) == true && amigo.getDecisaoMatch(posicaoAmigoMatch) == true) {
            user.setMatchTotais(nomeAmigo);
            amigo.setMatchTotais(nomeUser);
            System.out.println(ANSI_GREEN + "UNIKUT - Opa, deu Match, entre voce e " + nomeAmigo + ANSI_RESET);
        }
    }

    public void aceitaAmigos(Usuario user) {
        int posicaoUsuario;
        posicaoUsuario = buscarUsuario(user); // buscar usuario na lista.
        if (usuarios[posicaoUsuario].getQtdListaAmigosPendentes() != 0) {
            String amigo;
            System.out.println("UNIKUT - Informe o login do amigo que deseja aceitar:");
            amigo = in.next();
            in.nextLine();
            Usuario userAux = new Usuario(amigo); // criação do usuario apartir da entrada do usuario.
            int posicaoAmigo = buscarUsuario(userAux); // posição
            String eu = user.getLogin();
            boolean achou = false;
            for (int i = 0; i < usuarios[posicaoUsuario].getQtdListaAmigosPendentes(); i++) {
                // condição de buscar de amigo na lista de amigos pendentes.
                if (usuarios[posicaoUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    achou = true;
                }
            }
            if (achou == false) {
                System.err.println("Erro, amigo não encontrado na lista de pendentes!");
            } else { // processo de aceitação de amigo.
                usuarios[posicaoUsuario].setListaAmigos(amigo);
                usuarios[posicaoAmigo].setListaAmigos(eu);
                System.out.println(ANSI_GREEN + "UNIKUT - Pedido aceito com sucesso!" + ANSI_RESET);
                // inicio do processo de match
                usuarios[posicaoUsuario].executarMatch(amigo);
                adicionarMatch(usuarios[posicaoUsuario], usuarios[posicaoAmigo]);
            }
        }
    }

    public void exibirMatch(Usuario user) {
        if (user.getQtdMatchTotais() != 0) {
            for (int i = 0; i < user.getQtdMatchTotais(); i++) {
                System.out.println(ANSI_GREEN + "UNIKUT - Você deu Match com " + user.getMatchTotais(i) + ANSI_RESET);
            }
        } else {
            System.err.println("UNIKUT - Você não tem nenhum Match!");
        }
    }

    public void enviarSolicitacaoMural(Usuario user, String amigo, Scanner in) {
        String mensagem;
        boolean option = true;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
        if (qtdAmigo != -1) {
            while (option != false) {
                System.out.println("=============================");
                System.out.println("Digite a mensagem para o mural:");
                System.out.print("-> ");
                mensagem = in.nextLine();
                usuarios[qtdAmigo].setSolicitacaoMural(qtdUsuario, mensagem);
                System.out.println("Deseja enviar outra mensagem? [true/false]");
                option = in.nextBoolean();
                in.nextLine();
            }
        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }
    }

    public void exibeMural() {
        if (qtdMural == 0) {
            System.err.println("Erro, mural vazio!!");
        } else {
            System.out.println("MURAL:");
            for (int i = 0; i != qtdMural; i++) {
                System.out.println(autorMural[i] + ": " + mural[i]);
            }
        }
    }

    public void solicitacaoMural(Usuario user, String amigo) {
        boolean opcao;
        String mensagem, autor;
        int qtdUsuario, qtdSolicitacoes;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        qtdUsuario = buscarUsuario(user);
        if (qtdAmigo != -1) {
            qtdSolicitacoes = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
            if (qtdSolicitacoes == 0) {
                System.err.println("Lista de solicitações pendentes para seu mural está vázia!");
            } else {
                for (int i = 0; i != qtdSolicitacoes; i++) {
                    mensagem = usuarios[qtdUsuario].getSolicitacaoMural(qtdAmigo, i);
                    autor = usuarios[qtdAmigo].getNome();
                    System.out.println(autor + ": " + mensagem);
                    System.out.println("Deseja adicionar essa mensagem ao mural público? [true/false]");
                    System.out.print("-> ");
                    opcao = in.nextBoolean();
                    in.nextLine();
                    if (opcao) {
                        mural[qtdMural] = mensagem;
                        autorMural[qtdMural] = autor;
                        qtdMural++;
                        usuarios[qtdUsuario].setSolicitacaoMural(qtdAmigo, null);
                    } else {
                        usuarios[qtdUsuario].setSolicitacaoMural(qtdAmigo, null);
                    }
                }
                usuarios[qtdUsuario].setQtdSolicicacoesMural(0, qtdAmigo);
            }
        } else {
            System.err.println("Erro, usuário não está na lista de amizades!");
        }
    }
}
