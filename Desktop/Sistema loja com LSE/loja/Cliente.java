// Classe Cliente
package loja;

import br.unicap.c3.ed1.listas.LSESemRepetidos;

public class Cliente implements Comparable<Cliente> {

    private String cpf;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(String cpf, String nome, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    
    public Cliente(String cpf){
        this.cpf = cpf;
    }

    private String getCpf() {
        return this.cpf;
    }

    private String getNome() {
        return this.nome;
    }

    private String getTelefone() {
        return this.telefone;
    }

    private void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    private String getEmail() {
        return this.email;
    }

    private void setEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public void atualizaEmail(String email) {
        this.setEmail(email);
    }

    public void atualizaTelefone(String telefone) {
        this.setTelefone(telefone);
    }

    public String toString() {
        return "Nome: " + this.getNome() + " Telefone: " + this.getTelefone()
                + " E-mail: " + this.getEmail();
    }
    
    public int compareTo(Cliente cliente) {
        int result;
        result = this.cpf.compareTo(cliente.cpf);
        return result;
    }
}
