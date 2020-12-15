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

        for (int i = 4; i < 100000; i++) {
            lld.addLast(Integer.toString(i));
        }

        assertEquals("0", lld.get(0));
        assertEquals("3", lld.get(3));
        assertEquals("8520", lld.get(8520));
        assertEquals("98303", lld.get(98303));
        assertEquals("99999", lld.get(99999));
        assertNull(lld.get(-1));
        assertNull(lld.get(9999999));
    }

    @Test
    public void testIncSize() {
        lld.addLast("4");
        lld.addLast("5");
        lld.addLast("6");
        lld.addFirst("3");
        lld.addLast("7");
        lld.addFirst("2");
        lld.addLast("8");
        lld.addFirst("1");
        lld.addLast("9");
        lld.addFirst("0");
        lld.printDeque();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.removeFirst();
        lld.removeLast();
        lld.addLast("2");
        lld.addFirst("3");
        lld.addLast("2");
        lld.addFirst("3");
        lld.addLast("2");
        lld.addFirst("3");
        lld.removeFirst();
        lld.removeLast();
        lld.removeLast();
        lld.removeLast();
        lld.removeLast();
        lld.addLast("2");
        lld.addFirst("3");
        lld.addLast("2");
        lld.addFirst("3");
    }

    @Test
    public void addFirstRemoveLast() {
        lld.addFirst("2");
        lld.addFirst("1");
        lld.addFirst("0");
        assertEquals("2", lld.removeLast());
        assertEquals("1", lld.removeLast());
        assertEquals("0", lld.removeLast());
        assertNull(lld.removeLast());
        assertNull(lld.removeLast());
        assertTrue(lld.isEmpty());


        lld.addFirst("2");
        assertEquals("2", lld.removeLast());
        lld.addFirst("1");
        assertEquals("1", lld.removeLast());
        lld.addFirst("0");
        assertEquals("0", lld.removeLast());
        assertNull(lld.removeLast());
        assertTrue(lld.isEmpty());
    }

    @Test
    public void addFirstAddLastRemoveFirst() {
        lld.addFirst("2");
        lld.addFirst("1");
        lld.addFirst("0");
        assertEquals("0", lld.removeFirst());
        assertEquals("1", lld.removeFirst());
        assertEquals("2", lld.removeFirst());

        lld.addFirst("2");
        assertEquals("2", lld.removeFirst());
        lld.addFirst("1");
        assertEquals("1", lld.removeFirst());
        lld.addFirst("0");
        assertEquals("0", lld.removeFirst());

        lld.addLast("0");
        lld.addLast("1");
        lld.addLast("2");
        assertEquals("0", lld.removeFirst());
        assertEquals("1", lld.removeFirst());
        assertEquals("2", lld.removeFirst());

        lld.addLast("0");
        assertEquals("0", lld.removeFirst());
        lld.addLast("1");
        assertEquals("1", lld.removeFirst());
        lld.addLast("2");
        assertEquals("2", lld.removeFirst());

        lld.addFirst("0");
        lld.addLast("1");
        lld.addLast("2");
        assertEquals("0", lld.removeFirst());
        assertEquals("1", lld.removeFirst());
        assertEquals("2", lld.removeFirst());

        lld.addLast("2");
        lld.addFirst("1");
        lld.addFirst("0");
        assertEquals("0", lld.removeFirst());
        assertEquals("1", lld.removeFirst());
        assertEquals("2", lld.removeFirst());

        lld.addFirst("0");
        assertEquals("0", lld.removeFirst());
        lld.addLast("1");
        assertEquals("1", lld.removeFirst());
        lld.addLast("2");
        assertEquals("2", lld.removeFirst());

    }

    @Test
    public void testRecGet() {
        lld.addFirst("1");
        lld.addLast("2");
        lld.addFirst("0");
        lld.addLast("3");
        for (int i = 0; i < 4; i++) {
            assertEquals(Integer.toString(i), lld.getRecursive(i));
        }
        for (int i = 0; i < 4; i++) {
            lld.removeFirst();
        }

        lld.addFirst("0");
        lld.removeFirst();
        lld.addFirst("2");
        lld.removeFirst();
        lld.addFirst("6");
        lld.addFirst("7");
        lld.getRecursive(0);
        lld.getRecursive(1);
        lld.removeLast();
        lld.removeFirst();
        lld.addLast("12");
        assertEquals("12", lld.getRecursive(0));


    }
}
