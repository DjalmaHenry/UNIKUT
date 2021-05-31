package view;

import controller.Cadastro;
import java.util.Scanner;

public class Aplicacao {

    protected Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        cadastro.cadastraConta("al", "", "alyson");
        cadastro.cadastraConta("dj", "", "djalma");
        int op;
        String login, senha;
        do {
            menuDesLog();
            op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1:
                    //criação de conta
                    Registra.cadastra(cadastro, in);
                    //pós criação \/
                    break;

                case 2:
                    //criação de conta admin
                    String combinacao = "admin123";
                    System.out.println("Informe a chave de registro de admin:");
                    senha = in.nextLine();
                    if (senha.compareTo(combinacao) == 0) {
                        Registra.cadastraAdmin(cadastro, in);
                    } else {
                        System.err.println("UNIKUT - Chave de registro inválida!");
                    }
                    break;
                case 3:
                    //login em conta
                    System.out.print("Digite o seu login: ");
                    login = in.nextLine();
                    System.out.print("Digite a sua senha: ");
                    senha = in.nextLine();
                    //pós login \/
                    try {
                        cadastro.logaConta(login, senha, cadastro);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("UNIKUT - Desligando... Volte sempre!");
                    break;
                default:
                    System.err.println("UNIKUT - Erro, opção inválida.");
            }
        } while (op != 0);
    }

    public static void menuDesLog() {
        System.out.println("UNIKUT - MENU:");
        System.out.println("1 - Criar uma conta.");
        System.out.println("2 - Criar uma conta admin.");
        System.out.println("3 - Logar em uma conta.");
        System.out.println("0 - Sair do programa.");
        System.out.println("Informe a opção desejada: ");
        System.out.print("-> ");
    }
}
