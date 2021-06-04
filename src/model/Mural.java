package model;

import controller.Cadastro;

public class Mural {

    protected String[] autorMural;
    protected String[] mural;
    protected int qtdMural;

    public Mural() {
        autorMural = new String[1000];
        mural = new String[1000];
        this.qtdMural = 0;
    }

    public int buscarUsuario(Usuario[] usuarios, String login) {
        Cadastro cadastro = Cadastro.getInstance();
        Usuario user = new Usuario(login);
        int i = 0;
        if (cadastro.getQtd() == 0) {
            return -1;
        }
        for (i = 0; i < cadastro.getQtd(); i++) {
            if (usuarios[i].compareTo(user) == 0) {
                return i; // retorna posição do usuario no vetor.
            }
        }
        return -1; // login não encontrado.
    }

    public void enviarSolicitacaoMural(Usuario user, Usuario[] usuarios, String amigo) throws Exception {
        Cadastro cadastro = Cadastro.getInstance();
        int qtdAmigo = buscarUsuario(usuarios, amigo);
        int qtdUsuario = buscarUsuario(usuarios, user.getLogin());
        int qtdSolicitacoes = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
        if (qtdAmigo == -1) {
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        } else if (qtdSolicitacoes == 10) {
            throw new Exception("UNIKUT - Erro, caixa de solicitações cheia!!");
        } else {
            cadastro.mensagemMural(usuarios, qtdUsuario, qtdAmigo);
        }
    }

    public void setSolicitacaoMural(Usuario[] usuarios, int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        usuarios[qtdAmigo].setSolicitacaoMural(qtdUsuario, mensagem);
    }

    public void exibeMural() throws Exception {
        Cadastro cadastro = Cadastro.getInstance();
        if (qtdMural == 0) {
            throw new Exception("UNIKUT - Erro, mural vazio!!");
        } else {
            Thread e = new Thread(() -> { // Lambda Expression
                String mensagem;
                for (int i = 0; i != qtdMural; i++) {
                    mensagem = autorMural[i] + ": " + mural[i];
                    cadastro.printarMural(mensagem);
                }
            });
            e.start();
            synchronized (e) {
                e.join(50);
            }
        }
    }

    public void solicitacaoMural(Usuario user, Usuario[] usuarios, String amigo) throws Exception {
        Cadastro cadastro = Cadastro.getInstance();
        String opcao;
        String mensagem, autor, solicitacao;
        int qtdUsuario, qtdSolicitacoes;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(usuarios, amigo);
        qtdUsuario = buscarUsuario(usuarios, user.getLogin());
        if (qtdAmigo != -1) {
            qtdSolicitacoes = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
            if (qtdSolicitacoes == 0) {
                throw new Exception("UNIKUT - Erro, lista de solicitações pendentes para seu mural está vázia!");
            } else {
                for (int i = 0; i != qtdSolicitacoes; i++) {
                    mensagem = usuarios[qtdUsuario].getSolicitacaoMural(qtdAmigo, i);
                    autor = usuarios[qtdAmigo].getNome();
                    solicitacao = autor + ": " + mensagem;
                    opcao = cadastro.printaSolicitacaoMural(solicitacao);
                    if (opcao.charAt(0) == 's' || opcao.charAt(0) == 'S') {
                        mural[qtdMural] = mensagem;
                        autorMural[qtdMural] = autor;
                        qtdMural++;
                        usuarios[qtdUsuario].removeSolicitacaoMural(qtdAmigo, i);
                    } else {
                        usuarios[qtdUsuario].removeSolicitacaoMural(qtdAmigo, i);
                    }
                }
                usuarios[qtdUsuario].setQtdSolicicacoesMural(0, qtdAmigo);
            }
        } else {
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        }
    }
}
