package SWUS;

import java.io.*;
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
        writer.println("Srednia symulacji: "+Float.toString(suma/(float)simulationsNumber)+"%");
        writer.close();
        PrintWriter writer2 = new PrintWriter(new FileWriter("AverageStats.txt", true));
        writer2.println("Wynik symulacji:"+Float.toString(suma/(float)simulationsNumber)+"% dla danych: simulationsNumber("+simulationsNumber+");connectionNumber("+connectionNumber+");" +
                "packageNumber("+packageNumber+");peakRate("+peakRate+");tau("+tau+"ns);processingTime("+processingTime+"ns);bufferSize("+bufferSize+")");
        System.out.println("Koniec");
        writer2.close();
        System.exit(0);
    }

}


