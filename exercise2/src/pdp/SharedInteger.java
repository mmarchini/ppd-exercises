
package pdp;

class SharedInteger {

    private Integer val;

    public SharedInteger(Integer inintalVal) {
        val = inintalVal;
    }

    public SharedInteger() {
        this(0);
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer newVal){
        val = newVal;
    }

}
