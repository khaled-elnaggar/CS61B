import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        Arrays.stream(saStrings).forEach(t::add);
        assertEquals(4, t.size());

        Arrays.stream(otherStrings).forEach(t::add);
        assertEquals(7, t.size());

        List<String> keys = t.keysWithPrefix("sa");
        assertNotNull(keys);

        assertTrue(Arrays.stream(saStrings).allMatch(keys::contains));
        assertTrue(Arrays.stream(otherStrings).noneMatch(keys::contains));
    }

    @Test
    public void sanityLongestPrefixTest() {
        String[] saStrings =
                new String[]{"same", "sam", "sad", "sap",
                        "kito", "kiki", "kikiana"};

        MyTrieSet t = new MyTrieSet();
        Arrays.stream(saStrings).forEach(t::add);
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
        Arrays.stream(saStrings).forEach(t::add);

        List<String> matchingKeys2 = t.keysThatMatch("s..");
        final List<String> expectedMatch2 = List.of("she", "see", "sam", "sap", "sad");
        assertEquals(expectedMatch2.size(), matchingKeys2.size());
        assertTrue(expectedMatch2.containsAll(matchingKeys2));

        List<String> matchingKeys = t.keysThatMatch("s.e");
        final List<String> expectedMatch = List.of("she", "see");
        assertEquals(expectedMatch.size(), matchingKeys.size());
        assertTrue(expectedMatch.containsAll(matchingKeys));

        List<String> matchingKeys3 = t.keysThatMatch("..nx");
        final List<String> expectedMatch3 = List.of("ninx", "canx", "plnx");
        assertEquals(expectedMatch3.size(), matchingKeys3.size());
        assertTrue(expectedMatch3.containsAll(matchingKeys3));
    }

    @Test
    public void deleteAllKeys() {
        MyTrieSet t = new MyTrieSet();
        String[] saStrings =
                new String[]{"abcdefg", "ab", "abx", "aby"};

        Arrays.stream(saStrings).forEach(t::add);

        Arrays.stream(saStrings).forEach(t::delete);

        assertFalse(t.contains("abcdefg"));
        assertEquals(0, t.size());
    }

    @Test
    public void deleteOneKeys() {
        MyTrieSet t = new MyTrieSet();
        final String keyToBeDeleted = "abcdefg";
        String[] strings =
                new String[]{keyToBeDeleted, "ab", "abx", "aby"};

        Arrays.stream(strings).forEach(t::add);

        t.delete(keyToBeDeleted);

        assertFalse(t.contains(keyToBeDeleted));
        assertEquals(strings.length - 1, t.size());
        assertTrue(t.contains("ab"));
        assertTrue(t.contains("abx"));
        assertTrue(t.contains("aby"));
    }
}
