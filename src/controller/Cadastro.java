package controller;

import model.Usuario;
import model.Contas;
import model.ContasAdmin;
import view.Exibicao;
import static view.Logado.*;

public class Cadastro {

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

    
    private Contas contas;

    public Cadastro() {
        contas = new ContasAdmin();
    }

    public int buscaUsuario(String login) {
        Usuario userAux = new Usuario(login);
        int achouUsuario;
        achouUsuario = contas.buscarUsuario(userAux);
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

}
