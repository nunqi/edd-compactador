import Estruturas.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Compactador {

    FileWriter arq = new FileWriter("/home/nunqi/arquivo.txt");
    PrintWriter gravarArq = new PrintWriter(arq);
    private ArvoreBinaria arvore;
    private int[] histograma = new int[255];
    private Tabela tabela = new Tabela();

    public Compactador() throws IOException {
    }

    public File compactar(File arquivo) throws IOException {
        File arquivoCompactado = new File("/home/nunqi/arquivo.txt");
        Scanner dado = new Scanner(arquivo);

        Arrays.fill(histograma, 0);
        while (dado.hasNextLine()) {
            gerarFrequencia(dado.nextLine());
        }
        histograma[10]--;

        gerarArvoreHuffman();
        gerarCodigoHuffman();
        gerarArquivoCompactado(arquivo);

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

    }

    private void gerarCodigoHuffman() throws IOException {
        ListaEstatica<No> folhas = arvore.encontrarFolhas();
        String caminho;

        for (int i = 0; i < folhas.size(); i++) {
            No folha = folhas.get(i);
            caminho = arvore.encontrarCaminho(folha);
            tabela.adicionar(folha.dado.toString(), caminho);
        }

//        for (int i = 0; i < tabela.size(); i++) {
//            System.out.print(tabela.get(i).dado + " -> ");
//            System.out.println(tabela.get(i).codigo);
//        }

    }

    private void gerarArquivoCompactado(File arquivo) throws IOException {
        Scanner dado = new Scanner(arquivo);
        String linha1 = arvore.definirLinha();
        StringBuilder linha2 = new StringBuilder();
        String linhaAtual;
        String[] vLinhaAtual;

        while (dado.hasNextLine()) {
            linhaAtual = dado.nextLine();
            if (dado.hasNextLine()) linhaAtual += "\n";
            vLinhaAtual = linhaAtual.split("");
            for (int i = 0; i < vLinhaAtual.length; i++) {
                linha2.append(this.tabela.encontrarCodigo(vLinhaAtual[i]));
            }
        }

        System.out.println(linha1+"\n"+linha2);
        gravarArq.printf(linha1+"\n"+linha2);
        arq.close();
    }


    public void descompactar(String arquivo) {

    }

    private void regenerarArvoreHuffman() {

    }

    private void gerarArquivoDescompactado() throws IOException {
        arvore.exibir();
    }

}
