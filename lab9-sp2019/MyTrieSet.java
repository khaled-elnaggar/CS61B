import java.util.*;

public class MyTrieSet implements TrieSet61B {

    Node root = new Node();
    int size = 0;

    public int size() {
        return size;
    }

    private static class Node {
        private boolean isKey = false;

        private final Map<Character, Node> map = new HashMap<>();

        public Node get(char key) {
            return map.get(key);
        }

        public void put(char ch1, Node newNode) {
            map.put(ch1, newNode);
        }

        public boolean isKey() {
            return isKey;
        }

        public void setKey(boolean key) {
            isKey = key;
        }

        public void remove(char key) {
            map.remove(key);
        }

        public Set<Map.Entry<Character, Node>> getEntries() {
            return map.entrySet();
        }
    }

    @Override
    public void add(String key) {
        if (contains(key)) return;
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch1 = key.charAt(i);
            Node subtrie = node.get(ch1);

            if (subtrie == null) {
                subtrie = new Node();
                node.put(ch1, subtrie);
            }

            if (i + 1 == key.length()) {
                subtrie.setKey(true);
            }
            node = subtrie;
        }
        size++;
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch1 = key.charAt(i);
            Node subtrie = node.get(ch1);

            if (subtrie == null) return false;
            if (subtrie.isKey() && i + 1 == key.length()) return true;

            node = subtrie;
        }
        return false;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> matchingKeys = new ArrayList<>();
        Node node = root;
        node = getNodeUntiLastPrefix(prefix, node);
        if (node == null) return new ArrayList<>();
        getAllChildrenKeys(node, matchingKeys, prefix);
        return matchingKeys;
    }

    private Node getNodeUntiLastPrefix(String prefix, Node node) {
        for (int i = 0; i < prefix.length(); i++) {
            char ch1 = prefix.charAt(i);
            Node subtrie = node.get(ch1);
            if (subtrie == null) return null;
            node = subtrie;
        }
        return node;
    }

    private void getAllChildrenKeys(Node node, List<String> matchingKeys, String prefix) {
        if (node == null) return;

        if (node.isKey) {
            matchingKeys.add(prefix);
        }

        node.getEntries().forEach(characterNodeEntry -> {
            getAllChildrenKeys(characterNodeEntry.getValue(), matchingKeys, prefix + characterNodeEntry.getKey());
        });
    }

    public List<String> keys() {
        return keysWithPrefix("");
    }

    @Override
    public String longestPrefixOf(String key) {
        Node node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch1 = key.charAt(i);
            Node subtrie = node.get(ch1);
            if (subtrie == null) return key.substring(0, i);
            node = subtrie;
        }
        return "";
    }

    public List<String> keysThatMatch(String pattern) {
        List<String> matchingKeys = new ArrayList<>();
        keysThatMatch(pattern, matchingKeys, "", root);
        return matchingKeys;
    }

    private void keysThatMatch(String s, List<String> matchingKeys, String key, Node node) {
        if (s.length() == 0) {
            if (node.isKey())
                matchingKeys.add(key);
            return;
        }

        char c = s.charAt(0);
        Set<Node> matchingSets = new HashSet<>();
        if (c == '.') {
            node.getEntries().forEach(characterNodeEntry -> {
                keysThatMatch(s.substring(1), matchingKeys, key + characterNodeEntry.getKey(), characterNodeEntry.getValue());
            });
        } else {
            final Node node1 = node.get(c);
            if (node1 == null)
                return;
            keysThatMatch(s.substring(1), matchingKeys, key + c, node1);
        }
    }

    public void delete(String s) {
        if (!contains(s)) return;
        size = size - 1;
        delete(s, root);
    }

    private boolean delete(String s, Node node) {
        if (s.length() == 0) {
            if (!node.isKey()) {
                throw new RuntimeException("Sanity fail! This node should be the key node!");
            }

            node.setKey(false);
            return node.getEntries().isEmpty();
        }
        final char currChar = s.charAt(0);
        boolean shouldCutTree = delete(s.substring(1), node.get(currChar));


        if (shouldCutTree && node.getEntries().size() > 1 || shouldCutTree && node.equals(root)) {
            node.remove(currChar);
            return false;
        }
        return shouldCutTree;
    }
}
