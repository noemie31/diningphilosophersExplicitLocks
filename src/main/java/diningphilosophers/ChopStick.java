package diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private static int stickCount = 0;
    private boolean iAmFree = true;
    private final int myNumber;

    private final Lock verrou = new ReentrantLock();
    public ChopStick() {
        myNumber = ++stickCount;
    }

    public boolean tryTake(int DELAY) throws InterruptedException {
        if(verrou.tryLock(DELAY, TimeUnit.MILLISECONDS)){
            System.out.println("Stick " + myNumber + " Taken");
            return true;
        }
        return false;
    }

    public void release() {
        System.out.println("Stick " + myNumber + " Released");
        verrou.unlock();
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
