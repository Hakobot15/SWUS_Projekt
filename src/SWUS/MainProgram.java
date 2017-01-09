package SWUS;


import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;


/**
 * Created by ${TomaszJaniec} on 07.01.2017.
 */
public class MainProgram {


    /**
     * Buffer
     */
    private Buffer buffer;

    MainProgram(int connectionNumber, int packageNumber, int peakRate, int tau, int processingTime, int bufferSize) throws IOException, BrokenBarrierException, InterruptedException {

        buffer =  new Buffer(bufferSize);
        startProgram(connectionNumber, packageNumber, peakRate, tau, processingTime);
    }

    private void startProgram(int connectionNumber, int packageNumber, int peakRate, int tau, int processingTime) throws BrokenBarrierException, InterruptedException {
        Thread t1 = new EventPacketSender(() -> buffer.deletePackage(), processingTime);
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

    float getStarts() {
        if(buffer.getPackageSent() != 0) {
            return buffer.getPackageSent() / buffer.getPackageCounter() * 100;
        }
        else {
            return 0;
        }
    }
}
