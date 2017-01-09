package SWUS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;


/**
 * Created by Unknow User on 07.01.2017.
 */
class PackageGenerator {
    private int packageNumber;
    private int tau;
    private int connectionNumber;
    private Runnable addPackage;
    private CountDownLatch latch;
    PackageGenerator(int connectionNumber, int packageNumber, int tau, Runnable addPackage){
        this.addPackage = addPackage;
        this.packageNumber = packageNumber;
        this.tau = tau;
        this.connectionNumber = connectionNumber;
        latch = new CountDownLatch(connectionNumber);
        runConnections();
    }

    private void runConnections() {
        Collection<Thread> threads = new ArrayList<>();
        for(int i = 0;i<connectionNumber;i++) {
            Thread tmp = new Thread(new SingleConnection(latch, packageNumber, tau, addPackage));
            tmp.start();
            threads.add(tmp);
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
