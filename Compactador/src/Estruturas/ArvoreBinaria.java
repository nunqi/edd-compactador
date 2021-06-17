package Estruturas;

/*
Implementar a remoção da raiz
 */

public class ArvoreBinaria {

    private No raiz;

    public ArvoreBinaria(No esquerdo, No direito) {
        raiz = new No(0);
        raiz.filhos[0] = esquerdo;
        raiz.filhos[1] = direito;
        raiz.prioridade = esquerdo.prioridade + direito.prioridade;
    }

    public No getRaiz() {
        return this.raiz;
    }

    public No pesquisar(String caminho) {
        String[] vCaminho = caminho.split("");
        No aux = this.raiz;
        for (int i = 0; i < vCaminho.length; i++) {
            aux = aux.filhos[Integer.parseInt(vCaminho[i])];
        }

        return aux;
    }

    public void exibir() {

        exibirPreOrdem(this.raiz);
//        exibirEmOrdem(this.raiz);
//        exibirPosOrdem(this.raiz);

        System.out.print("\n");

    }

    private void exibirPreOrdem(No raiz) {
        /*
        Aplicações:
        - Impressão de um documento estruturado
        - Avaliação de expressões aritméticas
         */

        System.out.print(raiz.dado + "/" + raiz.prioridade + " ");
        if(raiz.filhos[0] != null) exibirPreOrdem(raiz.filhos[0]);
        if(raiz.filhos[1] != null) exibirPreOrdem(raiz.filhos[1]);
    }
    private void exibirEmOrdem(No raiz) {
        /*
        Aplicações:
        - Impressão dos elementos conforme a relação de ordem
         */

        if(raiz.filhos[0] != null) exibirEmOrdem(raiz.filhos[0]);
        System.out.print(raiz.dado + " ");
        if(raiz.filhos[1] != null) exibirEmOrdem(raiz.filhos[1]);
    }
    private void exibirPosOrdem(No raiz) {
        /*
        Aplicações:
        - Computar o espaço usado pelos arquivos em um diretório e seus subdiretórios
         */

        if(raiz.filhos[0] != null) exibirPosOrdem(raiz.filhos[0]);
        if(raiz.filhos[1] != null) exibirPosOrdem(raiz.filhos[1]);
        System.out.print(raiz.dado + " ");
    }

    public Object min(No raiz) {
        if (raiz.filhos[0] == null) return raiz.dado;
        else return min(raiz.filhos[0]);
    }
    public Object max(No raiz) {
        if (raiz.filhos[1] == null) return raiz.dado;
        else return max(raiz.filhos[1]);
    }

}
