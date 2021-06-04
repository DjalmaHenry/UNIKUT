package model;

import controller.Cadastro;
import static model.Contas.in;

public class HistoricoMensagens {

    private String nome, mensagemHora, mensagem, mensagemAmigo, senhaParaTestar;
    private int contUsuario = 0, contAmigo = 0;
    private int opcaoSenhaSecreta = -1;

    public void executa(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) {
        Cadastro cadastro = Cadastro.getInstance();
        while (contUsuario < usuarios[qtdUsuario].getQtdMensagens(qtdAmigo) || contAmigo < usuarios[qtdAmigo].getQtdMensagens(qtdUsuario)) {
            if (usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo) == null) {
                //hora
                mensagemHora = usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario);
                cadastro.enviarMensagemHora(mensagemHora);
                // mensagem abaixo 
                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario) != null) { // se for diferente de null é secreta
                    nome = usuarios[qtdUsuario].getNome();
                    cadastro.enviarMensagemSecreta(nome);
                    do {
                        cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                        senhaParaTestar = in.nextLine();
                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario).compareTo(senhaParaTestar) == 0) {
                            // senha igual :. exibi mensagem secreta
                            mensagem = usuarios[qtdUsuario].getNome() + ": "
                                    + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                            cadastro.enviarMensagem(mensagem);
                            break;
                        } else {
                            opcaoSenhaSecreta = cadastro.tratamentoSenhaErrada();
                        }
                    } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                } else { // caso seja null é pq é mensagem normal, logo exibimos
                    mensagem = usuarios[qtdUsuario].getNome() + ": "
                            + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                    cadastro.enviarMensagem(mensagem);
                }
                contUsuario++;

            } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario) == null) {  // se for diferente de null é secreta
                //hora
                mensagemHora = usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo);
                cadastro.enviarMensagemHora(mensagemHora);
                // mensagem abaixo 
                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo) != null) {
                    nome = usuarios[qtdAmigo].getNome();
                    cadastro.enviarMensagemSecretaAmigo(nome);
                    do {
                        cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                        senhaParaTestar = in.nextLine();

                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo).compareTo(senhaParaTestar) == 0) {
                            // senha igual :. exibi mensagem secreta
                            mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                            cadastro.enviarMensagemAmigo(mensagemAmigo);
                            break;

                        } else {
                            opcaoSenhaSecreta = cadastro.tratamentoSenhaErrada();
                        }
                    } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                } else { // caso seja null é pq é mensagem normal
                    mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                    cadastro.enviarMensagemAmigo(mensagemAmigo);
                }
                contAmigo++;

            } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo)) < 0) {
                //hora
                mensagemHora = usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario);
                cadastro.enviarMensagemHora(mensagemHora);
                // mensagem abaixo 
                if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario) != null) {  // se for diferente de null é secreta
                    nome = usuarios[qtdUsuario].getNome();
                    cadastro.enviarMensagemSecreta(nome);
                    do {
                        cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                        senhaParaTestar = in.nextLine();

                        if (usuarios[qtdUsuario].getSenhaMensagemSecreta(qtdAmigo, contUsuario).compareTo(senhaParaTestar) == 0) {
                            // senha igual :. exibi mensagem secreta
                            mensagem = usuarios[qtdUsuario].getNome() + ": "
                                    + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                            cadastro.enviarMensagem(mensagem);
                            break;
                        } else {
                            opcaoSenhaSecreta = cadastro.tratamentoSenhaErrada();
                        }
                    } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                } else { // caso seja null é pq é mensagem normal
                    mensagem = usuarios[qtdUsuario].getNome() + ": " + usuarios[qtdUsuario].getMensagem(qtdAmigo, contUsuario);
                    cadastro.enviarMensagem(mensagem);
                }
                contUsuario++;

            } else if (usuarios[qtdUsuario].getHoraMensagens(qtdAmigo, contUsuario).compareTo(usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo)) > 0) {
                //hora
                mensagemHora = usuarios[qtdAmigo].getHoraMensagens(qtdUsuario, contAmigo);
                cadastro.enviarMensagemHora(mensagemHora);
                // mensagem abaixo 
                if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo) != null) {  // se for diferente de null é secreta 
                    nome = usuarios[qtdAmigo].getNome();
                    cadastro.enviarMensagemSecretaAmigo(nome);
                    do {
                        cadastro.solicitacaoSecretaMensagem(); // pede para o usuario digitar senha
                        senhaParaTestar = in.nextLine();
                        if (usuarios[qtdAmigo].getSenhaMensagemSecreta(qtdUsuario, contAmigo).compareTo(senhaParaTestar) == 0) {
                            // senha igual :. exibi mensagem secreta
                            mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                            cadastro.enviarMensagemAmigo(mensagemAmigo);
                            break;
                        } else {
                            opcaoSenhaSecreta = cadastro.tratamentoSenhaErrada();
                        }
                    } while (opcaoSenhaSecreta == 1 || opcaoSenhaSecreta != 2);
                } else { // caso seja null é pq é mensagem normal
                    mensagemAmigo = usuarios[qtdAmigo].getNome() + ": " + usuarios[qtdAmigo].getMensagem(qtdUsuario, contAmigo);
                    cadastro.enviarMensagemAmigo(mensagemAmigo);
                }
                contAmigo++;
            }
        }
    }
}
