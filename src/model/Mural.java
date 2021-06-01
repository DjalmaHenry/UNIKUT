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

    public int buscarUsuario(Usuario[] usuarios, Cadastro cadastro, String login) {
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

    public void enviarSolicitacaoMural(Cadastro cadastro, Usuario user, Usuario[] usuarios, String amigo) throws Exception {
        int qtdAmigo = buscarUsuario(usuarios, cadastro, amigo);
        int qtdUsuario = buscarUsuario(usuarios, cadastro, user.getLogin());
        int qtdSolicitacoes = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
        if (qtdAmigo == -1) {
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        } else if (qtdSolicitacoes == 10) {
            throw new Exception("UNIKUT - Erro, caixa de solicitações cheia!!");
        } else {
            cadastro.mensagemMural(cadastro, usuarios, qtdUsuario, qtdAmigo);
        }
    }

    public void setSolicitacaoMural(Usuario[] usuarios, int qtdUsuario, int qtdAmigo, String mensagem) throws Exception {
        usuarios[qtdAmigo].setSolicitacaoMural(qtdUsuario, mensagem);
    }

    public void exibeMural() throws Exception {
        if (qtdMural == 0) {
            throw new Exception("UNIKUT - Erro, mural vazio!!");
        } else {
            Thread e = new Thread(() -> { // Lambda Expression
                String mensagem;
                for (int i = 0; i != qtdMural; i++) {
                    mensagem = autorMural[i] + ": " + mural[i];
                    Cadastro.printarMural(mensagem);
                }
            });
            e.start();
            synchronized (e) {
                e.join(50);
            }
        }
    }

    public void solicitacaoMural(Usuario user, Usuario[] usuarios, Cadastro cadastro, String amigo) throws Exception {
        String opcao;
        String mensagem, autor, solicitacao;
        int qtdUsuario, qtdSolicitacoes;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(usuarios, cadastro, amigo);
        qtdUsuario = buscarUsuario(usuarios, cadastro, user.getLogin());
        if (qtdAmigo != -1) {
            qtdSolicitacoes = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
            if (qtdSolicitacoes == 0) {
                throw new Exception("UNIKUT - Erro, lista de solicitações pendentes para seu mural está vázia!");
            } else {
                for (int i = 0; i != qtdSolicitacoes; i++) {
                    mensagem = usuarios[qtdUsuario].getSolicitacaoMural(qtdAmigo, i);
                    autor = usuarios[qtdAmigo].getNome();
                    solicitacao = autor + ": " + mensagem;
                    opcao = Cadastro.printaSolicitacaoMural(solicitacao);
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
