import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner dado = new Scanner(System.in);
        Compactador compactador = new Compactador();

        String input_compactar, output_compactar, input_descompactar, output_descompactar;

        label:
        while (true) {
            System.out.print("Comando: ");
            String comando = dado.nextLine();
            switch (comando) {
                case "compactar":
                    System.out.print("Arquivo que deve ser compactado: ");
                    input_compactar = dado.nextLine();
                    System.out.print("Arquivo compactado: ");
                    output_compactar = dado.nextLine();

                    compactador.compactar(input_compactar, output_compactar);
                    break;
                case "descompactar":
                    System.out.print("Arquivo que deve ser descompactado: ");
                    input_descompactar = dado.nextLine();
                    System.out.print("Arquivo descompactado: ");
                    output_descompactar = dado.nextLine();

                    compactador.descompactar(input_descompactar, output_descompactar);
                    break;
                case "sair":
                    break label;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
        }


//        input_compactar = ""; // Arquivo que vai ser compactado
//        output_compactar = ""; // Arquivo compactado
//        input_descompactar = output_compactar; // Arquivo que vai ser descompactado
//        output_descompactar = ""; // Arquivo descompactado
//
//        // O primeiro parâmetro diz o local do arquivo normal e o segundo diz onde vai ser colocado o arquivo compactado
//        compactador.compactar(input_compactar, output_compactar);
//        // O primeiro parâmetro diz o local do arquivo compactado e o segundo diz onde vai ser colocado o arquivo descompactado
//        compactador.descompactar(input_descompactar, output_descompactar);
//
//         // Verifica se os dois arquivos são iguais
//         // Não dá para usar com os comandos porque as variáveis não foram inicializadas
//        verificar(input_compactar, output_descompactar);

    }

    public static void verificar(String input_compactar, String output_descompactar) throws FileNotFoundException {
        File arquivoC = new File(input_compactar);
        File arquivoD = new File(output_descompactar);
        Scanner dadoC = new Scanner(arquivoC);
        Scanner dadoD = new Scanner(arquivoD);
        StringBuilder stringBuilderC = new StringBuilder(), stringBuilderD = new StringBuilder();

        while (dadoC.hasNext()) {
            stringBuilderC.append(dadoC.next());
        }

        while (dadoD.hasNext()) {
            stringBuilderD.append(dadoD.next());
        }

        String strC = stringBuilderC.toString();
        String strD = stringBuilderD.toString();

        if (strC.equals(strD)) System.out.println("deu certo");
        else System.out.println("não deu");
    }

}
