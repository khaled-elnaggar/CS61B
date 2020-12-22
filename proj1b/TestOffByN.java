import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    @Test
    public void OffBy1(){
        OffByN offBy1 = new OffByN(1);
        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('b', 'a'));
        assertTrue(offBy1.equalChars('r', 'q'));
        assertTrue(offBy1.equalChars('q', 'r'));
        assertFalse(offBy1.equalChars('a', 'x'));
        assertFalse(offBy1.equalChars('m', 't'));
        assertFalse(offBy1.equalChars('m', 'm'));
    }

    @Test
    public void OffBy2() {
        OffByN offBy2 = new OffByN(2);
        assertTrue(offBy2.equalChars('a', 'c'));
        assertTrue(offBy2.equalChars('d', 'b'));
        assertTrue(offBy2.equalChars('t', 'v'));
        assertTrue(offBy2.equalChars('v', 't'));
        assertFalse(offBy2.equalChars('a', 'a'));
        assertFalse(offBy2.equalChars('a', 'b'));
        assertFalse(offBy2.equalChars('a', 'm'));
    }


}
