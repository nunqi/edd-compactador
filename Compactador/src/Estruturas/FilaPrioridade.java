package Estruturas;

public class FilaPrioridade {

    private No primeiro;
    private No ultimo;
    private int cont;

    public FilaPrioridade() {
        this.primeiro = null;
        this.ultimo = null;
        this.cont = 0;
    }

    public void enqueue(No novo) {
        if (this.empty()) {
            primeiro = novo;
            ultimo = novo;
        }
        else {
            if (primeiro.prioridade > novo.prioridade) {
                novo.proximo = primeiro;
                primeiro = novo;
            }
            else {
                No aux = primeiro;
                while (aux.proximo != null && aux.proximo.prioridade <= novo.prioridade) {
                    aux = aux.proximo;
                }

                novo.proximo = aux.proximo;
                aux.proximo = novo;
            }
        }

        if (ultimo.proximo != null) ultimo = ultimo.proximo;

        cont++;
    }

    public void enqueue(Object valor, int prioridade) {
        No novo = new No(valor, prioridade);

        if (this.empty()) {
            primeiro = novo;
            ultimo = novo;
        }
        else {
            if (primeiro.prioridade > novo.prioridade) {
                novo.proximo = primeiro;
                primeiro = novo;
            }
            else {
                No aux = primeiro;
                while (aux.proximo != null && aux.proximo.prioridade <= novo.prioridade) {
                    aux = aux.proximo;
                }

                novo.proximo = aux.proximo;
                aux.proximo = novo;
            }
        }

        if (ultimo.proximo != null) ultimo = ultimo.proximo;

        cont++;
    }

    public void dequeue() {
        if (!this.empty()) {
            primeiro = primeiro.proximo;
            cont--;
        }
    }

    public boolean search(Object valor) {
        if (primeiro == ultimo) {
            return primeiro.dado.equals(valor);
        }
        No aux = primeiro;
        while (aux.proximo != null) {
            if (aux.dado.equals(valor)) {
                return true;
            }
            aux = aux.proximo;
        }
        if (ultimo.dado.equals(valor)) {
            return true;
        }

        return false;
    }

    public int size() {
        return cont;
    }

    public boolean empty() {
        return primeiro == null;
    }

    public No front() { //também chamdo de "peek"
        return primeiro;
    }

    public void exibir() {
        No aux = primeiro;
        System.out.print("[");
        while (aux != null) {
            System.out.print(aux.dado);
            aux = aux.proximo;
            if (aux != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void reset() {
        this.primeiro = null;
        this.ultimo = null;
        this.cont = 0;
    }

}
