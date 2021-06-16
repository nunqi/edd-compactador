package Estruturas;

public class No {

    public Object dado;
    public int prioridade;
    public No anterior;
    public No proximo;

    public No(Object dado, int prioridade) {
        this.dado = dado;
        this.prioridade = prioridade;
        this.proximo = null;
    }

}
