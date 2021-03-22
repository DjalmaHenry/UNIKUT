// Classe Cadastro
package loja;

import br.unicap.c3.ed1.listas.LSESemRepetidos;
import java.util.Scanner;
import loja.Cliente;

public class Cadastro {

    private LSESemRepetidos<Cliente> clientes;

    public Cadastro() {
        clientes = new LSESemRepetidos();
    }

    public void inserirValor(String cpf) {
        Scanner in = new Scanner(System.in);
        String nome, telefone, email;
        boolean result;
        Cliente cliA = new Cliente(cpf, "", "", "");
        result = clientes.buscaNum(cliA);

        if (result == true) {
            System.err.println("CPF já existe! Inserção não efetuada!");
        } else {
            System.out.println("Informe o Nome: ");
            System.out.print("-> ");
            nome = in.next();
            in.nextLine();
            System.out.println("Informe o Telefone: ");
            System.out.print("-> ");
            telefone = in.next();
            in.nextLine();
            System.out.println("Informe o E-mail: ");
            System.out.print("-> ");
            email = in.next();
            in.nextLine();
            Cliente cliB = new Cliente(cpf, nome, telefone, email);
            clientes.inserirValorSemV(cliB);
        }
    }

    public void alterarValor(String cpf) {
        Scanner in = new Scanner(System.in);
        String telefone, email;
        Cliente cli;
        boolean result;
        int op;
        Cliente cliA = new Cliente(cpf, "", "", "");
        cli = clientes.buscarObjeto(cliA);
        if (cli == null) {
            System.err.println("CPF NÃO existe!");
        } else {
            do {
                System.out.println("O que deseja alterar?");
                System.out.println("1 - E-mail e Telefone.");
                System.out.println("2 - E-mail.");
                System.out.println("3 - Telefone.");
                System.out.println("0 - Cancelar operação.");
                System.out.println("Informe a opção:");
                System.out.print("-> ");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("Informe o E-mail: ");
                        System.out.print("-> ");
                        email = in.next();
                        in.nextLine();
                        System.out.println("Informe o Telefone: ");
                        System.out.print("-> ");
                        telefone = in.next();
                        in.nextLine();
                        cli.atualizaEmail(email);
                        cli.atualizaTelefone(telefone);
                        System.out.println("Alteração efetuada com sucesso!");
                        break;
                    case 2:
                        System.out.println("Informe o E-mail: ");
                        System.out.print("-> ");
                        email = in.next();
                        in.nextLine();
                        cli.atualizaEmail(email);
                        System.out.println("Alteração efetuada com sucesso!");
                        break;
                    case 3:
                        System.out.println("Informe o Telefone: ");
                        System.out.print("-> ");
                        telefone = in.next();
                        in.nextLine();
                        cli.atualizaTelefone(telefone);
                        System.out.println("Alteração efetuada com sucesso!");
                        break;
                    case 0:
                        System.out.println("Operação cancelada.");
                        break;
                    default:
                        System.err.println("Erro, opção inválida.");
                        break;
                }
            } while (op != 0 && op != 1 && op != 2 && op != 3);
        }
    }

    public void exibirValor(String cpf) {
        Scanner in = new Scanner(System.in);
        String nome, telefone, email;
        Cliente aux;
        boolean result;
        Cliente cli = new Cliente(cpf, "", "", "");
        aux = clientes.buscarObjeto(cli);
        if (aux == null) {
            System.err.println("CPF NÃO existe!");
        } else {
            System.out.println(aux);

        }
    }

    public void removerValor(String cpf) {
        Cliente cli = new Cliente(cpf, "", "", "");
        clientes.removeValor(cli);
    }

    public void exibirValores() {
        clientes.exibirValores();
    }

}
