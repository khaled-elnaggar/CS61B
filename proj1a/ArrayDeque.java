public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        final int INITIAL_SIZE = 4;
        items = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        nextFirst = (int) INITIAL_SIZE / 2;
        nextLast = nextFirst + 1;
    }


    private void resize() {
    }

    private void incFirst() {
        if (size == items.length) {
            resize();
            return;
        }
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        resize();
    }
    public void addFirst(T item) {
        items[nextFirst] = item;
        size++;
        incFirst();
    }

    private void incLast() {
        if (size == items.length) {
            resize();
            return;
        }
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }

    }
    public void addLast(T item) {
        items[nextLast] = item;
        size++;
        incLast();
    }

    private void decFirst(){
        if(nextFirst == items.length - 1){
            nextFirst = 0;
        }else{
            nextFirst++;
        }
        resize();
    }
    public T removeFirst() {
        decFirst();
        T ret = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return ret;
    }

    private void decLast(){
        if(nextLast == 0){
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        resize();
    }
    public T removeLast() {
        decLast();
        T ret = items[nextLast];
        items[nextLast] = null;
        size--;
        return ret;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if(index >= size || index < 0){
            return null;
        }
        if(index + nextFirst + 1 < items.length){
            return items[index + nextFirst + 1];
        } else {
            return items[items.length - (index + nextFirst + 1)];
        }
    }

    public void printDeque() {

    }
}

