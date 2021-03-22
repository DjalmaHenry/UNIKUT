// Classe Aplicação
package loja;

import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        boolean result;
        int op;
        String cpf;
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Cadastrar um novo cliente.");
            System.out.println("2 - Remover um cliente.");
            System.out.println("3 - Alterar os dados de um cliente.");
            System.out.println("4 - Consultar os dados de um cliente.");
            System.out.println("5 - Exibir os dados de todos os clientes.");
            System.out.println("6 - Sair do programa.");
            System.out.println("Informe a opção desejada: ");
            System.out.print("-> ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Informe o CPF: ");
                    System.out.print("-> ");
                    cpf = in.next();
                    in.nextLine();
                    cadastro.inserirValor(cpf);
                    break;
                case 2:
                    System.out.println("Informe o CPF: ");
                    System.out.print("-> ");
                    cpf = in.next();
                    in.nextLine();
                    cadastro.removerValor(cpf);
                    break;
                case 3:
                    System.out.println("Informe o CPF: ");
                    System.out.print("-> ");
                    cpf = in.next();
                    in.nextLine();
                    cadastro.alterarValor(cpf);
                    break;
                case 4:
                    System.out.println("Informe o CPF: ");
                    System.out.print("-> ");
                    cpf = in.next();
                    in.nextLine();
                    cadastro.exibirValor(cpf);
                    break;
                case 5:
                    cadastro.exibirValores();
                    break;
                case 6:
                    System.out.println("Programa feito por Djalma Henrique");
                    System.out.println("Adeus!");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opção inválida");
            }
        }
    }
}
