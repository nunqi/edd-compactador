import Estruturas.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Compactador {

    private ArvoreBinaria arvore;
    private int[] histograma = new int[255];
    private ListaEstatica<Codigo> tabela;

    public File compactar(File arquivo) throws IOException {
        File arquivoCompactado = new File("/home/nunqi/arquivo.txt");
        Scanner dado = new Scanner(arquivo);

        Arrays.fill(histograma, 0);
        while (dado.hasNextLine()) {
            gerarFrequencia(dado.nextLine());
        }
        histograma[10]--;

        gerarArvoreHuffman();
        gerarArquivoCompactado();

        return arquivoCompactado;
    }

    // Gerar o histograma
    private void gerarFrequencia(String str) {

        // Adiciona cada caractere da linha no histograma
        for (int i = 0; i < str.length(); i++) {
            histograma[str.charAt(i)]++;
        }

        // Adiciona o "\n" do final da linha, que não é lido pelo nextLine()
        histograma[10]++;

    }

    // Gerar a árvore a partir da fila de prioridade
    private void gerarArvoreHuffman() throws IOException {
        FilaPrioridade fila = new FilaPrioridade();

        for (int i = 0; i < histograma.length; i++) {
            if (histograma[i] > 0) {
                fila.enqueue((char) i, histograma[i]);
            }
        }

        ArvoreBinaria arvoreAux = null;

        while (fila.size() != 1) {

            No primeiro = fila.front();
            fila.dequeue();
            No segundo = fila.front();
            fila.dequeue();

            arvoreAux = new ArvoreBinaria(primeiro, segundo);
            fila.enqueue(arvoreAux.getRaiz());

        }

        arvore = arvoreAux;

        arvore.exibir();



    }

    private void gerarCodigoHuffman() throws IOException {
        ListaEstatica<No> folhas = arvore.encontrarFolhas();
        String caminho;
        this.tabela = new ListaEstatica<>();

        for (int i = 0; i < folhas.size(); i++) {
            No folha = folhas.get(i);
            caminho = arvore.encontrarCaminho(folha);
            Codigo codigo = new Codigo(folha.dado.toString(), caminho);
            tabela.adicionar(codigo);
        }

//        for (int i = 0; i < tabela.size(); i++) {
//            System.out.print(tabela.get(i).dado + " -> ");
//            System.out.println(tabela.get(i).codigo);
//        }

    }

    private void gerarArquivoCompactado() {
        String linha1 = arvore.definirLinha();
        String linha2 = "";

        

        System.out.println(linha1);

    }


    public void descompactar(String arquivo) {

    }

    private void regenerarArvoreHuffman() {

    }

    private void gerarArquivoDescompactado() throws IOException {
        arvore.exibir();
    }

}
