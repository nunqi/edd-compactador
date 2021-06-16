package Estruturas;

/*
Implementar a remoção da raiz
 */

public class ArvoreBinaria {

    public static class No {

        public int dado;
        public No esquerdo;
        public No direito;

        public No(int dado) {
            this.dado = dado;
            this.esquerdo = null;
            this.direito = null;
        }

    }

    private No raiz;
    private int cont;

    public ArvoreBinaria() {
        raiz = null;
        cont = 0;
    }

    public void adicionar(int elemento) {
        if (this.empty()) this.raiz = new No(elemento);
        else {
            adicionar(elemento, this.raiz);
        }

        cont++;
    }

    private void adicionar(int elemento, No raiz) {
        if (elemento < raiz.dado) {
            if (raiz.esquerdo == null) raiz.esquerdo = new No(elemento);
            else adicionar(elemento, raiz.esquerdo);
        }
        else if (elemento > raiz.dado) {
            if (raiz.direito == null) raiz.direito = new No(elemento);
            else adicionar(elemento, raiz.direito);
        }
        else {
            // o que for colocado aqui depende de como a árvore será usada
        }
    }

    public void remover(int elemento) {
        if (!this.empty()) {
            remover(elemento, raiz, null);
            cont--;
        }
    }

    private void remover(int elemento, No raiz, No pai) {
        if (elemento == raiz.dado) {
            if (raiz.esquerdo == null && raiz.direito == null) { // Não tem filhos
                if (raiz == pai.esquerdo) { // É filho esquerdo
                    pai.esquerdo = null;
                }
                else { // É filho direito
                    pai.direito = null;
                }
            }
            else if (raiz.esquerdo != null && raiz.direito == null) { // Tem apenas um filho (esquerdo)
                if (raiz == pai.esquerdo) { // É filho esquerdo
                    pai.esquerdo = raiz.esquerdo;
                }
                else { // É filho direito
                    pai.direito = raiz.esquerdo;
                }
            }
            else if (raiz.esquerdo == null && raiz.direito != null) { // Tem apenas um filho (direito)
                if (raiz == pai.esquerdo) { // É filho esquerdo
                    pai.esquerdo = raiz.direito;
                }
                else { // É filho direito
                    pai.direito = raiz.direito;
                }
            }
            else { // Tem dois filhos
                raiz.dado = this.min(raiz.direito);
                remover(raiz.dado, raiz.direito, raiz);
//                raiz.dado = this.max(raiz.esquerdo);
//                remover(raiz.dado, raiz.esquerdo, raiz);

            }
        }
        else if (elemento < raiz.dado) {
            if (raiz.esquerdo != null) remover(elemento, raiz.esquerdo, raiz);
        }
        else { // if (elemento > raiz.dado)
            if (raiz.direito != null) remover(elemento, raiz.direito, raiz);
        }
    }

    public boolean pesquisar(int elemento) {
        if (this.empty()) return false;
        return pesquisar(elemento, this.raiz);
    }

    private boolean pesquisar(int elemento, No raiz) {
        if (elemento < raiz.dado) {
            if (raiz.esquerdo == null) return false;
            else return pesquisar(elemento, raiz.esquerdo);
        }
        else if (elemento > raiz.dado) {
            if (raiz.direito == null) return false;
            else return pesquisar(elemento, raiz.direito);
        }

        return true;
    }

    public int size() {
        return cont;
    }

    public boolean empty() {
        return raiz == null;
    }

    public void exibir() {

//        exibirPreOrdem(this.raiz);
        exibirEmOrdem(this.raiz);
//        exibirPosOrdem(this.raiz);

        System.out.print("\n");

    }

    private void exibirPreOrdem(No raiz) {
        /*
        Aplicações:
        - Impressão de um documento estruturado
        - Avaliação de expressões aritméticas
         */

        System.out.print(raiz.dado + " ");
        if(raiz.esquerdo != null) exibirPreOrdem(raiz.esquerdo);
        if(raiz.direito != null) exibirPreOrdem(raiz.direito);
    }
    private void exibirEmOrdem(No raiz) {
        /*
        Aplicações:
        - Impressão dos elementos conforme a relação de ordem
         */

        if(raiz.esquerdo != null) exibirEmOrdem(raiz.esquerdo);
        System.out.print(raiz.dado + " ");
        if(raiz.direito != null) exibirEmOrdem(raiz.direito);
    }
    private void exibirPosOrdem(No raiz) {
        /*
        Aplicações:
        - Computar o espaço usado pelos arquivos em um diretório e seus subdiretórios
         */

        if(raiz.esquerdo != null) exibirPosOrdem(raiz.esquerdo);
        if(raiz.direito != null) exibirPosOrdem(raiz.direito);
        System.out.print(raiz.dado + " ");
    }

    public int min(No raiz) {
        if (raiz.esquerdo == null) return raiz.dado;
        else return min(raiz.esquerdo);
    }
    public int max(No raiz) {
        if (raiz.direito == null) return raiz.dado;
        else return max(raiz.direito);
    }

}
