package model;

public class ThreadHistoricoMensagens {

    public static int contUsuario;
    public static int contAmigo;

    public static void executaParalelo(Usuario[] usuarios, int qtdUsuario, int qtdAmigo) { //exibição das mensagens em paralelo
        ThreadUsuario usuario = new ThreadUsuario(usuarios, qtdUsuario, qtdAmigo);
        ThreadAmigo amigo = new ThreadAmigo(usuarios, qtdUsuario, qtdAmigo);
        usuario.start();
        amigo.start();
    }
}
