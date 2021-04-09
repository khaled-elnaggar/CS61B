import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node sentinel;
    private int size;

    private class Node<K, V> {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int N;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            N = 1;
        }
    }

    public BSTMap() {
        // The root is always at sentinel.right
        sentinel = new Node(null, null);
        size = 0;
    }

    @Override
    public void clear() {
        sentinel.right = null;
        size = 0;
    }

    private void validate(K key) {
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
    }

    @Override
    public boolean containsKey(K key) {
        validate(key);
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        validate(key);
        return (V) get(key, sentinel.right);
    }

    private V get(K key, Node node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo((K) node.key);
        if (cmp < 0){
            return (V) get(key, node.left);
        } else if (cmp > 0){
            return (V) get(key, node.right);
        } else {
            return (V) node.value;
        }
    }

    @Override
    public int size() {
        return size;
    }
    private int size(Node n){
        if(n == null){
            return 0;
        }
        return size(n.left) + size(n.right) + 1;
    }
    @Override
    public void put(K key, V value) {
        validate(key);
        if(value == null){
            remove(key);
            return;
        }
        sentinel.right = put(key, value, sentinel.right);
    }

    private Node put(K key, V value, Node node) {
        if (node == null) {
            size += 1;
            return new Node(key, value);
        }

        int cmp = key.compareTo((K) node.key);
        if(cmp == 0){
            node.value = value;
        }
        if (cmp < 0) {
            node.left = put(key, value, node.left);
        } else if (cmp > 0) {
            node.right = put(key, value, node.right);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void printInOrder(){
        printInOrder(sentinel.right);
    }
    private void printInOrder(Node n) {
        if (n == null) {
            return;
        }

        System.out.print (n.key + " ");
        printInOrder(n.left);
        printInOrder(n.right);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        keySet(keySet, sentinel.right);
        return keySet;
    }

    private void keySet(Set<K> keySet, Node n){
        if (n == null) {
            return;
        }

        keySet.add((K) n.key);
        keySet(keySet, n.left);
        keySet(keySet, n.right);
    }

    private Node maxLeft(Node n){
        if(n.right == null){
            return n;
        }
        return maxLeft(n.right);
    }

    private Node minRight(Node n) {
        if(n.left == null){
            return n;
        }
        return minRight(n.left);
    }

    @Override
    public V remove(K key) {
        validate(key);
        V retVal = get(key);

        if(retVal == null){
            return null;
        } else {
            sentinel.right = remove(key, sentinel.right);
            size -= 1;
            return retVal;
        }
    }

    private Node remove(K key, Node node) {
        int cmp = key.compareTo((K) node.key);

        // Found the key-value pair we're looking for
        if(cmp == 0){
            // In case it has 2 children
            if (node.right != null && node.left != null) {
                Node newRoot = (node.left != null) ? maxLeft(node.left) : minRight(node.right);
                remove((K) newRoot.key, node);
                newRoot.right = node.right;
                newRoot.left = node.left;
                newRoot.N = size(newRoot.left) + size(newRoot.right) + 1;
                return newRoot;
            }

            // In case there is only a single child or leaf
            if(node.right != null){
                return node.right;
            } else if(node.left != null){
                return node.left;
            } else {
                return null;
            }
        }

        if(cmp < 0){
            node.left = remove(key, node.left);
        } else {
            node.right = remove(key, node.right);
        }

        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Working on it");
    }

    @Override
    public Iterator<K> iterator() {
        return new KeysIterator(this);
    }

    private class KeysIterator implements Iterator {
        Iterator setIterator;
        public KeysIterator(BSTMap<K, V> bst){
            setIterator = bst.keySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return setIterator.hasNext();
        }

        @Override
        public K next() {
            return (K) setIterator.next();
        }
    }
    public K ceiling(K key){
        Node n = ceiling(key, sentinel.right);
        if(n != null){
            return (K) n.key;
        } else {
            return null;
        }
    }

    private Node ceiling(K key, Node n) {
        if(n == null){
            return null;
        }
        Node t = null;
        int cmp = ((K) n.key).compareTo(key);
        if (cmp >= 0){
            t = ceiling(key, n.left);
        } else {
            return ceiling(key, n.right);
        }
        if(t == null){
            return n;
        } else {
            return t;
        }
    }

    public K floor(K key){
        Node ret = floor(key, sentinel.right);
        if(ret != null){
            return (K) ret.key;
        } else {
            return null;
        }
    }
    private Node floor(K key, Node n){
        if(n == null){
            return null;
        }
        int cmp = ((K) n.key).compareTo(key);

        Node t = null;

        if(cmp <= 0){
            t = floor(key, n.right);
        } else {
           return floor(key, n.left);
        }

        if(t == null){
            return n;
        } else {
            return t;
        }
    }

    public K select(int r){
        return (K) select(r + 1, sentinel.right);
    }

    private K select(int r, Node n) {
        int leftSize = size(n.left);
        if(leftSize > r){
            return (K) select(r, n.left);
        } else if (leftSize < r){
            return (K) select(r - (n.N - leftSize), n.right);
        } else {
            return (K) n.key;
        }
    }

    public static void main(String[] args){
        BSTMap<Integer, Double> bst = new BSTMap<>();
        bst.put(5, 0.0);
        bst.put(3, 0.0);
        bst.put(4, 0.0);
        bst.put(1, 0.0);
        bst.put(7, 0.0);
        bst.put(6, 0.0);
        bst.put(8, 0.0);
        bst.printInOrder();

        for(Integer key : bst){
            System.out.println(key);
        }

        bst.remove(3);
        bst.remove(8);
        bst.remove(4);
    }
}
