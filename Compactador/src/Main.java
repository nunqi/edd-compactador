import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner dado = new Scanner(System.in);
        Compactador compactador = new Compactador();

        System.out.print("Escreva o caminho para o arquivo que deseja compactar: ");
        compactador.compactar(dado.nextLine());

    }

}
