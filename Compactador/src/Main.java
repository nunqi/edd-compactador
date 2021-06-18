import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner dado = new Scanner(System.in);
        Compactador compactador = new Compactador();

//        System.out.print("Escreva o nome do arquivo que deseja compactar: ");
//        File arquivo = new File(dado.nextLine());
        File arquivo = new File("/home/nunqi/file.txt");
        compactador.compactar(arquivo);

    }

}
