/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    public void add(double x) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == x) {
                return;
            }
        }
        items.addLast(x);
    }

    public double floor(double x) {
        if(items.size() == 0){
            return Double.NEGATIVE_INFINITY;
        }
        double ret = items.get(0);
        for(int i = 1; i < items.size(); i++){
            if(items.get(i) < x && items.get(i) > ret){
                ret = items.get(i);
            }
        }
        return ret;
    }
}
