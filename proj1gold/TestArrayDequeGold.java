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
                student.addLast(i);
                solution.addLast(i);
                int lastIndex = solution.size() - 1;
                assertEquals(solution.get(lastIndex), student.get(lastIndex));

            } else if (numberBetweenZeroAndOne < 0.5) {
                student.addFirst(i);
                solution.addFirst(i);
                assertEquals(solution.get(0), student.get(0));

            } else if (numberBetweenZeroAndOne < 0.75) {
                if(student.size() > 0){
                    Integer std = student.removeLast();
                    Integer sln = student.removeLast();
                    assertEquals(solution.get(0), student.get(0));
                }
            }else{
                if(student.size() > 0){
                    Integer std = student.removeFirst();
                    Integer sln = student.removeFirst();
                    assertEquals(solution.get(0), student.get(0));
                }
            }
        }

    }

}
