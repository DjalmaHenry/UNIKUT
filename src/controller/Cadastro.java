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
    
    public void alteraDados(Usuario user){
        contas.alterarDados(user);
    }
}
