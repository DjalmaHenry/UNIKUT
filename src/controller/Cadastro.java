package controller;

import model.Usuario;
import model.Contas;
import model.ContasAdmin;
import static view.Logado.*;

public class Cadastro {

    private Contas contas;

    public Cadastro() {
        contas = new ContasAdmin();
    }

    public void cadastraConta(String login) {
        contas.cadastrarUsuario(login);
    }

    public void cadastraContaAdmin(String login) {
        ContasAdmin aux;
        aux = (ContasAdmin) contas;
        aux.cadastrarUsuarioAdmin(login);
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
}
