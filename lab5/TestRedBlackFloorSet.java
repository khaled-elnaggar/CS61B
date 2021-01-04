import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        StdRandom.uniform(-5000, 5000);
        AListFloorSet als = new AListFloorSet();
        RedBlackFloorSet rbs = new RedBlackFloorSet();
        for(int i = 0; i < 1000000; i++){
            als.add(StdRandom.uniform(-5000.0, 5001.0));
            rbs.add(StdRandom.uniform(-5000.0, 5001.0));
        }
        for(int i = 0; i < 100000; i++){
            assertEquals(als.floor(StdRandom.uniform(-5000, 5001)), rbs.floor(StdRandom.uniform(-5000, 5001)), 0.000001);
        }
    }
}
