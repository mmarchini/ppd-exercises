
package pdp;

import pdp.AbstractObserver;

public class Reducer extends AbstractObserver{

    public Reducer(SharedInteger _sharedInteger) {
        super(_sharedInteger);
    }

    @Override
    protected Integer operation(Integer a, Integer b) {
        return a - b;
    }

}
