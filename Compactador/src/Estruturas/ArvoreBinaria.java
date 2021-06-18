package Estruturas;

/*
Implementar a remoção da raiz
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArvoreBinaria {

    private No raiz;
    private ListaEstatica<No> folhas = new ListaEstatica<>();
    private StringBuilder str;

    FileWriter arq = new FileWriter("/home/nunqi/texto.txt");
    PrintWriter gravarArq = new PrintWriter(arq);

    public ArvoreBinaria(No esquerdo, No direito) throws IOException {
        raiz = new No(0, null);
        raiz.filhos[0] = esquerdo;
        raiz.filhos[0].pai = raiz;
        raiz.filhos[1] = direito;
        raiz.filhos[1].pai = raiz;
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

    public ListaEstatica<No> encontrarFolhas() {
        folhas = new ListaEstatica<>();
        encontrarFolhas(this.raiz);
        return folhas;
    }
    public void encontrarFolhas(No raiz) {
        if (raiz.dado != null) {
            this.folhas.adicionar(raiz);
        }
        if(raiz.filhos[0] != null) encontrarFolhas(raiz.filhos[0]);
        if(raiz.filhos[1] != null) encontrarFolhas(raiz.filhos[1]);
    }

    public String encontrarCaminho(No folha) {
        str = new StringBuilder();
        No anterior = folha.pai;

        while (anterior != null) {
            if (folha == anterior.filhos[0]) str.append("0");
            else if (folha == anterior.filhos[1]) str.append("1");

            folha = anterior;
            anterior = folha.pai;
        }

        // Inverte a ordem dos caracteres
        String[] vStr = str.toString().split("");
        str = new StringBuilder();
        for (int i = vStr.length - 1; i >= 0; i--) {
            str.append(vStr[i]);
        }

        return str.toString();
    }

    public String definirLinha() {
        str = new StringBuilder();
        definirLinhaPreOrdem(this.raiz);

        return str.toString();
    }
    private void definirLinhaPreOrdem(No raiz) {
        if (!raiz.ehFolha()) str.append("0");
        else {
            str.append("1");
            String bin = Integer.toBinaryString(raiz.dado.toString().charAt(0));
//            System.out.print(raiz.dado.toString().charAt(0) + " ");
//            System.out.println(bin);
            str.append(String.format("%8s", bin).replace(' ', '0'));
        }

        if (raiz.filhos[0] != null) definirLinhaPreOrdem(raiz.filhos[0]);
        if (raiz.filhos[1] != null) definirLinhaPreOrdem(raiz.filhos[1]);
    }

    public void exibir() throws IOException {
        this.exibirPreOrdem(this.raiz);
        System.out.print("\n");
        arq.close();
    }
    private void exibirPreOrdem(Estruturas.No raiz) throws IOException {

        if(raiz.dado!=null) {
            System.out.print(raiz.dado + "/" + raiz.prioridade + " ");
            gravarArq.printf(raiz.dado + "/" + raiz.prioridade + " ");
        }

        if (raiz.filhos[0] != null) {
            this.exibirPreOrdem(raiz.filhos[0]);
        }

        if (raiz.filhos[1] != null) {
            this.exibirPreOrdem(raiz.filhos[1]);
        }
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
