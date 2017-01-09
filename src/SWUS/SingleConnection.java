package SWUS;


import java.util.concurrent.*;

/**
 * Created by Unknow User on 07.01.2017.
 */
public class SingleConnection implements Runnable {
    @Override
    public void run() {
        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.NANOSECONDS.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < packageNumber;i++)
        {
            new Thread(() -> addPackage.run()).start();
            try {
                TimeUnit.NANOSECONDS.sleep(tau);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private CountDownLatch latch;
    private int packageNumber;
    private int tau;
    private int randomNum;
    private Runnable addPackage;
    SingleConnection(CountDownLatch latch, int packageNumber, int tau, Runnable addPackage)
    {
        this.latch = latch;
        this.packageNumber = packageNumber;
        this.tau = tau;
        randomNum = ThreadLocalRandom.current().nextInt(0, tau + 1);
        this.addPackage = addPackage;
    }
}
