package model;

import controller.Cadastro;
import java.util.Scanner;
import static view.CoresTerminal.*;

public class Contas {
    
    public static final Scanner in = new Scanner(System.in);
    protected Usuario[] usuarios;
    protected boolean[] admin;
    protected Mural mural;
    protected int qtd;
    
    public Contas() {
        usuarios = new Usuario[100];
        admin = new boolean[100];
        mural = new Mural();
    }
    
    public boolean getAdmin(String user) { // $$$$$$$$$$$$
        int qtdUsuario = buscarUsuario(user);
        return admin[qtdUsuario];
    }
    
    public int getQtd() { // $$$$$$$$$$$$
        return qtd;
    }
    
    protected Usuario[] getUsuarios() {
        return usuarios;
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
        // $$$$$$$$$$$$     Usuario userAux = new Usuario(login);
        achouUsuario = buscarUsuario(login); // $$$$$$$$$$$$
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
    
    public Usuario procurarUsuario(String login) {
        int achouUsuario;
        // $$$$$$$$$$$$     Usuario userAux = new Usuario(login);
        achouUsuario = buscarUsuario(login); // $$$$$$$$$$$$
        return usuarios[achouUsuario];
    }
    
    public void exibeListaAmigosPendentes(Usuario user) throws Exception {
        int qtdUsuario;
        String mensagem;
        qtdUsuario = buscarUsuario(user.getLogin());
        if (usuarios[qtdUsuario].getQtdListaAmigosPendentes() == 0) {
            throw new Exception("UNIKUT ERRO - Lista de amigos pendentesn vázia!");
        } else {
            for (int i = 0; i < usuarios[qtdUsuario].getQtdListaAmigosPendentes(); i++) {
                mensagem = (usuarios[qtdUsuario].getListaAmigosPendentes(i));
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
    
    public void historicoMensagens(Usuario user, String amigoA) throws Exception {
        int i = 0, j = 0;
        // $$$$$$$$$$$$ Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigoA);
        int qtdUsuario = buscarUsuario(user.getLogin()); // $$$$$$$$$$$$ user -> user.getNome
        String senhaParaTestar;
        int opcaoSenhaSecreta = -1;
        boolean resultado = usuarios[qtdUsuario].buscaAmigo(amigoA);
        String mensagemHora, mensagem, mensagemAmigo, mensagemSecreta, nome;
        if (resultado == true) {// true = são amigos;
            if (usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) == 0 && usuarios[qtdAmigo].getQtdMensagens(qtdUsuario) == 0) {
                throw new Exception("Histórico de mensagens vázio!"); // tratando ambas mensagens vazias
            } else {
                HistoricoMensagens historico = new HistoricoMensagens();
                historico.executa(usuarios, qtdUsuario, qtdAmigo);
            }
        } else { // não são amigos, throws Exception
            throw new Exception("Erro, usuário não está na lista de amizades!");
        }
    }
    
    public void adicaoAmigos(Usuario user, String amigo) throws Exception {
        int posicaoUsuario, posicaoAmigo;
        String eu = user.getLogin();
        posicaoUsuario = buscarUsuario(user.getLogin()); // buscar usuario na lista.
        // $$$$$$$$$$$$
        // retirei o Usuario userAux = new Usuario(amigo); // criação de um usuario para o amigo a ser add.
        // $$$$$$$$$$$$ e foi passado como parametro aqui
        posicaoAmigo = buscarUsuario(amigo); // buscar amigo a ser add.
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
        String nomeUser, nomeAmigo, resultado;
        nomeUser = user.getNome();
        nomeAmigo = amigo.getNome();
        decisao = Cadastro.perguntaMatch();
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
            resultado = "UNIKUT - Match salvo como sim para: " + nomeAmigo;
            Cadastro.resultadoMatch(resultado);
        } else {
            resultado = "UNIKUT - Voce não deu Match para: " + nomeAmigo;
            Cadastro.resultadoMatch(resultado);
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
            resultado = "UNIKUT - Opa, deu Match, entre voce e " + nomeAmigo;
            Cadastro.resultadoMatch(resultado);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public void aceitaAmigos(Usuario user) throws Exception {
        int posicaoUsuario;
        // $$$$$$$$$$$$
        posicaoUsuario = buscarUsuario(user.getLogin()); // buscar usuario na lista.
        if (usuarios[posicaoUsuario].getQtdListaAmigosPendentes() != 0) {
            String amigo = Cadastro.informaLogin();
            // $$$$$$$$$$$$
            // refatorando par auma busca direta de Usuario 
            //userAux = new Usuario(amigo); // criação do usuario apartir da entrada do usuario.
            // $$$$$$$$$$$$

            int posicaoAmigo = buscarUsuario(amigo); // posição
            String eu = user.getLogin();
            boolean achou = false;
            for (int i = 0; i < usuarios[posicaoUsuario].getQtdListaAmigosPendentes(); i++) {
                // condição de buscar de amigo na lista de amigos pendentes.
                if (usuarios[posicaoUsuario].getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    achou = true;
                }
            }
            if (achou == false) {
                throw new Exception("UNIKUT - Erro, amigo não encontrado na lista de pendentes!");
            } else { // processo de aceitação de amigo.
                usuarios[posicaoUsuario].setListaAmigos(amigo);
                usuarios[posicaoAmigo].setListaAmigos(eu);
                // inicio do processo de match
                usuarios[posicaoUsuario].executarMatch(amigo);
                adicionarMatch(usuarios[posicaoUsuario], usuarios[posicaoAmigo]);
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////

    public void exibirMatch(Usuario user) throws Exception {
        if (user.getQtdMatchTotais() != 0) {
            for (int i = 0; i < user.getQtdMatchTotais(); i++) {
                String match = user.getMatchTotais(i);
                Cadastro.mostraMatch(match);
            }
        } else {
            throw new Exception("UNIKUT ERRO- Você não tem nenhum Match!");
        }
    }
    
    public void enviarSolicitacaoMural(Cadastro cadastro, Usuario user, String amigo) throws Exception {
        mural.enviarSolicitacaoMural(cadastro, user, usuarios, amigo);
    }
    
    public void setSolicitacaoMural(Usuario[] usuarios, int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        mural.setSolicitacaoMural(usuarios, qtdUsuario, qtdAmigo, mensagem);
    }
    
    public void solicitacaoMural(Usuario user, Cadastro cadastro, String amigo) throws Exception {
        mural.solicitacaoMural(user, usuarios, cadastro, amigo);
    }
    
    public void exibirMural() throws Exception {
        mural.exibeMural();
    }
}
