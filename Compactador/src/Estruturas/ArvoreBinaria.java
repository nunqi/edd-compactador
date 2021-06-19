package Estruturas;

/*
Implementar a remoção da raiz
 */

import java.io.IOException;

public class ArvoreBinaria {

    private No raiz;
    private ListaEstatica<No> folhas = new ListaEstatica<>();
    private StringBuilder str;
    private int posCont;

    public ArvoreBinaria(No esquerdo, No direito) throws IOException {
        raiz = new No(0, null);
        raiz.filhos[0] = esquerdo;
        raiz.filhos[0].pai = raiz;
        raiz.filhos[1] = direito;
        raiz.filhos[1].pai = raiz;
        raiz.prioridade = esquerdo.prioridade + direito.prioridade;
    }

    public ArvoreBinaria(String str) {
        this.raiz = new No(0, null);
        this.posCont = 0;
        this.str = new StringBuilder();
        this.str.append(str);
        this.regenerar();
    }

    private void regenerar() {
        regenerarPreOrdem(this.raiz);
    }
    private void regenerarPreOrdem(No raiz) {

        if (str.toString().charAt(posCont) == '1') {
            StringBuilder charAtual = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                posCont++;
                charAtual.append(str.toString().charAt(posCont));
            }

            raiz.dado = (char) Integer.parseInt(charAtual.toString(), 2);
            raiz.dado = raiz.dado.toString();
        }
        else {
            raiz.filhos[0] = new No(0, raiz);
            raiz.filhos[1] = new No(0, raiz);
        }

        posCont++;
        if (raiz.filhos[0] != null) regenerarPreOrdem(raiz.filhos[0]);
        if (raiz.filhos[1] != null) regenerarPreOrdem(raiz.filhos[1]);
    }

    public No getRaiz() {
        return this.raiz;
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

    public String seguirCaminho(String caminho) {
        StringBuilder valor = new StringBuilder();
        No aux = this.raiz;

        for (int i = 0; i < caminho.length(); i++) {
            if (aux.ehFolha()) {
                valor.append(aux.dado);
                aux = this.raiz;
                i--;
            }
            else aux = aux.filhos[caminho.charAt(i) - 48];
        }
        valor.append(aux.dado);

        return valor.toString();
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

    public void exibir() {

        exibirPreOrdem(this.raiz);
        System.out.print("\n");

    }
    private void exibirPreOrdem(No raiz) {

        System.out.print(raiz.dado + " ");
        if(raiz.filhos[0] != null) exibirPreOrdem(raiz.filhos[0]);
        if(raiz.filhos[1] != null) exibirPreOrdem(raiz.filhos[1]);
    }

}
