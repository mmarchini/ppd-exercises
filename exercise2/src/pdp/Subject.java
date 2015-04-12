
package pdp;

import java.util.ArrayList;
import java.util.List;
import pdp.AbstractObserver;

public class Subject implements Runnable {

    List<Integer> pool;
    List<AbstractObserver> observers;

    public synchronized void subscribe(AbstractObserver observer){
        observers.add(observer);
    }

    public synchronized void unsubscribe(AbstractObserver observer) {
        observers.remove(observer);
    }

    public synchronized void insertOnPool(Integer newValue) {
        pool.add(newValue);
    }

    private synchronized void notifyAllObservers(Integer nextValue) {
        for(AbstractObserver observer : observers) {
            observer.execute(nextValue);
        }
    }

    private synchronized void terminateAllObservers() {
        for(AbstractObserver observer : observers) {
            observer.terminate();
        }
    }

    @Override
    public void run() {
        for(Integer nextValue : pool) {
            notifyAllObservers(nextValue);
        }
        terminateAllObservers();
    }
}

