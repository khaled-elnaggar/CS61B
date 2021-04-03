package hw2;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestPercolation {

    @Test
    public void testPerco() {
        Percolation p = new Percolation(4);
        p.open(2, 1);
        assertTrue(p.isOpen(2, 1));
        assertFalse(p.isFull(2, 1));
        assertFalse(p.percolates());


        assertFalse(p.isFull(0, 2));
        p.open(0, 2);
        assertTrue(p.isOpen(0, 2));
        assertTrue(p.isFull(0, 2));
        assertFalse(p.percolates());


        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(3, 3));
        assertFalse(p.isOpen(2, 2));
        p.open(0, 1);
        p.open(1, 1);

        assertTrue(p.isFull(2, 1));
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(0, 1));
        assertTrue(p.isFull(0, 2));
        assertFalse(p.percolates());

        assertFalse(p.isOpen(2, 2));
        assertFalse(p.isOpen(2, 3));
        assertFalse(p.isOpen(3, 3));

        p.open(2, 2);
        assertTrue(p.isFull(0, 1));
        assertFalse(p.percolates());

        p.open(2, 3);
        assertTrue(p.isFull(0, 1));
        assertFalse(p.percolates());

        p.open(3, 3);
        assertTrue(p.percolates());
    }
}
