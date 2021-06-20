import Estruturas.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Compactador {

    FileWriter arq;
    PrintWriter gravarArq;
    private ArvoreBinaria arvore;
    private final int[] histograma = new int[255];
    private final Tabela tabela = new Tabela();

    public void compactar(String inputPath, String outputPath) throws IOException {

        File arquivo = new File(inputPath);
        arq = new FileWriter(outputPath);
        gravarArq = new PrintWriter(arq);
        Scanner dado = new Scanner(arquivo);

        Arrays.fill(histograma, 0);
        while (dado.hasNextLine()) {
            gerarFrequencia(dado.nextLine());
        }
        histograma[10]--;

        gerarArvoreHuffman();
        gerarCodigoHuffman();
        gerarArquivoCompactado(arquivo);

        arq.close();

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

        if (fila.size() == 1) {
            arvoreAux = new ArvoreBinaria(fila.front());
        }
        else {
            while (fila.size() != 1) {

                No primeiro = fila.front();
                fila.dequeue();
                No segundo = fila.front();
                fila.dequeue();

                arvoreAux = new ArvoreBinaria(primeiro, segundo);
                fila.enqueue(arvoreAux.getRaiz());

            }
        }

        arvore = arvoreAux;

    }

    private void gerarCodigoHuffman() {
        ListaEstatica<No> folhas = arvore.encontrarFolhas();
        String caminho;

        if (folhas.size() == 1) tabela.adicionar(folhas.get(0).dado.toString(), "0");
        else {
            for (int i = 0; i < folhas.size(); i++) {
                No folha = folhas.get(i);
                caminho = arvore.encontrarCaminho(folha);
                tabela.adicionar(folha.dado.toString(), caminho);
            }
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
            for (String s : vLinhaAtual) {
                linha2.append(this.tabela.encontrarCodigo(s));
            }
        }

//        System.out.println(linha1+"\n"+linha2);
        gravarArq.printf(linha1+"\n"+linha2);

    }


    public void descompactar(String inputPath, String outputPath) throws IOException {

        File arquivo = new File(inputPath);
        arq = new FileWriter(outputPath);
        gravarArq = new PrintWriter(arq);
        Scanner dado = new Scanner(arquivo);

        String linha1 = dado.nextLine();
        String linha2 = dado.nextLine();

        regenerarArvoreHuffman(linha1);
        gerarArquivoDescompactado(linha2);

        arq.close();

    }

    private void regenerarArvoreHuffman(String linha) {

        this.arvore = new ArvoreBinaria(linha);
//        this.arvore.exibir();

    }

    private void gerarArquivoDescompactado(String mensagemCompactada) {

        String mensagem = arvore.seguirCaminho(mensagemCompactada);
        gravarArq.printf(mensagem);

    }

}
