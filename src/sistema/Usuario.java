package sistema;

public class Usuario implements Comparable<Usuario> {

    private String login;
    private String senha;
    private String nome;
    private String[] listaAmigos;
    private String[] listaAmigosPendentes;
    private int qtdListaAmigos;
    private int qtdListaAmigosPendentes;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        listaAmigos = new String[100];
        listaAmigosPendentes = new String[100];
        qtdListaAmigos = 0;
        qtdListaAmigosPendentes = 0;
    }

    public Usuario(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdListaAmigos() {
        return this.qtdListaAmigos;
    }

    public int getQtdListaAmigosPendentes() {
        return this.qtdListaAmigosPendentes;
    }

    public String getListaAmigos(int amigo) {
        return this.listaAmigos[amigo];
    }

    public void setListaAmigos(String login) {
        listaAmigos[qtdListaAmigos] = login;
        qtdListaAmigos++;
        for (int i = 0; i < qtdListaAmigosPendentes; i++) {
            if (listaAmigosPendentes[i].compareTo(login) == 0) {
                for (; i < qtdListaAmigosPendentes; i++) {
                    listaAmigosPendentes[i] = listaAmigosPendentes[i + 1];
                }
                break;
            }
        }
        qtdListaAmigosPendentes--;
    }

    public String getListaAmigosPendentes(int amigo) {
        return this.listaAmigosPendentes[amigo];
    }

    public void setListaAmigosPendentes(String login) {
        listaAmigosPendentes[qtdListaAmigosPendentes] = login;
        qtdListaAmigosPendentes++;
    }

    public void alterarNome(String n) {
        this.nome = n;
    }

    public void alterarSenha(String s) {
        this.senha = s;
    }

    public void alterarNomeSenha(String n, String s) {
        setNome(n);
        setSenha(s);
    }

    @Override
    public String toString() {
        String nome, login, dados;
        nome = "Nome: " + this.getNome() + "\n";
        login = "Login: " + this.getLogin() + "\n";
        dados = nome + login;
        return dados;
    }

    @Override
    public int compareTo(Usuario u) {
        return this.login.compareTo(u.login);
    }

}
