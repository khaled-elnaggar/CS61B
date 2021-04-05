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

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BSTMap() {
        sentinel = new Node(null, null);
        size = 0;
    }

    @Override
    public void clear() {
        sentinel.right = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
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

    @Override
    public void put(K key, V value) {
        if (size == 0) {
            sentinel.right = new Node(key, value);
            size = 1;
            return;
        }
        put(key, value, sentinel.right);
    }

    private Node put(K key, V value, Node node) {
        if (node == null) {
            size += 1;
            return new Node(key, value);
        }

        int cmp = key.compareTo((K) node.key);
        if (cmp < 0) {
            node.left = put(key, value, node.left);
        } else if (cmp > 0) {
            node.right = put(key, value, node.right);
        }
        return node;
    }

    public void printInOrder() {

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Working on it");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Working on it");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Working on it");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Working on it");
    }
}
