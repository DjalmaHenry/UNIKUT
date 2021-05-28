package model;

import controller.Cadastro;

public class Mural extends Contas {

    public void enviarSolicitacaoMural(Cadastro cadastro, Usuario user, String amigo) throws Exception {
        int qtdAmigo = buscarUsuario(amigo);
        int qtdUsuario = buscarUsuario(user.getLogin());
        int qtdSolicitacoes = usuarios[qtdUsuario].getQtdSolicicacoesMural(qtdAmigo);
        if (qtdAmigo == -1) {
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        } else if (qtdSolicitacoes == 10) {
            throw new Exception("UNIKUT - Erro, caixa de solicitações cheia!!");
        } else {
            cadastro.mensagemMural(cadastro, qtdUsuario, qtdAmigo);
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        }
    }

    public void setSolicitacaoMural(int qtdUsuario, int qtdAmigo, String mensagem) {
        usuarios[qtdAmigo].setSolicitacaoMural(qtdUsuario, mensagem);
    }

    public void exibeMural(Cadastro cadastro) throws Exception {
        String mensagem;
        if (super.qtdMural == 0) {
            throw new Exception("UNIKUT - Erro, mural vazio!!");
        } else {
            for (int i = 0; i != qtdMural; i++) {
                mensagem = autorMural[i] + ": " + mural[i];
                cadastro.printarMural(mensagem);
            }
        }
    }

    public void solicitacaoMural(Usuario user, String amigo) throws Exception {
        String opcao;
        String mensagem, autor, solicitacao;
        int qtdUsuario, qtdSolicitacoes;
        Usuario amigoA = new Usuario(amigo);
        int qtdAmigo = buscarUsuario(amigo);
        qtdUsuario = buscarUsuario(user.getLogin());
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
                        usuarios[qtdUsuario].setSolicitacaoMural(qtdAmigo, null);
                    } else {
                        usuarios[qtdUsuario].setSolicitacaoMural(qtdAmigo, null);
                    }
                }
                usuarios[qtdUsuario].setQtdSolicicacoesMural(0, qtdAmigo);
            }
        } else {
            throw new Exception("UNIKUT - Erro, usuário não está na lista de amizades!");
        }
    }
}
