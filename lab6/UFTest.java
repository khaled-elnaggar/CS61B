import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UFTest {

    @Test
    public void testConstructor() {
        UnionFind uf = new UnionFind(8);
        assertTrue(uf.equals(new int[]{-1, -1, -1, -1, -1, -1, -1, -1}));
        assertEquals(1, uf.sizeOf(0));
        assertEquals(1, uf.sizeOf(7));
        assertEquals(-1, uf.parent(0));
        assertEquals(-1, uf.parent(7));
    }

    @Test
    public void testConnect() {
        UnionFind uf = new UnionFind(9);

        uf.connect(1, 0);
        assertTrue(uf.equals(new int[]{-2, 0, -1, -1, -1, -1, -1, -1, -1}));

        uf.connect(5, 4);
        assertTrue(uf.equals(new int[]{-2, 0, -1, -1, -2, 4, -1, -1, -1}));

        uf.connect(3, 2);
        assertTrue(uf.equals(new int[]{-2, 0, -2, 2, -2, 4, -1, -1, -1}));

        uf.connect(5, 3);
        assertTrue(uf.equals(new int[]{-2, 0, -4, 2, 2, 4, -1, -1, -1}));

        uf.connect(7, 6);
        uf.connect(6, 8);
        assertTrue(uf.equals(new int[]{-2, 0, -4, 2, 2, 4, -3, 6, 6}));

        uf.connect(5, 7);
        assertTrue(uf.equals(new int[]{-2, 0, -7, 2, 2, 4, 2, 6, 6}));

    }

}
