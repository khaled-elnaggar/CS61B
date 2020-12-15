import static org.junit.Assert.*;

import org.junit.Test;

public class LLDTest {
    LinkedListDeque<String> lld = new LinkedListDeque<>();

    @Test
    public void testListInitialization() {
        assertNotNull(lld);
        assertEquals(0, lld.size());
        assertTrue(lld.isEmpty());
    }

    @Test
    public void testAddRemoveFirst() {
        lld.addFirst("second");
        assertEquals(1, lld.size());
        assertFalse(lld.isEmpty());
        lld.addFirst("first");
        assertEquals(2, lld.size());
        assertFalse(lld.isEmpty());
        String actualFirst = lld.removeFirst();
        assertEquals(1, lld.size());
        assertEquals("first", actualFirst);
        assertFalse(lld.isEmpty());
        actualFirst = lld.removeFirst();
        assertEquals(0, lld.size());
        assertTrue(lld.isEmpty());
        assertEquals("second", actualFirst);
    }

    @Test
    public void testAddRemoveLast() {
        lld.addLast("middle");
        assertEquals(1, lld.size());
        assertFalse(lld.isEmpty());
        lld.addFirst("first");
        assertEquals(2, lld.size());
        assertFalse(lld.isEmpty());
        lld.addLast("back");
        String actualLast = lld.removeLast();
        assertEquals(2, lld.size());
        assertEquals("back", actualLast);
        assertFalse(lld.isEmpty());
        actualLast = lld.removeLast();
        assertEquals(1, lld.size());
        assertEquals("middle", actualLast);
    }

    @Test
    public void testGet() {
        lld.addFirst("1");
        lld.addFirst("0");
        lld.addLast("2");
        lld.addLast("3");

        assertEquals("0", lld.get(0));
        assertEquals("3", lld.get(3));
        assertNull(lld.get(-1));
        assertNull(lld.get(4));
    }

    @Test
    public void testGetRec() {
        lld.addFirst("1");
        lld.addFirst("0");
        lld.addLast("2");
        lld.addLast("3");

        assertEquals("0", lld.getRecursive(0));
        assertEquals("3", lld.getRecursive(3));
        assertNull(lld.getRecursive(-1));
        assertNull(lld.getRecursive(4));
    }

}
