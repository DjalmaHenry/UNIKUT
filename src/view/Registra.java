package view;

import controller.Cadastro;
import static view.CoresTerminal.*;
import java.util.Scanner;

public class Registra {

    public static void cadastra(Cadastro cadastro, Scanner in) {
        String login, senha, nome = "";
        int op;
        System.out.print("Digite o login: ");
        login = in.nextLine();
        int achouUsuario;
        achouUsuario = cadastro.buscaUsuario(login);
        if (achouUsuario != -1) { // login ja encontrado no vetor.
            System.err.println("UNIKUT - O Usuário já se encontra cadastrado!");
            System.out.println("Informe um login diferente!");
        } else { // login não encontrado, processo de cadastro...
            System.out.print("Informe sua senha: ");
            senha = in.nextLine();
            System.out.println("Deseja inserir um nome na sua conta?");
            do {
                System.out.print("Digite 1 - para sim ou 2 - para não: ");
                op = in.nextInt();
                in.nextLine();
                switch (op) {
                    case 1:
                        System.out.print("Informe o nome: ");
                        nome = in.nextLine();
                        break;
                    case 2:
                        nome = "Convidado";
                        System.out.println(ANSI_GREEN + "UNIKUT - Seu nome foi definido como 'Convidado!'" + ANSI_RESET);
                        break;
                    default:
                        System.err.println("Opção invalida!");
                }
            } while (op != 1 && op != 2);
            cadastro.cadastraConta(login, senha, nome);
            System.out.println(ANSI_GREEN + "UNIKUT - Usuário cadastrado!" + ANSI_RESET);
        }
    }

    public static void cadastraAdmin(Cadastro cadastro, Scanner in) {
        String login, senha, nome = "";
        int op;
        System.out.print("Digite o login: ");
        login = in.nextLine();
        int achouUsuario;
        achouUsuario = cadastro.buscaUsuario(login);
        if (achouUsuario != -1) { // login ja encontrado no vetor.
            System.err.println("UNIKUT - O Usuário já se encontra cadastrado!");
            System.out.println("Informe um login diferente!");
        } else { // login não encontrado, processo de cadastro...
            System.out.print("Informe sua senha: ");
            senha = in.nextLine();
            System.out.print("Informe o nome: ");
            nome = in.nextLine();
            cadastro.cadastraContaAdmin(login, senha, nome);
            System.out.println(ANSI_GREEN + "UNIKUT - Usuário admin cadastrado!" + ANSI_RESET);
        }
    }
}
