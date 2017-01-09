package SWUS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ${TomaszJaniec} on 07.01.2017.
 */
public class MainProgram {

    /**
     * Ilosc polaczen do routera
     */
    private int connectionNumber;
    /**
     * Ilosc pakietow w ramach jednego polaczenia
     */
    private int packageNumber;
    /**
     * PeakRate
     */
    private int peakRate;
    /**
     * czas Tau - czas pomiedzy wyslaniem 2 pakietow w nanoS
     */
    private int tau;
    /**
     * Czas obslugi jednego pakietu - w nanoS
     */
    private int processingTime;
    private int bufferSize;
    /**
     * Buffer
     */

    private  Buffer buffer;

    MainProgram() throws IOException, BrokenBarrierException, InterruptedException {
        readFile();
        buffer =  new Buffer(bufferSize);
        startProgram();
    }

    private void startProgram() throws BrokenBarrierException, InterruptedException {
        Thread t1 = new EventPacketSender(() -> buffer.deletePackage(), processingTime );
        Thread t2 = new Thread(() -> {
                new PackageGenerator(connectionNumber, packageNumber, tau, () -> buffer.addPackage());
        });
        t1.start();
        t2.start();
        t2.join();
        while(!buffer.isEmpty())
        {
            continue;
        }

    }

    private void readFile() throws IOException {
        BufferedReader br = new BufferedReader( new FileReader("settings.txt"));
        connectionNumber = Integer.parseInt(br.readLine());
        packageNumber = Integer.parseInt(br.readLine());
        peakRate = Integer.parseInt(br.readLine());
        tau = Integer.parseInt(br.readLine());
        processingTime = Integer.parseInt(br.readLine());
        bufferSize = Integer.parseInt(br.readLine());
        br.close();
    }

    int getConnectionNumber(){
        return connectionNumber;
    }

    public int getPeakRate() {
        return peakRate;
    }

    int getTau() {
        return tau;
    }

    int getProcessingTime() {
        return processingTime;
    }

    int getPackageNumber() {
        return packageNumber;
    }

    float getStarts() {
        if(buffer.getPackageSent() != 0) {
            return buffer.getPackageSent() / buffer.getPackageCounter() * 100;
        }
        else {
            return 0;
        }
    }
}
