package concurrency.problems;

//Imagine you have an application where you have multiple readers and a single writer.
// You are asked to design a lock which lets multiple readers read at the same time, but only one writer write at a time

public class ReadWriteLock {
    //all the methods are synchronized on the ReadWriteLock object itself.
    boolean isWriteLocked = false;
    int readers = 0;
    public synchronized void acquireReadLock() throws InterruptedException{
        while(isWriteLocked){
           wait();
        }
        readers++;
    }
    public synchronized void releaseReadLock(){
        if(readers>0){
            readers--;
            notify();
        }
    }
    public synchronized void acquireWriteLock() throws InterruptedException {
    while(readers!=0 || isWriteLocked){
        wait();
    }
        isWriteLocked= true;
    }
    public synchronized void releaseWriteLock() throws InterruptedException{
        if(isWriteLocked){
            isWriteLocked = false;
        }
        notify();
    }

}
