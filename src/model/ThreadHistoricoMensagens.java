package model;

public class ThreadHistoricoMensagens {

    public static int contUsuario;
    public static int contAmigo;

    public static void executaParalelo(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) throws InterruptedException { //exibição das mensagens em paralelo
        ThreadUsuario usuario = new ThreadUsuario(usuarios, qtdUsuario, qtdAmigo);
        ThreadAmigo amigo = new ThreadAmigo(usuarios, qtdUsuario, qtdAmigo);
        usuario.start();
        amigo.start();
        synchronized (usuario) {
            usuario.join(0,0);
        }
        synchronized (amigo) {
            amigo.join(0,0);
        }
        
    }

}
