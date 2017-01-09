package SWUS;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ${TomaszJaniec} on 08.01.2017.
 */
public class Buffer {
    private final Lock lock;
    private final Condition notEmpty;
    private ConcurrentLinkedDeque<Integer> fifoQueue;
    private int size;
    private int packageCounter = 0;
    private int packageSent = 0;
    Buffer(int size )
    {
        fifoQueue = new ConcurrentLinkedDeque<>();
        this.size = size;
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
    }
    void addPackage() {
        lock.lock();
            packageCounter++;
            if (fifoQueue.size() < size) {
                fifoQueue.add(1);
                notEmpty.signal();
                packageSent++;
                //notify conditional
            }
        lock.unlock();
    }

    boolean deletePackage()   {
        lock.lock();
        try {
            if (fifoQueue.size() == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return fifoQueue.poll() != null;
        } finally {
            lock.unlock();
        }
    }

    boolean isEmpty()
    {
        return fifoQueue.size() == 0;
    }

    public float getPackageCounter() {
        return (float) packageCounter;
    }

    public float getPackageSent() {
        return (float) packageSent;
    }
}
