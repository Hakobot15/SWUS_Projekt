package SWUS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BrokenBarrierException;

public class Main {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, IOException {

        BufferedReader br = new BufferedReader( new FileReader("settings.txt"));
        String tmp = br.readLine();
            int simulationsNumber = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        tmp = br.readLine();
            int connectionNumber = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        tmp = br.readLine();
            int packageNumber = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        tmp = br.readLine();
            int peakRate = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        tmp = br.readLine();
            int tau = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        tmp = br.readLine();
            int processingTime = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        tmp = br.readLine();
            int bufferSize = Integer.parseInt(tmp.substring(tmp.indexOf("#")+1,tmp.indexOf('@')));
        br.close();

        PrintWriter writer = new PrintWriter("Stats.txt", "UTF-8");
        float suma = 0;

        for (int i = 0; i < simulationsNumber; i++) {
            System.out.println("Symulacja "+i);
            MainProgram mainProgram = new MainProgram(connectionNumber,packageNumber,peakRate,tau,processingTime,bufferSize);
            writer.println("Wynik "+i+" symulacji: "+Double.toString(mainProgram.getStarts())+"%");
            suma += mainProgram.getStarts();
        }
        writer.println("Srednia symulacji: "+Double.toString(suma/(float)simulationsNumber)+"%");
        writer.close();
        System.out.println("Koniec");
        System.exit(0);
    }

}


