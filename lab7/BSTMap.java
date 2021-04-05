import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {
    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(K key, V value) {

    }

    public void printInOrder(){

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
