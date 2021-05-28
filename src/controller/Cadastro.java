package controller;

import model.Usuario;
import model.Contas;
import model.ContasAdmin;
import model.Mural;
import view.Exibicao;
import view.Logado;
import static view.Logado.*;

public class Cadastro {

    private Contas contas;
    private Mural mural;

    public Cadastro() {
        contas = new ContasAdmin();
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

    public void logaConta(String login, String senha, Cadastro cadastro) throws Exception {
        Usuario user = contas.procurarUsuario(login, senha);
        if (user != null) {
            boolean admin = contas.getAdmin(user);
            if (admin == true) {
                logadoAdmin(user, cadastro);
            } else {
                logado(user, cadastro);
            }
        } else {
            throw new Exception("UNIKUT - Login ou senha incorretos!");
        }
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

    public void pedidosAmizades(Usuario user) {
        contas.exibeListaAmigosPendentes(user);
        contas.aceitaAmigos(user);
    }

    public void listaAmizades(Usuario user) {
        contas.exibeListaAmigos(user);
    }

    public void excluiConta(String login) throws Exception {
        ContasAdmin aux;
        aux = (ContasAdmin) contas;
        aux.excluirConta(login);
        contas = (Contas) aux;
    }
    
    public void printarMural(String mensagem){
        Exibicao.printaMural(mensagem);
    }
    
    public void exibirMural(Cadastro cadastro) throws Exception {
        mural.exibeMural(cadastro);
    }
    
    public void enviaSolicitacaoMural(Cadastro cadastro, Usuario user, String amigo) throws Exception{
        mural.enviarSolicitacaoMural(cadastro, user, amigo);
    }

    public void mensagemMural(Cadastro cadastro, int qtdUsuario, int qtdAmigo){
        Exibicao.mensagemMural(cadastro, qtdUsuario, qtdAmigo);
    }
    
    public void setSolicitacaoMural(int qtdUsuario, int qtdAmigo,String mensagem){
        mural.setSolicitacaoMural(qtdUsuario, qtdAmigo, mensagem);
    }
    
    public static String printaSolicitacaoMural(String solicitacao){
        String opcao = Exibicao.printaSolicitacaoMural(solicitacao);
        return opcao;
    }
}
