// Classe LSESemRepetidos
package br.unicap.c3.ed1.listas;

public class LSESemRepetidos<T extends Comparable<T>> {

    private LSENode<T> prim;
    private LSENode<T> ult;
    private int qtd;

    // métodos publicos para manipulação da lista
    public void inserirValorInicio(T valor) {
        LSENode<T> novo;
        LSENode<T> aux;
        boolean achou;
        novo = new LSENode(valor);
        if (qtd == 0) { // lista está vazia
            prim = novo;
            ult = novo;
            qtd++;
        } else { // lista não está vazia
            achou = this.buscaNum(valor);
            if (achou == true) { // achou!
                System.out.println("Valor repetido! Inserção não efetuada!");
            } else {
                aux = new LSENode(valor);
                aux.setProx(prim);
                prim = aux;
                qtd++;
                System.out.println("Inserção efetuada com sucesso!");
            }
        }
    }

    public void inserirValorFinal(T valor) {
        LSENode<T> novo;
        boolean achou;
        novo = new LSENode(valor);
        if (qtd == 0) { // lista está vazia
            prim = novo;
            ult = novo;
            qtd++;
        } else { // lista não está vazia
            achou = this.buscaNum(valor);
            if (achou == true) { // achou!
                System.out.println("Valor repetido! Inserção não efetuada!");
            } else {  // não achou!!!
                ult.setProx(novo);
                ult = novo;
                qtd++;
                System.out.println("Inserção efetuada com sucesso!");
            }
        }
    }

    public void inserirValorSemV(T valor) {
        LSENode<T> novo;
        boolean achou;
        novo = new LSENode(valor);
        if (qtd == 0) { // lista está vazia
            prim = novo;
            ult = novo;
            qtd++;
        } else { // lista não está vazia
            ult.setProx(novo);
            ult = novo;
            qtd++;
            System.out.println("Inserção efetuada com sucesso!");
        }
    }

    public void RemValorInicio() {
        if (qtd == 0) { // lista está vazia
            System.err.println("Erro, lista vázia.");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        } else { // lista não está vazia
            prim = prim.getProx();
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        }
    }

    public void RemValorFinal() {
        LSENode<T> aux;
        if (qtd == 0) { // lista está vazia
            System.err.println("Erro, lista vázia.");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        } else { // lista não está vazia
            aux = prim;
            for (int i = 1; i < qtd - 1; i++) {
                aux = aux.getProx();
            }
            ult = aux;
            aux.setProx(null);
            qtd--;
            System.out.println("Remoção efetuada com sucesso!");
        }
    }

    public boolean encontra(T valor) {
        LSENode<T> aux;
        if (qtd == 0) { // lista vazia
            return false;
        } else {
            aux = prim;
            while (aux != null) {
                if (aux.getInfo().equals(valor)) {
                    return true;
                } else {
                    aux = aux.getProx();
                }
            }
            return false;
        }
    }

    public void removeValor(T valor) {
        LSENode<T> result, auxA, auxB;
        result = buscaProd(valor);
        if (result == null) {
            System.err.println("Erro, Valor NÃO encontrado!");
        } else if (qtd == 1) {
            prim = null;
            ult = null;
            qtd--;
            System.out.println("Valor removido com sucesso!");
        } else if (result == prim) {
            prim = prim.getProx();
            qtd--;
            System.out.println("Valor removido com sucesso!");
        } else {
            auxA = prim;
            auxB = prim;
            for (int i = 1; i < qtd-1; i++) {
                auxA = auxA.getProx();
                if (result == auxA) {
                    auxB.setProx(auxA.getProx());
                    qtd--;
                    System.out.println("Valor removido com sucesso!");
                    break;
                }
                auxB = auxA;
            }
        }
    }

    public boolean buscaNum(T valor) { // Busca sequencial simples
        LSENode<T> aux;
        if (qtd == 0) { // lista vazia
            return false;
        } else {
            aux = prim;
            while (aux != null) {
                if (aux.getInfo().compareTo(valor) == 0) {
                    return true;
                } else {
                    aux = aux.getProx();
                }
            }
            return false;
        }
    }

    private LSENode<T> buscaProd(T p) { // Busca sequencial simples
        LSENode<T> aux;
        T aux2;
        if (qtd == 0) { // lista vazia
            return null;
        } else {
            aux = prim;
            for (int i = 0; i < qtd; i++) {
                aux2 = aux.getInfo();
                if (aux2.compareTo(p) == 0) {
                    return aux;
                }
                aux = aux.getProx();
            }
            return null;
        }
    }

    public void exibirValor(T p) {
        LSENode<T> aux;
        if (qtd == 0) {
            System.out.println("Lista vazia!!");
        } else {
            aux = buscaProd(p);
            if (aux == null) {
                System.err.println("Valor não cadastrado!");
            } else {
                System.out.println(aux.getInfo());
            }
        }
    }

    public T buscarObjeto(T valor) {
        LSENode<T> pos;
        pos = buscaProd(valor);
        if (pos == null) {
            return null;
        } else {
            return pos.getInfo(); // <<<=== retornando o objeto
        }
    }

    public void exibirValores() {
        LSENode<T> aux;
        if (qtd == 0) {
            System.out.println("Lista vazia!!");
        } else {
            aux = prim;
            while (aux != null) {
                System.out.println(aux.getInfo());
                aux = aux.getProx();
            }
        }
    }
}
