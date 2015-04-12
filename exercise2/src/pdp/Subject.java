
package pdp;

import java.util.ArrayList;
import java.util.List;
import pdp.AbstractObserver;

public class Subject implements Runnable {

    List<Integer> pool;
    List<AbstractObserver> observers;

    public synchronized void subscribe(AbstractObserver observer) throws DuplicateObserverException {
        observers.add(observer);
    }

    public synchronized void unsubscribe(AbstractObserver observer) {
        observers.remove(observer);
    }

    public synchronized void insertOnPool(Integer newValue) {
        pool.add(newValue);
    }

    private synchronized void notifyAllObservers() {
        Integer nextValue = pool.remove(0);
        for(AbstractObserver observer : observers) {
            observer.execute(nextValue);
        }
    }

    @Override
    public void run() {
        pool = new ArrayList<Integer>();
        while(true){
            if(pool.size() > 1) {
                notifyAllObservers();
            }
        }
    }
}

