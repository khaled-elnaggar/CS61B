public class ArrayDequeNd<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDequeNd() {
        final int INITIAL_SIZE = 8;
        items = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = items.length - 1;
    }

    private void resize() {
        T[] newArr = (T[]) new Object[items.length * 2];
        for (int i = 0; i < nextFirst; i++) {
            newArr[i] = items[i];
        }
        int amountOfLast = items.length - nextLast - 1;
        System.arraycopy(items, nextLast + 1, newArr, newArr.length - amountOfLast, amountOfLast);
        items = newArr;
        nextLast = items.length - amountOfLast - 1;

    }


    public void addFirst(T item) {
        if (nextFirst + 1 == nextLast) {
            resize();
        }
        items[nextFirst] = item;
        nextFirst++;
        size++;
    }

    public void addLast(T item) {
        if (nextLast - 1 == nextFirst) {
            resize();
        }
        items[nextLast] = item;
        nextLast--;
        size++;
    }


    public T removeFirst() {
        nextFirst--;
        T ret = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return ret;
    }

    public T removeLast() {
        nextLast++;
        T ret = items[nextLast];
        items[nextLast] = null;
        size--;
        return ret;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        if (index < nextFirst) {
            return items[nextFirst - 1 - index];
        } else {
            int indexFromEnd = index - nextFirst;
            return items[items.length - 1 - indexFromEnd];
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }
}
