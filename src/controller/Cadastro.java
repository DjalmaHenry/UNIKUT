package controller;

import java.util.Scanner;
import model.Contas;
import model.ContasAdmin;
import model.Usuario;
import view.Exibicao;
import static view.Logado.logado;
import static view.LogadoAdmin.logadoAdmin;

public class Cadastro {

    private static Cadastro instancia;
    private Contas contas;

    private Cadastro() {
        contas = new ContasAdmin();
    }

    public static synchronized Cadastro getInstance() {
        if (instancia == null) {
            instancia = new Cadastro();
        }
        return instancia;
    }
    
       public static int tratamentoSenhaErrada() {
        int tratamentoSenha = Exibicao.tratamentoSenha();
        return tratamentoSenha;
    }

    public static void solicitacaoSecretaMensagem() {
        Exibicao.solicitacaoSecretaMensagem();
    }

    public static void enviarMensagemSecreta(String nome) {
        Exibicao.mensagemSecreta(nome);
    }

    public static void enviarMensagemSecretaAmigo(String nome) {
        Exibicao.mensagemSecretaAmigo(nome);
    }

    public static void enviarMensagemAmigo(String mensagem) {
        Exibicao.mensagemAmigo(mensagem);
    }

    public void historicoMensagens(Usuario user, String amigo) throws Exception {
        contas.historicoMensagens(user, amigo);
    }

    public static void enviarMensagem(String mensagem) {
        Exibicao.mensagem(mensagem);
    }

    public static void enviarMensagemHora(String mensagem) {
        Exibicao.mensagemHora(mensagem);
    }

    public int buscaUsuario(String login) {
        int achouUsuario = contas.buscarUsuario(login);
        return achouUsuario;
    }

    public void cadastraConta(String login, String senha, String nome) {
        contas.cadastrarUsuario(login, senha, nome);
    }

    public void cadastraContaAdmin(String login, String senha, String nome) {
        ContasAdmin aux;
        aux = (ContasAdmin) contas;
        aux.cadastrarUsuarioAdmin(login, senha, nome);
        contas = (Contas) aux;
    }

    public void logaConta(String login, String senha) throws Exception {
        Usuario user = contas.procurarUsuario(login, senha);
        if (user != null) {
            boolean admin = contas.getAdmin(user.getLogin());
            if (admin == true) {
                logadoAdmin(user);
            } else {
                logado(user);
            }
        } else {
            throw new Exception("UNIKUT - Login ou senha incorretos!");
        }
    }

    public Usuario procurarUsuario(String login) {
        Usuario user = contas.procurarUsuario(login);
        return user;
    }

    public void alteraNome(Usuario user, String nome) {
        user.alterarNome(nome);
    }

    public void alteraSenha(Usuario user, String senha) {
        user.alterarSenha(senha);
    }

    public void alteraDados(Usuario user, String nome, String senha) {
        user.alterarNomeSenha(nome, senha);
    }

    public void adicaoAmigos(Usuario user, String amigo) throws Exception {
        contas.adicaoAmigos(user, amigo);
    }

    public void pedidosAmizades(Usuario user) throws Exception {
        contas.exibeListaAmigosPendentes(user);
        contas.aceitaAmigos(user);
    }

    public static String informaLogin() {
        String amigo = Exibicao.informaLogin();
        return amigo;
    }

    public static void exibirAmigosPendentes(String amigosPendentes) {
        Exibicao.exibirAmigosPendentes(amigosPendentes);
    }

    public void listaAmizades(Usuario user) throws Exception {
        contas.exibeListaAmigos(user);

    }

    public static void exibirAmigos(String amigo) {
        Exibicao.exibirAmigos(amigo);
    }

    public void excluiConta(String login) throws Exception {
        ContasAdmin aux;
        aux = (ContasAdmin) contas;
        aux.excluirConta(login);
        contas = (Contas) aux;
    }

    public static void printarMural(String mensagem) {
        Exibicao.printaMural(mensagem);
    }

    public void exibirMural() throws Exception {
        contas.exibirMural();
    }

    public void solicitacaoMural(Usuario user, String amigo) throws Exception {
        contas.solicitacaoMural(user, amigo);
    }

    public void enviaSolicitacaoMural(Usuario user, String amigo) throws Exception {
        contas.enviarSolicitacaoMural(user, amigo);
    }

    public void mensagemMural(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) {
        Exibicao.mensagemMural(usuarios, qtdUsuario, qtdAmigo);
    }

    public int getQtdSolicicacoesMural(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) {
        int qtd = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
        return qtd;
    }

    public void setSolicitacaoMural(Usuario[] usuarios, int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        contas.setSolicitacaoMural(usuarios, qtdUsuario, qtdAmigo, mensagem);
    }

    public static String printaSolicitacaoMural(String solicitacao) {
        String opcao = Exibicao.printaSolicitacaoMural(solicitacao);
        return opcao;
    }

    public void exibirMatch(Usuario user) throws Exception {
        contas.exibirMatch(user);
    }

    public static void mostraMatch(String match) {
        Exibicao.exibirMatch(match);
    }

    public static void resultadoMatch(String resultado) {
        Exibicao.resultadoMatch(resultado);
    }

    public static char perguntaMatch() {
        char decisao = Exibicao.perguntaMatch();
        return decisao;
    }

    public void enviarMensagem(Usuario user, String amigo, Scanner in) throws Exception {
        contas.enviarMensagem(user, amigo, in);
    }

    public static void exibirMensagem(boolean resul, int qtdAmigo, int qtdUsuario, String senhaPadrao) throws Exception {
        Exibicao.exibirMsg(resul, qtdAmigo, qtdUsuario, senhaPadrao);
    }

    public void setMsgSecreta(int qtdUsuario, String auxSenha) {
        contas.setMsgSecreta(qtdUsuario, auxSenha);
    }

    public void setMensagensSecretaPadrao(int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        contas.setMensagensSecretaPadrao(qtdUsuario, qtdAmigo, mensagem);
    }

    public void setMensagensSecreta(int qtdUsuario, int qtdAmigo, String mensagem, String senhaPadrao) throws Exception {
        contas.setMensagensSecreta(qtdUsuario, qtdAmigo, mensagem, senhaPadrao);
    }

    public void setMensagem(int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        contas.setMensagem(qtdUsuario, qtdAmigo, mensagem);
    }

    public int getQtd() { // $$$$$$$$$$$$
        return contas.getQtd();
    }
}
