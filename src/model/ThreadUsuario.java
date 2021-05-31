package model;

import static model.Contas.in;
import static model.ThreadHistoricoMensagens.contAmigo;
import static model.ThreadHistoricoMensagens.contUsuario;

class ThreadUsuario implements Runnable {

    private Usuario[] usuarios;
    private int qtdUsuario, qtdAmigo;
    private String nome, mensagemHora, mensagem, senhaParaTestar;
    private int opcaoSenhaSecreta = -1;

    public ThreadUsuario(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) {
        this.usuarios = usuarios;
        this.qtdUsuario = qtdUsuario;
        this.qtdAmigo = qtdAmigo;
    }

    @Override
    public void run() {
        String nome, mensagemHora, mensagem, senhaParaTestar;
        int opcaoSenhaSecreta = -1;
        try {
            while (contUsuario < usuarios[qtdUsuario].getQtdMensagens(qtdAmigo)) {
                if (usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo) == null) {
                    //hora
                    mensagemHora = usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario);
                    controller.Cadastro.enviarMensagemHora(mensagemHora);
                    // mensagem abaixo 
                    if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario) != null) { // se for diferente de null é secreta
                        nome = usuarios[qtdUsuario].getNome();
                        controller.Cadastro.enviarMensagemSecreta(nome);
                        do {
                            controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                            senhaParaTestar = in.nextLine();
                            if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario).compareTo(senhaParaTestar) == 0) {
                                // senha igual :. exibi mensagem secreta
                                mensagem = usuarios[qtdUsuario].getNome() + ": "
                                        + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                                controller.Cadastro.enviarMensagem(mensagem);
                                break;
                            } else {
                                opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                            }
                        } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                    } else { // caso seja null é pq é mensagem normal, logo exibimos
                        mensagem = usuarios[qtdUsuario].getNome() + ": "
                                + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                        controller.Cadastro.enviarMensagem(mensagem);
                    }
                    contUsuario++;
                } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo)) < 0) {
                    //hora
                    mensagemHora = usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario);
                    controller.Cadastro.enviarMensagemHora(mensagemHora);
                    // mensagem abaixo 
                    if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario) != null) {  // se for diferente de null é secreta
                        nome = usuarios[qtdUsuario].getNome();
                        controller.Cadastro.enviarMensagemSecreta(nome);
                        do {
                            controller.Cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                            senhaParaTestar = in.nextLine();

                            if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario).compareTo(senhaParaTestar) == 0) {
                                // senha igual :. exibi mensagem secreta
                                mensagem = usuarios[qtdUsuario].getNome() + ": "
                                        + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                                controller.Cadastro.enviarMensagem(mensagem);
                                break;
                            } else {
                                opcaoSenhaSecreta = controller.Cadastro.tratamentoSenhaErrada();
                            }
                        } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                    } else { // caso seja null é pq é mensagem normal
                        mensagem = usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                        controller.Cadastro.enviarMensagem(mensagem);
                    }
                    contaMais();
                } else {
                    Thread.sleep(50);
                }
            }
            System.out.println("FIM usuario");
        } catch (Exception e) {
        }
    }
;
    
    public synchronized static void contaMais(){
        contUsuario++;
    }
}
