import static org.junit.Assert.*;

import org.junit.Test;

public class ADTest {

    ArrayDeque<String> ad = new ArrayDeque<>();

    @Test
    public void testListInitialization() {
        assertNotNull(ad);
        assertEquals(0, ad.size());
        assertTrue(ad.isEmpty());
    }

    @Test
    public void testAddRemoveFirst() {
        ad.addFirst("second");
        assertEquals(1, ad.size());
        assertFalse(ad.isEmpty());
        ad.addFirst("first");
        assertEquals(2, ad.size());
        assertFalse(ad.isEmpty());
        String actualFirst = ad.removeFirst();
        assertEquals(1, ad.size());
        assertEquals("first", actualFirst);
        assertFalse(ad.isEmpty());
        actualFirst = ad.removeFirst();
        assertEquals(0, ad.size());
        assertTrue(ad.isEmpty());
        assertEquals("second", actualFirst);
    }

    @Test
    public void testAddRemoveLast() {
        ad.addLast("middle");
        assertEquals(1, ad.size());
        assertFalse(ad.isEmpty());
        ad.addFirst("first");
        assertEquals(2, ad.size());
        assertFalse(ad.isEmpty());
        ad.addLast("back");
        String actualLast = ad.removeLast();
        assertEquals(2, ad.size());
        assertEquals("back", actualLast);
        assertFalse(ad.isEmpty());
        actualLast = ad.removeLast();
        assertEquals(1, ad.size());
        assertEquals("middle", actualLast);
    }

    @Test
    public void testGet() {
        ad.addFirst("1");
        ad.addFirst("0");
        ad.addLast("2");
        ad.addLast("3");

        for (int i = 4; i < 100000; i++) {
            ad.addLast(Integer.toString(i));
        }

        assertEquals("0", ad.get(0));
        assertEquals("3", ad.get(3));
        assertEquals("8520", ad.get(8520));
        assertEquals("98303", ad.get(98303));
        assertEquals("99999", ad.get(99999));
        assertNull(ad.get(-1));
        assertNull(ad.get(9999999));
    }

    @Test
    public void testIncSize() {
        ad.addLast("4");
        ad.addLast("5");
        ad.addLast("6");
        ad.addFirst("3");
        ad.addLast("7");
        ad.addFirst("2");
        ad.addLast("8");
        ad.addFirst("1");
        ad.addLast("9");
        ad.addFirst("0");
        ad.printDeque();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.addLast("2");
        ad.addFirst("3");
        ad.addLast("2");
        ad.addFirst("3");
        ad.addLast("2");
        ad.addFirst("3");
        ad.removeFirst();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.removeLast();
        ad.addLast("2");
        ad.addFirst("3");
        ad.addLast("2");
        ad.addFirst("3");
    }


}
