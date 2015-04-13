
package pdp;

import pdp.Subject;
import pdp.SharedInteger;
import pdp.Reducer;
import pdp.Raiser;

public class Main {

    private static Raiser[] createRaisers(Integer n, SharedInteger val) {
        Raiser[] newRaisers = new Raiser[n];
        for(int i=0;i<n;i++){
            newRaisers[i] = new Raiser(val);
        }

        return newRaisers;
    }

    private static Reducer[] createReducers(Integer n, SharedInteger val) {
        Reducer[] newReducers = new Reducer[n];
        for(int i=0;i<n;i++){
            newReducers[i] = new Reducer(val);
        }

        return newReducers;
    }

    public static void main(String[] args) {
        SharedInteger val = new SharedInteger();
        Subject s = new Subject();
        Thread subjectThread = new Thread(s);
        Integer[] poolValues = {1, 5, 10, 15};

        Reducer[] reducers = createReducers(5, val);

        Raiser[] raisers = createRaisers(4, val);

        Thread[] threads = new Thread[raisers.length+reducers.length];

        for(int i=0; i<raisers.length; i++) {
            Raiser raiser = raisers[i];
            threads[i] = new Thread(raiser);
            s.subscribe(raiser);
            threads[i].start();
        }
        for(int i=0; i<reducers.length; i++) {
            Reducer reducer = reducers[i];
            threads[raisers.length+i] = new Thread(reducer);
            s.subscribe(reducer);
            threads[raisers.length+i].start();
        }

        for(Integer poolValue : poolValues) {
            s.insertOnPool(poolValue);
        }

        subjectThread.start();

        for(Thread thread : threads) {
            try{
                thread.join();
            } catch (InterruptedException ex){
            }
        }

        System.out.println(val.getVal());
    }

}
