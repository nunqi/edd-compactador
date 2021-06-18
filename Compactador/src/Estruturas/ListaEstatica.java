package Estruturas;

public class ListaEstatica<T> {

    private Object[] v;
    private int cont = 0;

    public ListaEstatica() {
        v = new Object[10];
    }

    public void adicionar(T valor) {
        cont++;
        if (cont > v.length) {
            Object[] aux = v;
            v = new Object[v.length + v.length/2];
            if (aux.length >= 0) System.arraycopy(aux, 0, v, 0, aux.length);
//            for (int i = 0; i < aux.length; i++) {
//                v[i] = aux[i];
//            }
        }
        v[cont-1] = valor;
    }

    public void adicionar(int posicao, T valor) throws Exception {
        if (posicao > cont) throw new Exception("Posição " + posicao + " é inválida!");
        else {
            if (cont - posicao >= 0) System.arraycopy(v, posicao, v, posicao + 1, cont - posicao);
//            for (int i = contador; i > posicao; i--) {
//                v[i] = v[i-1];
//            }

            v[posicao] = valor;
            cont++;
        }
    }

    public void remover() {
        cont--;
    }

    public void remover(int posicao) throws Exception {
        if (posicao <= cont) System.arraycopy(v, cont + 1, v, cont, posicao - cont);
//        for (int i = posicao; i > contador; i--) {
//            v[i-1] = v[i];
//        }
        else throw new Exception("Posição inválida");
        cont--;
    }

    public T get(int posicao) {
        if (posicao < cont) return (T) v[posicao];
        else return null;
    }

    public int size() {
        return cont;
    }

    public void exibir() {
        System.out.print("[" + v[0]);
        for (int i = 1; i < cont; i++) {
            System.out.print(", " + v[i]);
        }
        System.out.println("]");
    }

}
