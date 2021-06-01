package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static view.CoresTerminal.*;

public class Usuario implements Comparable<Usuario> {

    private final String login;
    private String senha;
    private String nome;
    private String[] listaAmigos;
    private String[] listaAmigosPendentes;
    private int qtdListaAmigos;
    private int qtdListaAmigosPendentes;
    private String[][] mensagens;
    private String[][] solicitacaoMural; // solicitação mural
    private String[][] senhaMensagemSecreta;
    private String senhaPadrao;
    private boolean[] decisaoMatch; // variavel de armazenamento da decisao de match.
    private int qtdMatch; // guarda a posicao do nome e decisao de cada match do usuario.
    private String[] nomesMatch; // variavel de armazenagem, dos nomes do Match dos usuarios.
    private String[] matchTotais; // variavel de armazenagem dos Match aceitos.
    private int qtdMatchTotais; // variavel de armazenagem da quantidade de Match
    private int[] qtdMensagens;
    private int[] qtdSolicicacoesMural;
    private String[][] horaMensagens;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.listaAmigos = new String[100];
        this.listaAmigosPendentes = new String[100];
        this.mensagens = new String[100][1000];
        this.solicitacaoMural = new String[100][10];
        this.senhaMensagemSecreta = new String[100][1000];
        this.qtdMensagens = new int[100];
        this.qtdSolicicacoesMural = new int[100];
        this.horaMensagens = new String[100][1000];
        this.decisaoMatch = new boolean[100];
        this.nomesMatch = new String[100];
        this.matchTotais = new String[100];
    }

    public Usuario(String login) {
        this.login = login;
    }

    public int getQtdSolicicacoesMural(int amigo) {
        return qtdSolicicacoesMural[amigo];
    }

    public void setQtdSolicicacoesMural(int qtd, int amigo) {
        this.qtdSolicicacoesMural[amigo] = qtd;
    }

    public String getSenhaPadrao() {
        return senhaPadrao;
    }

    public void setSenhaPadrao(String senhaPadrao) {
        this.senhaPadrao = senhaPadrao;
    }

    public boolean buscaAmigo(String amigo) {
        if (this.qtdListaAmigos == 0) {
            return false;
        } else {
            for (int i = 0; i < this.qtdListaAmigos; i++) {
                if (listaAmigos[i].compareTo(amigo) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getMensagem(int amigo, int mensagem) {
        return mensagens[amigo][mensagem];
    }

    public String getSenhaMensagemSecreta(int amigo, int mensagem) {
//   original       return senhaMensagemSecreta[amigo][this.qtdMensagens[amigo]];
        return senhaMensagemSecreta[amigo][mensagem];

    }

    public String getSolicitacaoMural(int amigo, int mensagem) {
        return solicitacaoMural[amigo][mensagem];
    }

    public void setMensagens(int amigo, String mensagem) throws Exception {
        if (this.qtdMensagens[amigo] == 100) {
            throw new Exception("UNIKUT - Erro, mensagem NÃO enviada, memória cheia!!!");
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String hora = (dtf.format(LocalDateTime.now()));
            this.mensagens[amigo][this.qtdMensagens[amigo]] = mensagem;
            this.horaMensagens[amigo][this.qtdMensagens[amigo]] = hora;
            // System.out.println(horaMensagens[amigo][this.qtdMensagens[amigo]]);
            this.qtdMensagens[amigo]++;
        }
    }

    public void setMensagensSecreta(int amigo, String mensagem, String senha) throws Exception {
        if (this.qtdMensagens[amigo] == 100) {
            throw new Exception("UNIKUT - Erro, mensagem NÃO enviada, memória cheia!!!");
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String hora = (dtf.format(LocalDateTime.now()));
            this.mensagens[amigo][this.qtdMensagens[amigo]] = mensagem;
            this.horaMensagens[amigo][this.qtdMensagens[amigo]] = hora;
            this.senhaMensagemSecreta[amigo][this.qtdMensagens[amigo]] = senha;
            this.qtdMensagens[amigo]++;
        }
    }

    public void removeSolicitacaoMural(int amigo, int posicao) throws Exception {
        this.solicitacaoMural[amigo][posicao] = null;
    }

    public void setSolicitacaoMural(int amigo, String mensagem) throws Exception {
        if (this.qtdSolicicacoesMural[amigo] == 10) {
            throw new Exception("UNIKUT - Erro, mensagem NÃO enviada, memória cheia!!!");
        } else {
            this.solicitacaoMural[amigo][this.qtdSolicicacoesMural[amigo]] = mensagem;
            this.qtdSolicicacoesMural[amigo]++;
        }
    }

    public String getHoraMensagens(int amigo, int mensagem) {
        return horaMensagens[amigo][mensagem];
    }

    public int getQtdMensagens(int pessoa) {
        return qtdMensagens[pessoa];
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdListaAmigos() {
        return this.qtdListaAmigos;
    }

    public int getQtdListaAmigosPendentes() {
        return this.qtdListaAmigosPendentes;
    }

    public String getListaAmigos(int amigo) {
        return this.listaAmigos[amigo];
    }

    public void setListaAmigos(String login) {
        listaAmigos[qtdListaAmigos] = login;
        qtdListaAmigos++;
        for (int i = 0; i < qtdListaAmigosPendentes; i++) {
            if (listaAmigosPendentes[i].compareTo(login) == 0) {
                for (; i < qtdListaAmigosPendentes; i++) {
                    listaAmigosPendentes[i] = listaAmigosPendentes[i + 1];
                }
                break;
            }
        }
        qtdListaAmigosPendentes--;
    }

    public String getListaAmigosPendentes(int amigo) {
        return this.listaAmigosPendentes[amigo];
    }

    public void setListaAmigosPendentes(String login) {
        listaAmigosPendentes[qtdListaAmigosPendentes] = login;
        qtdListaAmigosPendentes++;
    }

    public void alterarNome(String n) {
        this.nome = n;
    }

    public void alterarSenha(String s) {
        this.senha = s;
    }

    public void alterarNomeSenha(String n, String s) {
        setNome(n);
        setSenha(s);
    }

    public int getQtdMatchTotais() {
        return this.qtdMatchTotais;
    }

    public void setMatchTotais(String nome) {
        this.matchTotais[this.qtdMatchTotais] = nome;
        this.qtdMatchTotais++;
    }

    public String getMatchTotais(int pos) {
        return this.matchTotais[pos];
    }

    public boolean getDecisaoMatch(int posicao) {
        return this.decisaoMatch[posicao];
    }

    public int getQtdMatch() {
        return this.qtdMatch;
    }

    public void setDecisaoMatch(boolean valor, int posicao) {
        this.decisaoMatch[posicao] = valor;
    }

    public void setnomesMatch(String nome, int posicao) {
        this.nomesMatch[posicao] = nome;
    }

    public String getnomesMatch(int posicao) {
        return this.nomesMatch[posicao];
    }

    public void executarMatch(String login) {
        this.setDecisaoMatch(false, qtdMatch);
        this.setnomesMatch(login, qtdMatch);
        qtdMatch++;
    }

    @Override
    public String toString() {
        String nome, login, dados;
        nome = "Nome: " + this.getNome() + "\n";
        login = "Login: " + this.getLogin() + "\n";
        dados = nome + login;
        return dados;
    }

    @Override
    public int compareTo(Usuario u) {
        return this.login.compareTo(u.login);
    }

}
