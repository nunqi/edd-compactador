import Estruturas.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Compactador {

    private ArvoreBinaria arvore;
    private int[] histograma = new int[255];
    private ListaEstatica<Codigo> codigosHuffman;

    public File compactar(File arquivo) throws FileNotFoundException {
        File arquivoCompactado = new File("/home/nunqi/arquivo.txt");
        Scanner dado = new Scanner(arquivo);

        Arrays.fill(histograma, 0);
        while (dado.hasNextLine()) {
            gerarFrequencia(dado.nextLine());
        }

        gerarArvoreHuffman();
        gerarCodigoHuffman();

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

        /*
        Ainda não cheguei a começar isso, mas pode ser uma boa fazer alguns teste para confirmar
        quando o "\n" deve ser colocado, dependendo do funcionamento do nextLine().
        Do jeito que tá, o programa provavelmente vai colocar um "\n" quando não devia.
        Também pode só colocar um "histograma[10]--" depois do while no compactar().
         */

    }

    // Gerar a árvore a partir da fila de prioridade
    private void gerarArvoreHuffman() {
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

    private void gerarCodigoHuffman() {

    }

    private void gerarArquivoCompactado() {

    }


    public void descompactar(String arquivo) {

    }

    private void regenerarArvoreHuffman() {

    }

    private void gerarArquivoDescompactado() {

    }

}
