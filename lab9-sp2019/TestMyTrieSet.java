import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jenny Huang on 3/12/19.
 * Extended by Khalid ElNaggar on 4/12/2022
 * SOOOOOOOOOO FUCKING HAPPY
 */
public class TestMyTrieSet {

    // assumes add/contains work
    @Test
    public void sanityClearTest() {
        MyTrieSet t = new MyTrieSet();
        for (int i = 0; i < 455; i++) {
            t.add("hi" + i);
            //make sure put is working via contains
            assertTrue(t.contains("hi" + i));
        }
        t.clear();
        for (int i = 0; i < 455; i++) {
            assertFalse(t.contains("hi" + i));
        }
    }

    // assumes add works
    @Test
    public void sanityContainsTest() {
        MyTrieSet t = new MyTrieSet();
        assertFalse(t.contains("waterYouDoingHere"));
        t.add("waterYouDoingHere");
        assertTrue(t.contains("waterYouDoingHere"));
    }

    // assumes add works
    @Test
    public void sanityPrefixTest() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s : saStrings) {
            t.add(s);
        }
        assertEquals(4, t.size());

        for (String s : otherStrings) {
            t.add(s);
        }
        assertEquals(7, t.size());

        List<String> keys = t.keysWithPrefix("sa");
        assertNotNull(keys);
        for (String s : saStrings) {
            assertTrue(keys.contains(s));
        }
        for (String s : otherStrings) {
            assertFalse(keys.contains(s));
        }
    }

    @Test
    public void sanityLongestPrefixTest() {
        String[] saStrings =
                new String[]{"same", "sam", "sad", "sap",
                        "kito", "kiki", "kikiana"};

        MyTrieSet t = new MyTrieSet();
        for (String s : saStrings) {
            t.add(s);
        }
        assertEquals("same", t.longestPrefixOf("same7aaaaa"));
        assertEquals("kiki", t.longestPrefixOf("kiki3abdo"));
    }

    @Test
    public void keysThatMatch() {
        String[] saStrings =
                new String[]{"same", "sam", "sad", "sap",
                        "she", "see",
                        "ninx", "canx", "plnx", "asdnx", "anx"};

        MyTrieSet t = new MyTrieSet();
        for (String s : saStrings) {
            t.add(s);
        }

        List<String> matchingKeys = t.keysThatMatch("s.e");
        final List<String> expectedMatch = List.of("she", "see");
        assertEquals(expectedMatch.size(), matchingKeys.size());
        assertTrue(expectedMatch.containsAll(matchingKeys));

        List<String> matchingKeys2 = t.keysThatMatch("s..");
        final List<String> expectedMatch2 = List.of("she", "see", "sam", "sap", "sad");
        assertEquals(expectedMatch2.size(), matchingKeys2.size());
        assertTrue(expectedMatch2.containsAll(matchingKeys2));

        List<String> matchingKeys3 = t.keysThatMatch("..nx");
        final List<String> expectedMatch3 = List.of("ninx", "canx", "plnx");
        assertEquals(expectedMatch3.size(), matchingKeys3.size());
        assertTrue(expectedMatch3.containsAll(matchingKeys3));
    }

    @Test
    public void deleteSomeKey() {

    }

    @Test
    public void deleteKey() {
        MyTrieSet t = new MyTrieSet();
        String[] saStrings =
                new String[]{"abcdefg", "ab", "abx", "aby"};

        for (String s : saStrings) {
            t.add(s);
        }

        for (String s : saStrings) {
            t.delete(s);
        }

        assertFalse(t.contains("abcdefg"));
        assertEquals(0, t.size());
    }
}
