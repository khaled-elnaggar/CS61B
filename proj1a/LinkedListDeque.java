public class LinkedListDeque<T> {

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    public void addFirst(T item) {
        Node<T> newFront = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = newFront;
        sentinel.next = newFront;
        size++;
    }

    public void addLast(T item) {
        Node<T> newBack = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = newBack;
        sentinel.prev = newBack;
        size++;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();

    }

    public T removeFirst() {
        Node<T> ret = sentinel.next;
        sentinel.next = ret.next;
        ret.next.prev = sentinel;
        size--;
        return ret.item;
    }

    public T removeLast() {
        Node<T> ret = sentinel.prev;
        sentinel.prev = ret.prev;
        ret.next = sentinel;
        size--;
        return ret.item;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        Node<T> p = sentinel.next;
        for (int i = 0; i < index && p != sentinel; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node<T> node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
