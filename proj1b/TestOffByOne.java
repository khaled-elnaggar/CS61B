import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('q', 'r'));
        assertTrue(offByOne.equalChars(';', ':'));
        assertTrue(offByOne.equalChars('(', ')'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('a', 'x'));
        assertFalse(offByOne.equalChars('m', 't'));
        assertFalse(offByOne.equalChars('m', 'm'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('B', 'a'));
        assertFalse(offByOne.equalChars('?', '%'));
        assertFalse(offByOne.equalChars('+', '='));
    }
}
