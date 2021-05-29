package model;

import model.Contas;
import model.Usuario;
import static view.CoresTerminal.*;

public class ContasAdmin extends Contas {

    public ContasAdmin() {
        super();
    }

    public void cadastrarUsuarioAdmin(String login, String senha, String nome) {
        super.usuarios[super.qtd] = new Usuario(login, senha, nome);
        admin[super.qtd] = true;
        super.qtd++; // usuario cadastrado.
    }

    public void excluirConta(String login) throws Exception {
        int qtdUsuario;
        // $$$$$$$$$$
        // Usuario user = new Usuario(login);
        qtdUsuario = buscarUsuario(login);
        if (qtdUsuario == -1) { // login ja encontrado no vetor.
            throw new Exception("UNIKUT - Erro, usuário não cadastrado!");
        } else {
            if (admin[qtdUsuario] == true) {
                throw new Exception("UNIKUT - Erro, você não pode remover um admin!");
            } else {
                for (int i = qtdUsuario; i < super.qtd; i++) {
                    super.usuarios[i] = super.usuarios[i + 1];
                    admin[i] = admin[i + 1];
                }
                super.qtd--;
            }
        }
    }

}
