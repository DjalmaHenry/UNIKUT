package sistema;

import java.util.Scanner;

public class Cadastro {

    private Usuario[] usuarios;
    private int qtd;

    public Cadastro() {
        usuarios = new Usuario[100];
        this.qtd = 0;
    }
    
    public void cadastrarUsuario(String login) {
        Usuario u = new Usuario(login,"","");
        boolean achouUsuario;
        achouUsuario = buscarUsuario(u);
        if (achouUsuario == true) {
            System.out.println("===================================================");
            System.err.println("O Usuário já se encontra cadastrado!");
            System.out.println("Informe um login diferente!");
            System.out.println("===================================================");
        } else {
            Scanner in = new Scanner(System.in);
            String senha;
            int op;
            String nome = "";
            System.out.println("Informe sua senha: ");
            senha = in.nextLine();
            System.out.println("Deseja inserir um nome na sua conta?");
            do {
                System.out.print("Digite 1 - para sim ou 2 - para não: ");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        nome = in.nextLine();
                        break;
                    case 2:
                        nome = "Convidado";
                        System.out.println("===================================================");
                        System.out.println("Seu nome foi definido como convidado!");
                        System.out.println("===================================================");
                        break;
                    default:
                        System.out.println("===================================================");
                        System.err.println("Opção invalida!");
                        System.out.println("===================================================");
                }
                
            } while (op!= 1 && op != 2);
            usuarios[this.qtd] = new Usuario(login, senha, nome);
            this.qtd++; // usuario cadastrado.
            System.out.println("===================================================");
            System.out.println("Usuário cadastrado!");
            System.out.println("===================================================");
        }
    }
    
    private boolean buscarUsuario(Usuario u) {
        if (this.qtd == 0) {
            return false;
        }
        for (int i = 0; i < this.qtd; i++) {
            if (usuarios[i].compareTo(u) == 0) {
                return true;
            }
        }
        return false;
    }
}
