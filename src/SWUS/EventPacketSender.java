package SWUS;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by Unknow User on 07.01.2017.
 */
public class EventPacketSender extends Thread implements Runnable {
    private int processingTime;
    private Supplier<Boolean> deletePackage;
    @Override
    public void run() {
    while(!Thread.currentThread().isInterrupted() | deletePackage.get())
    {
        try {
        if(deletePackage.get()) {
            TimeUnit.NANOSECONDS.sleep(processingTime);
        }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    }
    EventPacketSender(Supplier<Boolean> deletePackage, int processingTime)  {
        this.deletePackage = deletePackage;
        this.processingTime = processingTime;
    }
}
