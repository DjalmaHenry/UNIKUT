package unikut;

import static unikut.CoresTerminal.*;

public class CadastroAdmin extends Cadastro{
    
    public CadastroAdmin() {
        super();
    }
    
    public void cadastrarUsuarioAdmin(String login) {
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
            System.out.print("Informe o nome: ");
            nome = in.nextLine();
            super.usuarios[super.qtd] = new Usuario(login, senha, nome);
            admin[super.qtd] = true;
            super.qtd++; // usuario cadastrado.
            System.out.println(ANSI_GREEN + "UNIKUT - Usuário admin cadastrado!" + ANSI_RESET);
        }
    }
    
    public void excluiConta(Usuario user) {
        int qtdUsuario;
        qtdUsuario = buscarUsuario(user);
        if (qtdUsuario == -1) { // login ja encontrado no vetor.
            System.err.println("UNIKUT - Usuário não cadastrado!");
        } else {
            if (admin[qtdUsuario] == true) {
                System.err.println("UNIKUT - Você não pode remover um admin!");
            } else {
                for (int i = qtdUsuario; i < super.qtd; i++) {
                    super.usuarios[i] = super.usuarios[i + 1];
                    admin[i] = admin[i + 1];
                }
                super.qtd--;
                System.out.println(ANSI_GREEN + "UNIKUT - Usuário excluido com sucesso!" + ANSI_RESET);
            }
        }
    }

    public Usuario procurarUsuario(String login) {
        int achouUsuario;
        Usuario userAux = new Usuario(login, "", "");
        achouUsuario = buscarUsuario(userAux);
        if (achouUsuario >= 0) {
            return super.usuarios[achouUsuario]; // retorna o usuario.
        } else {
            return null; // login não encontrado.
        }
    }
}