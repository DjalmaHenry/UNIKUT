// Classe LSENode
package br.unicap.c3.ed1.listas;

class LSENode<T extends Comparable<T>> { // esta classe tem visibilidade de pacote

    private T info;
    private LSENode<T> prox;

    LSENode(T valor) {
        info = valor;
    }

    void setInfo(T valor) {
        info = valor;
    }

    void setProx(LSENode<T> novoProx) {
        prox = novoProx;
    }

    T getInfo() {
        return info;
    }

    LSENode<T> getProx() {
        return prox;
    }
}
