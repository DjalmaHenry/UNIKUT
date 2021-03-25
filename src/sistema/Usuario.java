package sistema;

public class Usuario implements Comparable<Usuario>{
    private String login;
    private String senha;
    private String nome;
    
    public Usuario(String l, String s) {
        this.login = l;
        this.senha = s;
        this.nome = "Convidado";
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
    
    public void alterarNome(String n){
        this.nome = n;
    }
    
    public void alterarSenha(String s){
        this.senha = s;
    }
    
    @Override
    public String toString () {
        String no, lo, dados;
        no = "Nome: "+this.getNome()+"\n";
        lo = "Login: "+this.getLogin()+"\n";
        dados = nome + login;
        return dados;
    }

    @Override
    public int compareTo(Usuario u) {
        return this.login.compareTo(u.login);
    }
    
    
}
