import Estruturas.ArvoreBinaria;
import Estruturas.Codigo;
import Estruturas.ListaEstatica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Compactador {

    private ArvoreBinaria arvore;
    private int[] histograma = new int[255];
    private ListaEstatica<Codigo> codigosHuffman;

    public File compactar(String arquivo) throws FileNotFoundException {
        File arquivoCompactado = new File("/home/nunqi/arquivo.txt");
        Scanner dado = new Scanner(arquivoCompactado);

        Arrays.fill(histograma, 0);
        while (dado.hasNextLine()) {
            gerarFrequencia(dado.nextLine());
        }









        return arquivoCompactado;
    }

    // Gerar o histograma
    private void gerarFrequencia(String str) {

        for (int i = 0; i < str.length(); i++) {
            histograma[str.charAt(i)]++;
        }

    }

    // Gerar a árvore a partir da fila de prioridade
    private void gerarArvoreHuffman() {

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
