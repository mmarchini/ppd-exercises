
package pdp;

import java.util.List;
import java.util.ArrayList;

import pdp.SharedInteger;

public abstract class AbstractObserver {

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
        }
    }

    public synchronized void terminate() {
        willTerminate = true;
    }

    private boolean stopExecution() {
        return pool.size() > 0 ? false : willTerminate;
    }

    protected abstract Integer operation(Integer a, Integer b);

    public void run() {
        Integer nextVal;
        willTerminate = false;
        while(!stopExecution()) {
            if(pool.size()>0){
                synchronized(this) {
                    nextVal = pool.remove(0);
                }
                synchronized(sharedInteger){
                    nextVal = operation(sharedInteger.getVal(), nextVal);
                    sharedInteger.setVal(nextVal);
                }
            }
        }
    };
}

