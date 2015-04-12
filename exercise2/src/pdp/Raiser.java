
package pdp;

import pdp.AbstractObserver;

public class Raiser extends AbstractObserver{

    public Raiser(SharedInteger _sharedInteger) {
        super(_sharedInteger);
    }

    @Override
    protected Integer operation(Integer a, Integer b) {
        return a + b;
    }
}
