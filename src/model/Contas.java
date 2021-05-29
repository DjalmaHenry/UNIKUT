package model;

import controller.Cadastro;
import model.Usuario;
import java.util.Scanner;
import controller.Cadastro;

import static view.CoresTerminal.*;

public class Contas {

    public static final Scanner in = new Scanner(System.in);
    protected Usuario[] usuarios;
    protected boolean[] admin;
    protected String[] autorMural;
    protected String[] mural;
    protected int qtdMural;
    protected int qtd;

    public Contas() {
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

    public void cadastrarUsuario(String login, String senha, String nome) {
        usuarios[this.qtd] = new Usuario(login, senha, nome);
        this.qtd++; // usuario cadastrado.
    }

    public int buscarUsuario(String login) {
        Usuario user = new Usuario(login);
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
        Usuario userAux = new Usuario(login);
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

    ///////////////////////////////////////////////////////////////////////////
    public void exibeListaAmigosPendentes(Usuario user) throws Exception {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(user.getLogin());
        if (usuarios[qtdUsuario].getQtdListaAmigosPendentes() == 0) {
            System.err.println("Lista de amigos pendentesn vázia!");
            throw new Exception("UNIKUT ERRO - Lista de amigos pendentesn vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                System.out.println(usuarios[qtdUsuario].getListaAmigosPendentes(i));
                String amigoPendente = usuarios[qtdUsuario].getListaAmigosPendentes(i);
                Cadastro.exibirAmigosPendentes(amigoPendente);
            }
        }
    }

    public void exibeListaAmigos(Usuario user) throws Exception {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(user.getLogin());
        if (usuarios[qtdUsuario].getQtdListaAmigos() == 0) {
            throw new Exception("ERRO - Lista de amigos vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigos(); i++) {
                String amigo = usuarios[qtdUsuario].getListaAmigos(i);
                Cadastro.exibirAmigos(amigo);
            }
        }
    }


    public void enviarMensagem(Usuario user, String amigo, Scanner in, Cadastro cadastro) throws Exception {
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA.getLogin());
        int qtdUsuario = buscarUsuario(user.getLogin());
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        String senhaPadrao = usuarios[qtdUsuario].getSenhaPadrao();
        Cadastro.exibirMensagem(resultado, qtdAmigo, qtdUsuario, senhaPadrao, cadastro);
    }

    public void setMsgSecreta(int qtdUsuario, String auxSenha) {
        usuarios[qtdUsuario].setSenhaPadrao(auxSenha);
    }

    public void setMensagensSecretaPadrao(int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        usuarios[qtdUsuario].setMensagensSecreta(qtdAmigo, mensagem, usuarios[qtdUsuario].getSenhaPadrao());
    }

    public void setMensagensSecreta(int qtdUsuario, int qtdAmigo, String mensagem, String senhaPadrao) throws Exception {
        usuarios[qtdUsuario].setMensagensSecreta(qtdAmigo, mensagem, senhaPadrao);
    }

    public void setMensagem(int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        usuarios[qtdUsuario].setMensagens(qtdAmigo, mensagem);
    }

    ///////////////////////////////////////////////////////////////////////////
    public void historicoMensagens(Usuario user, String amigo) throws Exception {
        int i = 0, j = 0;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user);
        String senhaParaTestar;
        int opcaoSenhaSecreta = -1;
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigo);
        String mensagemHora, mensagem, mensagemAmigo, mensagemSecreta, nome;
        if (resultado == true) {// true = são amigos;
            if (usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) == 0 && usuarios[qtdAmigo].getQtdMensagens(qtdUsuario) == 0) {
                throw new Exception("Histórico de mensagens vázio!"); // tratando ambas mensagens vazias
            } else {
                while (i < usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) || j < usuarios[qtdAmigo].getQtdMensagens(qtdUsuario)) {
                    if (usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j) == null) {
                        //hora
                        mensagemHora = usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i);
                        controller.Cadastro.enviarMensagemHora(mensagemHora);
                        // mensagem abaixo 
                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) { // se for diferente de null é secreta
                            nome = usuarios[qtdUsuario].getNome();
                            controller.Cadastro.enviarMensagemSecreta(nome);
                            do {
                                controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                                senhaParaTestar = in.nextLine();
                                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i).compareTo(senhaParaTestar) == 0) {
                                    // senha igual :. exibi mensagem secreta
                                    mensagem = usuarios[qtdUsuario].getNome() + ": "
                                            + usuarios[qtdUsuario].getMensagem(qtdAmigo, i);
                                    controller.Cadastro.enviarMensagem(mensagem);
                                    break;
                                } else {
                                    opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal, logo exibimos
                            mensagem = usuarios[qtdUsuario].getNome() + ": "
                                    + usuarios[qtdUsuario].getMensagem(qtdAmigo, i);
                            controller.Cadastro.enviarMensagem(mensagem);
                        }
                        i++;

                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i) == null) {  // se for diferente de null é secreta
                        //hora
                        mensagemHora = usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j);
                        controller.Cadastro.enviarMensagemHora(mensagemHora);
                        // mensagem abaixo 
                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j) != null) {
                            nome = usuarios[qtdAmigo].getNome();
                            controller.Cadastro.enviarMensagemSecretaAmigo(nome);
                            do {
                                controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                                senhaParaTestar = in.nextLine();

                                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j).compareTo(senhaParaTestar) == 0) {
                                    // senha igual :. exibi mensagem secreta
                                    mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j);
                                    controller.Cadastro.enviarMensagemAmigo(mensagemAmigo);
                                    break;

                                } else {
                                    opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j);
                            controller.Cadastro.enviarMensagemAmigo(mensagemAmigo);
                        }
                        j++;

                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) < 0) {
                        //hora
                        mensagemHora = usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i);
                        controller.Cadastro.enviarMensagemHora(mensagemHora);
                        // mensagem abaixo 
                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i) != null) {  // se for diferente de null é secreta
                            nome = usuarios[qtdUsuario].getNome();
                            controller.Cadastro.enviarMensagemSecreta(nome);
                            do {
                                controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                                senhaParaTestar = in.nextLine();

                                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, i).compareTo(senhaParaTestar) == 0) {
                                    // senha igual :. exibi mensagem secreta
                                    mensagem = usuarios[qtdUsuario].getNome() + ": "
                                            + usuarios[qtdUsuario].getMensagem(qtdAmigo, i);
                                    controller.Cadastro.enviarMensagem(mensagem);
                                    break;
                                } else {
                                    opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            mensagem = ANSI_BLUE + usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, i) + ANSI_RESET;
                            controller.Cadastro.enviarMensagem(mensagem);
                        }
                        i++;

                    } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, i).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j)) > 0) {
                        //hora
                        mensagemHora = usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, j);
                        controller.Cadastro.enviarMensagemHora(mensagemHora);
                        // mensagem abaixo 
                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j) != null) {  // se for diferente de null é secreta 
                            nome = usuarios[qtdAmigo].getNome();
                            controller.Cadastro.enviarMensagemSecretaAmigo(nome);
                            do {
                                controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                                senhaParaTestar = in.nextLine();
                                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, j).compareTo(senhaParaTestar) == 0) {
                                    // senha igual :. exibi mensagem secreta
                                    mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j);
                                    controller.Cadastro.enviarMensagemAmigo(mensagemAmigo);
                                    break;
                                } else {
                                    opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                                }
                            } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                        } else { // caso seja null é pq é mensagem normal
                            mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, j);
                            controller.Cadastro.enviarMensagemAmigo(mensagemAmigo);
                        }
                        j++;
                    }
                }
            }
        } else { // não são amigos, throws Exception
            throw new Exception("Erro, usuário não está na lista de amizades!");
        }
    }

    public void adicaoAmigos(Usuario user, String amigo) throws Exception {
        int posicaoUsuario, posicaoAmigo;
        String eu = user.getLogin();
        posicaoUsuario = buscarUsuario(user); // buscar usuario na lista.
        Usuario userAux = new Usuario(amigo); // criação de um usuario para o amigo a ser add.
        posicaoAmigo = buscarUsuario(userAux); // buscar amigo a ser add.
        if (posicaoAmigo == -1) { // amigo não se encontra na lista.
            throw new Exception("UNIKUT - Erro, este usuário não existe!");
        } else if (user.getLogin().compareTo(amigo) == 0) { // tentativa de adicionar a si mesmo.
            throw new Exception("UNIKUT - Erro, você não pode adicionar você mesmo!");
        } else {
            // verificação de amigo a ser add, se ja se encontra na lista de pendentes.
            for (int i = 0; i < usuarios[posicaoUsuario].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[posicaoUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    throw new Exception("UNIKUT - Erro, este usuário já encontra-se na lista de pendentes!");
                }
            }

            // verificação de pedido de amizade ja enviado.
            for (int i = 0; i < usuarios[posicaoAmigo].getQtdListaAmigosPendentes(); i++) {
                if (usuarios[posicaoAmigo].getListaAmigosPendentes(i).compareTo(usuarios[posicaoUsuario].getLogin()) == 0) {
                    throw new Exception("UNIKUT - Erro, pedido de amizade já enviado!");
                }
            }
            // verificação de amigo ja exitir na lista de amigos.
            for (int i = 0; i < usuarios[posicaoUsuario].getQtdListaAmigos(); i++) {
                if (usuarios[posicaoUsuario].getListaAmigos(i).compareTo(amigo) == 0) {
                    throw new Exception("UNIKUT - Erro, este usuário já encontra-se na lista de amizades!");
                }
            }
            //processo de adição de amigo
            usuarios[posicaoAmigo].setListaAmigosPendentes(eu);
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
//////////////////////////////////////////////////////////////////////////////

    public void exibirMatch(Usuario user) throws Exception {
        if (user.getQtdMatchTotais() != 0) {

            for (int i = 0; i < user.getQtdMatchTotais(); i++) {

                String match = user.getMatchTotais(i);
                Cadastro.exibirMatch(match);
            }
        } else {
            throw new Exception("UNIKUT ERRO- Você não tem nenhum Match!");
        }
    }

///////////////////////////////////////////////////////////////////////////////
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
