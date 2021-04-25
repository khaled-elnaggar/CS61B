import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

/*
 *          5
 *        /   \
 *       3     7
 *      / \   / \
 *     1  4  6  8
 */

/** Tests of optional parts of lab 8. */
public class TestBSTMapExtra {

    /*
    * Sanity test for keySet, only here because it's optional
    */
    @Test
    public void sanityKeySetTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        HashSet<String> values = new HashSet<String>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            values.add("hi" + i);
        }
        assertEquals(455, b.size()); //keys are there
        Set<String> keySet = b.keySet();
        assertTrue(values.containsAll(keySet));
        assertTrue(keySet.containsAll(values));
    }

    /* Remove Test
     *
     * Note for testRemoveRoot:
     *
     * Just checking that c is gone (perhaps incorrectly)
     * assumes that remove is BST-structure preserving.
     *
     * More exhaustive tests could be done to verify
     * implementation of remove, but that would require doing
     * things like checking for inorder vs. preorder swaps,
     * and is unnecessary in this simple BST implementation.
     */
    @Test
    public void testRemoveRoot() {
        BSTMap<String,String> q = new BSTMap<String,String>();
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a"); // a b c d e
        assertTrue(null != q.remove("c"));
        assertFalse(q.containsKey("c"));
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        assertTrue(q.containsKey("e"));
    }

    /* Remove Test 2
     * test the 3 different cases of remove
     */
    @Test
    public void testRemoveThreeCases() {
        BSTMap<String,String> q = new BSTMap<String,String>();
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a");                         // a b c d e
        assertTrue(null != q.remove("e"));      // a b c d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("c"));
        assertTrue(q.containsKey("d"));
        assertTrue(null != q.remove("c"));      // a b d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        q.put("f","a");                         // a b d f
        assertTrue(null != q.remove("d"));      // a b f
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("f"));
    }

    /* Remove Test 3
    *  Checks that remove works correctly on root nodes
    *  when the node has only 1 or 0 children on either side. */
    @Test
    public void testRemoveRootEdge() {
        BSTMap rightChild = new BSTMap();
        rightChild.put('A', 1);
        rightChild.put('B', 2);
        Integer result = (Integer) rightChild.remove('A');
        assertTrue(result.equals(new Integer(1)));
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C'+i), 3+i);
        }
        rightChild.put('A', 100);
        assertTrue(((Integer) rightChild.remove('D')).equals(new Integer(4)));
        assertTrue(((Integer) rightChild.remove('G')).equals(new Integer(7)));
        assertTrue(((Integer) rightChild.remove('A')).equals(new Integer(100)));
        assertTrue(rightChild.size()==9);

        BSTMap leftChild = new BSTMap();
        leftChild.put('B', 1);
        leftChild.put('A', 2);
        assertTrue(((Integer) leftChild.remove('B')).equals(1));
        assertEquals(1, leftChild.size());
        assertEquals(null, leftChild.get('B'));

        BSTMap noChild = new BSTMap();
        noChild.put('Z', 15);
        assertTrue(((Integer) noChild.remove('Z')).equals(15));
        assertEquals(0, noChild.size());
        assertEquals(null, noChild.get('Z'));
    }

    @Test
    public void testFloorCeiling(){
        BSTMap<Character, Integer> index = new BSTMap<>();
        index.put('S', 1);
        index.put('E', 1);
        index.put('X', 1);
        index.put('A', 1);
        index.put('R', 1);
        index.put('C', 1);
        index.put('H', 1);
        index.put('M', 1);
        assertEquals('E', (long) index.floor( 'G'));
        assertEquals('M', (long) index.floor( 'Q'));
        assertEquals('C', (long) index.floor( 'D'));
        assertEquals('X', (long) index.floor( 'Z'));
        assertEquals('X', (long) index.floor( 'X'));
        assertEquals('S', (long) index.floor( 'S'));
        assertEquals('S', (long) index.floor( 'W'));

        assertEquals('C', (long) index.ceiling('B'));
        assertEquals('X', (long) index.ceiling('T'));
        assertEquals('M', (long) index.ceiling('J'));
        assertEquals('E', (long) index.ceiling('E'));
    }

   @Test
   public void testSelect(){
       BSTMap<Integer, Double> bst = new BSTMap<>();
       bst.put(5, 0.0);
       bst.put(3, 0.0);
       bst.put(4, 0.0);
       bst.put(1, 0.0);
       bst.put(7, 0.0);
       bst.put(6, 0.0);
       bst.put(8, 0.0);
       assertEquals(1, (long) bst.select(0));
       assertEquals(3, (long) bst.select(1));
       assertEquals(4, (long) bst.select(2));
       assertEquals(5, (long) bst.select(3));
       assertEquals(6, (long) bst.select(4));
       assertEquals(7, (long) bst.select(5));
       assertEquals(8, (long) bst.select(6));
   }

   @Test
   public void testRank(){
       BSTMap<Integer, Double> bst = new BSTMap<>();
       bst.put(5, 0.0);
       bst.put(3, 0.0);
       bst.put(4, 0.0);
       bst.put(1, 0.0);
       bst.put(7, 0.0);
       bst.put(6, 0.0);
       bst.put(8, 0.0);
       assertEquals(0, (long) bst.rank(1));
       assertEquals(1, (long) bst.rank(3));
       assertEquals(2, (long) bst.rank(4));
       assertEquals(3, (long) bst.rank(5));
       assertEquals(4, (long) bst.rank(6));
       assertEquals(5, (long) bst.rank(7));
       assertEquals(6, (long) bst.rank(8));
       assertTrue((long) bst.rank(-10) < 0);
       assertTrue((long) bst.rank(999) < 0);
   }

    @Test
    public void testRankRange(){
        BSTMap<Integer, Double> bst = new BSTMap<>();
        bst.put(5, 0.0);
        bst.put(3, 0.0);
        bst.put(4, 0.0);
        bst.put(1, 0.0);
        bst.put(7, 0.0);
        bst.put(6, 0.0);
        bst.put(8, 0.0);
        assertTrue(Arrays.asList(1, 3, 4).containsAll(bst.rankRange(0, 2)));
        assertTrue(Arrays.asList(4, 5, 6).containsAll(bst.rankRange(2, 4)));
        assertTrue(Arrays.asList(6, 7, 8).containsAll(bst.rankRange(4, 6)));
        assertTrue(Arrays.asList(7, 8).containsAll(bst.rankRange(5, 10)));
        assertTrue(Arrays.asList(1, 3, 4, 5, 6, 7, 8).containsAll(bst.rankRange(-100, 500)));
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMapExtra.class);
    }
}
