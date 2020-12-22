import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void randomTest(){
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        // @source: StudentArrayDequeLauncher
        for (int i = 0; i < 99; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {

            } else if (numberBetweenZeroAndOne < 0.5) {

            } else if (numberBetweenZeroAndOne < 0.75) {

            }else{

            }
        }

    }

}
