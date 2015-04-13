
package pdp;

import java.util.List;
import java.util.ArrayList;

import pdp.SharedInteger;

public abstract class AbstractObserver implements Runnable {

    private SharedInteger sharedInteger;
    private List<Integer> pool;
    private boolean willTerminate;

    public AbstractObserver(SharedInteger _sharedInteger) {
        pool = new ArrayList<Integer>();
        sharedInteger = _sharedInteger;
    }

    public void execute(Integer val) {
        if(willTerminate)
            return; // Exception?
        synchronized(this){
            pool.add(val);
            notifyAll();
        }
    }

    public synchronized void terminate() {
        willTerminate = true;
        notifyAll();
    }

    private boolean stopExecution() {
        return pool.size() > 0 ? false : willTerminate;
    }

    protected abstract Integer operation(Integer a, Integer b);

    public void run() {
        Integer nextVal = 0;
        boolean newValue;
        willTerminate = false;
        while(!stopExecution()) {
            synchronized(this) {
                if(pool.size()>0) {
                    newValue = true;
                    nextVal = pool.remove(0);
                } else {
                    newValue = false;
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }
            if(newValue){
                synchronized(sharedInteger){
                    nextVal = operation(sharedInteger.getVal(), nextVal);
                    sharedInteger.setVal(nextVal);
                }
            }
        }
    };
}

