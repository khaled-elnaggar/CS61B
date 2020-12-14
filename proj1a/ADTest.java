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

        assertEquals("0", ad.get(0));
        assertEquals("3", ad.get(3));
        assertNull(ad.get(-1));
        assertNull(ad.get(4));
    }


}
