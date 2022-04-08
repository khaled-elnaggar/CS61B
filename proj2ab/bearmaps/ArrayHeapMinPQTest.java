package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.jupiter.api.RepeatedTest;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    ArrayHeapMinPQ<String> arrayHeapMinPQ;

    @Before
    public void init() {
        arrayHeapMinPQ = new ArrayHeapMinPQ<>();
    }

    @Test
    public void whenAddExistingItemThenThrow() {
        arrayHeapMinPQ.add("hamada", 10);
        assertThrows(IllegalArgumentException.class, () -> {
            arrayHeapMinPQ.add("hamada", 10);
        });
    }

    @Test
    public void whenGetEmptyArrayThenThrow() {
        assertThrows(NoSuchElementException.class, () -> {
            arrayHeapMinPQ.getSmallest();
        });
        assertThrows(NoSuchElementException.class, () -> {
            arrayHeapMinPQ.removeSmallest();
        });

    }

    @Test
    public void addAndGetItems() {
        arrayHeapMinPQ.add("som3a", 5);
        arrayHeapMinPQ.add("3bdo", 3);
        assertEquals("3bdo", arrayHeapMinPQ.getSmallest());
        assertEquals(2, arrayHeapMinPQ.size());

        arrayHeapMinPQ.add("habiba", 10);
        arrayHeapMinPQ.add("salma", 1);
        assertEquals("salma", arrayHeapMinPQ.getSmallest());
        assertEquals(4, arrayHeapMinPQ.size());

        arrayHeapMinPQ.add("hamada", 0);
        arrayHeapMinPQ.add("kiiki", 3);
        assertEquals("hamada", arrayHeapMinPQ.getSmallest());
        assertEquals(6, arrayHeapMinPQ.size());

        arrayHeapMinPQ.add("new", 10);
        arrayHeapMinPQ.add("new123", 0);
        assertEquals("hamada", arrayHeapMinPQ.getSmallest());
        assertEquals(8, arrayHeapMinPQ.size());

    }


    @Test
    public void removeSmallest() {
        arrayHeapMinPQ.add("som3a", 5);
        arrayHeapMinPQ.add("3bdo", 3);
        assertEquals("3bdo", arrayHeapMinPQ.removeSmallest());
        assertEquals(1, arrayHeapMinPQ.size());

        arrayHeapMinPQ.add("salma", 1);
        assertEquals("salma", arrayHeapMinPQ.removeSmallest());
        assertEquals(1, arrayHeapMinPQ.size());


        arrayHeapMinPQ.add("habiba", 10);
        arrayHeapMinPQ.add("hamada", 0);
        arrayHeapMinPQ.add("kiiki", 3);
        arrayHeapMinPQ.add("new", 10);
        arrayHeapMinPQ.add("new123", 0);

        assertEquals("hamada", arrayHeapMinPQ.removeSmallest());
        assertEquals(5, arrayHeapMinPQ.size());

        final int size = arrayHeapMinPQ.size();
        for (int i = 0; i < size; i++) {
            arrayHeapMinPQ.removeSmallest();
        }

        assertEquals(0, arrayHeapMinPQ.size());
        assertThrows(NoSuchElementException.class, () -> arrayHeapMinPQ.removeSmallest());
        assertThrows(NoSuchElementException.class, () -> arrayHeapMinPQ.getSmallest());
    }

    @Test
    public void testChangePriority() {
        arrayHeapMinPQ.add("som3a", 5);
        arrayHeapMinPQ.add("salma", 1);
        arrayHeapMinPQ.add("habiba", 10);
        arrayHeapMinPQ.add("kiiki", 3);
        arrayHeapMinPQ.add("new123", 0);
        arrayHeapMinPQ.add("3bdo", 3);

        arrayHeapMinPQ.print();

        arrayHeapMinPQ.changePriority("new123", 2);
        assertEquals("salma", arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.print();

        arrayHeapMinPQ.changePriority("habiba", -1);
        assertEquals("habiba", arrayHeapMinPQ.getSmallest());

        arrayHeapMinPQ.changePriority("salma", -2);
        assertEquals("salma", arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.print();
    }
    @Test
    public void exactTest(){
        ArrayHeapMinPQ<Integer> integerArrayHeapMinPQ = new ArrayHeapMinPQ<>();
        integerArrayHeapMinPQ.add(4, 64);
        integerArrayHeapMinPQ.add(3, 66);
        integerArrayHeapMinPQ.changePriority(1, 66);
        integerArrayHeapMinPQ.removeSmallest();
        //throws out of bounds
    }
    @RepeatedTest(100)
    public void randomTest() {
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> integerArrayHeapMinPQ = new ArrayHeapMinPQ<>();

        for (int i = 0; i < 5; i++) {
            int r = StdRandom.uniform(4);

            int item = StdRandom.uniform(10);
            int priority = StdRandom.uniform(100);

            if (r <= 1) {
                if (!naiveMinPQ.contains(item)) {
                    System.out.println("add " + item + " " + priority);
                    naiveMinPQ.add(item, priority);
                    integerArrayHeapMinPQ.add(item, priority);
                }
            }

            if (r == 2) {
                if (naiveMinPQ.contains(item)) {
                    System.out.println("put " + item + " " + priority);
                    naiveMinPQ.changePriority(item, priority);
                    integerArrayHeapMinPQ.changePriority(item, priority);
                }
            }

            if (r == 3) {
                if (naiveMinPQ.size() > 0) {
                    System.out.println("remove smallest");
                    naiveMinPQ.removeSmallest();
                    integerArrayHeapMinPQ.removeSmallest();
                }
            }

            assertEquals(naiveMinPQ.size(), integerArrayHeapMinPQ.size());
            if(naiveMinPQ.size() > 0){
                // sometimes it will throw an error when the items are of the same priority but different order
                assertEquals(naiveMinPQ.getSmallest(), integerArrayHeapMinPQ.getSmallest());
            }
        }
    }
}
