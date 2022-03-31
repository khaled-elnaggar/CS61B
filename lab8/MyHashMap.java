import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    Set<K> keySet = new HashSet<>();
    int size = 0;

    LinkedList[] hashmap;

    public MyHashMap() {
        initializeArray();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        initializeArray();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        initializeArray();
    }

    private void initializeArray() {
        hashmap = new LinkedList[initialSize];
        for (int i = 0; i < hashmap.length; i++) {
            hashmap[i] = new LinkedList<Map.Entry<K, V>>();
        }
    }

    @Override
    public void clear() {
        initializeArray();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key, hashmap);
        LinkedList<Map.Entry<K, V>> list = hashmap[index];
        for (Map.Entry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(K key, LinkedList[] map) {
        int hashValue = key.hashCode();
        int index = Math.floorMod(hashValue, map.length);
        return index;
    }

    @Override
    public void put(K key, V value) {
        resize();
        remove(key);
        putInto(key, value, hashmap);
        size++;
        keySet.add(key);
    }

    private void putInto(K key, V value, LinkedList[] map){
        int index = getIndex(key, map);
        LinkedList<Map.Entry<K, V>> list = map[index];

        Map.Entry<K, V> entry = Map.entry(key, value);
        list.add(entry);
    }

    private void resize() {
        if (size * 1.0 / hashmap.length < 0.75) {
            return;
        }
        int newSize = hashmap.length * 2;
        LinkedList[] newHashMap;
        newHashMap = new LinkedList[newSize];
        for (int i = 0; i < newHashMap.length; i++) {
            newHashMap[i] = new LinkedList<Map.Entry<K, V>>();
        }

        for (int i = 0; i < hashmap.length; i++) {
            LinkedList<Map.Entry<K, V>> oldList = hashmap[i];
            for (Map.Entry<K, V> kvEntry : oldList) {
                K key = kvEntry.getKey();
                V value = kvEntry.getValue();
                putInto(key, value, newHashMap);
            }
        }
        hashmap = newHashMap;
    }


    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }

        int index = getIndex(key, hashmap);
        LinkedList<Map.Entry<K, V>> list = hashmap[index];

        Map.Entry<K, V> entryToRemove = null;
        for (Map.Entry<K, V> kvEntry : list) {
            if (kvEntry.getKey() == key) {
                entryToRemove = kvEntry;
                break;
            }
        }
        list.remove(entryToRemove);
        size--;
        keySet.remove(key);
        return entryToRemove == null ? null : entryToRemove.getValue();
    }

    @Override
    public V remove(K key, V value) {
        if (value.equals(get(key))) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public static void main(String[] args) {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        dictionary.put("hamada", "100");
        System.out.println(dictionary.get("hamada"));
        System.out.println(dictionary.size());
    }
}
