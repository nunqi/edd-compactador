import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Compactador compactador = new Compactador();

        // O primeiro parâmetro diz o local do arquivo normal e o segundo diz onde vai ser colocado o arquivo compactado
        compactador.compactar("/home/nunqi/Teste/input_compactar.txt", "/home/nunqi/Teste/aux.txt");
        // O primeiro parâmetro diz o local do arquivo compactado e o segundo diz onde vai ser colocado o arquivo descompactado
        compactador.descompactar("/home/nunqi/Teste/aux.txt", "/home/nunqi/Teste/output_descompactar.txt");

    }

}
