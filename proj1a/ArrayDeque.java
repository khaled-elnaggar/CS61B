public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int lastEle;
    private CASE arrayCase;

    enum CASE {
        // Case ONE
        //                   first      last
        //   [ ] [ ] [ ] [ ] [ ] [x] [x] [ ] [ ] [ ] [ ]
        ONE,

        //          last     first
        //   [x] [x] [ ] [ ] [ ] [x] [x] [x] [x] [x] [x]
        //                              last first
        //   [x] [x] [x] [x] [x] [x] [x] [ ] [ ] [x] [x]
        TWO,
    }

    public ArrayDeque() {
        final int initialSize = 8;
        items = (T[]) new Object[initialSize];
        size = 0;
        nextFirst = 0;
        nextFirst = (int) initialSize / 2;
        nextLast = nextFirst + 1;
        lastEle = initialSize - 1;
        arrayCase = CASE.ONE;
    }

    private void checkIncSize() {
        if (size == items.length - 2) {
            resize(items.length * 2);
            return;
        }
    }

    private void checkDecSize() {
        if (size * 1.0 / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
            return;
        }
    }

    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];

        int oldIndex = nextFirst + 1;
        int newIndex = newSize / 4;
        nextFirst = newIndex - 1;

        if (arrayCase == CASE.ONE) {
            while (oldIndex < nextLast) {
                newArr[newIndex] = items[oldIndex];
                oldIndex++;
                newIndex++;
            }

        } else if (arrayCase == CASE.TWO) {
            while (oldIndex < items.length) {
                newArr[newIndex] = items[oldIndex];
                oldIndex++;
                newIndex++;
            }

            oldIndex = 0;
            while (oldIndex < nextLast) {
                newArr[newIndex] = items[oldIndex];
                oldIndex++;
                newIndex++;
            }
        }
        items = newArr;
        nextLast = newIndex;
        lastEle = newSize - 1;
        arrayCase = CASE.ONE;
    }

    public void addFirst(T item) {
        checkIncSize();
        items[nextFirst] = item;
        size++;

        if (nextFirst == 0) {
            nextFirst = lastEle;
            arrayCase = CASE.TWO;
        } else {
            nextFirst--;
        }
    }


    public void addLast(T item) {
        checkIncSize();
        items[nextLast] = item;
        size++;

        if (nextLast == lastEle) {
            nextLast = 0;
            arrayCase = CASE.TWO;
        } else {
            nextLast++;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst == lastEle) {
            nextFirst = 0;
            arrayCase = CASE.ONE;
        } else {
            nextFirst++;
        }
        T ret = items[nextFirst];
        items[nextFirst] = null;
        size--;
        checkDecSize();
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = lastEle;
            arrayCase = CASE.ONE;
        } else {
            nextLast--;
        }
        T ret = items[nextLast];
        items[nextLast] = null;
        size--;
        checkDecSize();
        return ret;
    }

    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        if (arrayCase == CASE.ONE) {
            return items[nextFirst + 1 + index];
        } else {
            if (nextFirst + 1 + index <= lastEle) {
                return items[nextFirst + 1 + index];
            } else {
                return items[nextFirst + index - lastEle];
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (arrayCase == CASE.ONE) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }


}
