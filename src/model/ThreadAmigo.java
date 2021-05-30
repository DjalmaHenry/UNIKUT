package model;

import static model.Contas.in;
import static model.ThreadHistoricoMensagens.contAmigo;
import static model.ThreadHistoricoMensagens.contUsuario;

class ThreadAmigo extends Thread implements Runnable {

    private Usuario[] usuarios;
    private int qtdUsuario, qtdAmigo;
    private String nome, mensagemHora, mensagem, senhaParaTestar;
    private int opcaoSenhaSecreta = -1;

    public ThreadAmigo(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) {
        this.usuarios = usuarios;
        this.qtdUsuario = qtdUsuario;
        this.qtdAmigo = qtdAmigo;
    }

    public void run() {
        String nome, mensagemHora, mensagem, senhaParaTestar;
        int opcaoSenhaSecreta = -1;
        try {
            while (contUsuario < usuarios[qtdUsuario].getQtdMensagens(qtdAmigo)) {
                if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario) == null) {  // se for diferente de null é secreta
                    //hora
                    mensagemHora = usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo);
                    controller.Cadastro.enviarMensagemHora(mensagemHora);
                    // mensagem abaixo 
                    if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo) != null) {
                        nome = usuarios[qtdAmigo].getNome();
                        controller.Cadastro.enviarMensagemSecretaAmigo(nome);
                        do {
                            controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                            senhaParaTestar = in.nextLine();
                            if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo).compareTo(senhaParaTestar) == 0) {
                                // senha igual :. exibi mensagem secreta
                                mensagem = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                                controller.Cadastro.enviarMensagemAmigo(mensagem);
                                break;
                            } else {
                                opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                            }
                        } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                    } else { // caso seja null é pq é mensagem normal
                        mensagem = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                        controller.Cadastro.enviarMensagemAmigo(mensagem);
                    }
                    contAmigo++;
                } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo)) > 0) {
                    //hora
                    mensagemHora = usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo);
                    controller.Cadastro.enviarMensagemHora(mensagemHora);
                    // mensagem abaixo 
                    if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo) != null) {  // se for diferente de null é secreta 
                        nome = usuarios[qtdAmigo].getNome();
                        controller.Cadastro.enviarMensagemSecretaAmigo(nome);
                        do {
                            controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                            senhaParaTestar = in.nextLine();
                            if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo).compareTo(senhaParaTestar) == 0) {
                                // senha igual :. exibi mensagem secreta
                                mensagem = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                                controller.Cadastro.enviarMensagemAmigo(mensagem);
                                break;
                            } else {
                                opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                            }
                        } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                    } else { // caso seja null é pq é mensagem normal
                        mensagem = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                        controller.Cadastro.enviarMensagemAmigo(mensagem);
                    }
                    contAmigo++;
                } else {
                    Thread.sleep(50);
                }
            }
            System.out.println("FIM amigo");
        } catch (Exception e) {
            System.err.println("ERRO");
        }
    }
;
}
