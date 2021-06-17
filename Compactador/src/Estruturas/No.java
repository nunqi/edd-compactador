package Estruturas;

public class No {

    public Object dado;
    public int prioridade;
    public No[] filhos;
    public No anterior;
    public No proximo;

    public No(Object dado, int prioridade) {
        this.dado = dado;
        this.prioridade = prioridade;
        this.anterior = null;
        this.proximo = null;
        filhos = new No[2];
    }

    public No(int prioridade) {
        this.dado = null;
        this.prioridade = prioridade;
        this.anterior = null;
        this.proximo = null;
        filhos = new No[2];
    }

}
