public class XArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public XArrayDeque() {
        final int initialSize = 8;
        items = (T[]) new Object[initialSize];
        size = 0;
        nextFirst = 0;
        nextLast = items.length - 1;
    }

    private void checkResize() {
        if (nextFirst + 1 == nextLast) {
            resize(items.length * 2);
        }
        if (size * 0.1/items.length <= 0.25 && items.length > 16){
            resize(items.length/2);
        }
    }


    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i < nextFirst; i++) {
            newArr[i] = items[i];
        }

        int newIndex = newArr.length - 1;
        int oldIndex = items.length - 1;
        while (oldIndex > nextLast) {
            newArr[newIndex] = items[oldIndex];
            oldIndex--;
            newIndex--;
        }
        items = newArr;
        nextLast = newIndex;

        /* // Old Implementation
        int amountOfLast = items.length - nextLast - 1;
        System.arraycopy(items, nextLast + 1, newArr, newArr.length - amountOfLast, amountOfLast);
        items = newArr;
        nextLast = items.length - amountOfLast - 1;
        */
    }


    public void addFirst(T item) {
        checkResize();
        items[nextFirst] = item;
        nextFirst++;
        size++;
    }

    public void addLast(T item) {
        checkResize();
        items[nextLast] = item;
        nextLast--;
        size++;
    }


    public T removeFirst() {
        if(size == 0){
            return null;
        }
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        T ret = items[nextFirst];
        items[nextFirst] = null;
        size--;
        checkResize();
        return ret;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        nextLast++;
        if (nextLast > items.length - 1) {
            nextLast = 0;
        }
        T ret = items[nextLast];
        items[nextLast] = null;
        size--;
        checkResize();
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
