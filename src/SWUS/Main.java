package SWUS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BrokenBarrierException;

public class Main {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, IOException {
        PrintWriter writer = new PrintWriter("Stats.txt", "UTF-8");
        float iloscSymulacji = 50;
        float suma = 0;
        for (int i = 0; i < iloscSymulacji; i++) {
            System.out.println("Symulacja "+i);
            MainProgram mainProgram = new MainProgram();
            writer.println("Wynik "+i+" symulacji: "+Double.toString(mainProgram.getStarts())+"%");
            suma += mainProgram.getStarts();
        }
        writer.println("Srednia symulacji: "+Double.toString(suma/iloscSymulacji)+"%");
        writer.close();
        System.out.println("Koniec");
        System.exit(0);
    }

}
