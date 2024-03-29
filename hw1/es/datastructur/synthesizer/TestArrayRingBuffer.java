package es.datastructur.synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(5);
        assertEquals(0, arb.fillCount());
        assertTrue(arb.isEmpty());
        arb.enqueue(4.0);
        double delta = 0.00000001;
        assertEquals(4.0, arb.peek(), delta);
        assertEquals(1, arb.fillCount());
        assertFalse(arb.isEmpty());
        arb.enqueue(-12.0);
        assertEquals(4.0, arb.peek(), delta);
        assertEquals(2, arb.fillCount());
        assertFalse(arb.isEmpty());

        assertEquals(4.0, arb.dequeue(), delta);
        assertEquals(-12.0, arb.peek(), delta);
        assertEquals(1, arb.fillCount());
        assertFalse(arb.isEmpty());
        assertEquals(-12.0, arb.dequeue(), delta);
        assertEquals(0, arb.fillCount());
        assertTrue(arb.isEmpty());

        for (int i = 0; i < 5; i++) {
            arb.enqueue(i * 1.2);
            assertEquals(0.0, arb.peek(), delta);
            assertEquals(i + 1, arb.fillCount());
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i * 1.2, arb.dequeue(), delta);
            assertEquals(4 - i, arb.fillCount());
        }
    }

    @Test
    public void equalityTest() {
        ArrayRingBuffer<Double> arb1 = new ArrayRingBuffer<>(8);
        ArrayRingBuffer<Double> arb2 = new ArrayRingBuffer<>(10);
        arb1.enqueue(Double.NEGATIVE_INFINITY);
        arb1.dequeue();
        arb1.enqueue(Double.NEGATIVE_INFINITY);
        arb1.dequeue();

        for (int i = 0; i < 5; i++) {
            arb1.enqueue((double) i);
            arb2.enqueue((double) i);
        }
        for (double d : arb1) {
            System.out.println(d);
        }
        for (double d : arb2) {
            System.out.println(d);
        }
        assertEquals(arb1, arb2);
    }

}
