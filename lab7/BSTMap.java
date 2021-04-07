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
        if(cmp == 0){
            node.value = value;
        }
        if (cmp < 0) {
            node.left = put(key, value, node.left);
        } else if (cmp > 0) {
            node.right = put(key, value, node.right);
        }
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
        if(cmp == 0){

            // in case it had 2 children
            if (node.right != null && node.left != null) {
                Node newRoot = (node.left != null) ? maxLeft(node.left) : minRight(node.right);
                remove((K) newRoot.key, node);
                newRoot.right = node.right;
                newRoot.left = node.left;
                return newRoot;
            }

            // in case there is only a single child or leaf
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
        return node;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Working on it");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Working on it");
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
    }
}
