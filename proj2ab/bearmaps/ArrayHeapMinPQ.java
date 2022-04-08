package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    HashMap<T, Integer> indexes = new HashMap<>();

    ArrayList<T> items = new ArrayList<>();
    ArrayList<Double> priorities = new ArrayList<>();

    public ArrayHeapMinPQ() {
        items.add(null);
        priorities.add(null);
        indexes.put(null, null);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("" + item + " already exists");
        }

        items.add(item);
        priorities.add(priority);
        indexes.put(item, size() + 1);

        if (size() > 1) {
            swim(size());
        }
    }

    private void swim(int currentIndex) {
        int parentIndex = currentIndex / 2;

        while (parentIndex >= 1) {
            if (priorities.get(parentIndex) > priorities.get(currentIndex)) {
                swap(parentIndex, currentIndex);
            }
            currentIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    private void swap(int parentIndex, int currentIndex) {
        T tempParent = items.get(parentIndex);
        double tempPriority = priorities.get(parentIndex);

        final T currentItem = items.get(currentIndex);
        items.set(parentIndex, currentItem);
        priorities.set(parentIndex, priorities.get(currentIndex));
        indexes.put(currentItem, parentIndex);

        items.set(currentIndex, tempParent);
        priorities.set(currentIndex, tempPriority);
        indexes.put(tempParent, currentIndex);
    }

    @Override
    public boolean contains(T item) {
        return indexes.containsKey(item);
    }

    @Override
    public T getSmallest() {
        throwExceptionIfEmpty();
        return items.get(1);
    }

    private void throwExceptionIfEmpty() {
        if (size() == 0) {
            throw new NoSuchElementException("Size is " + items.size());
        }
    }

    @Override
    public T removeSmallest() {
        throwExceptionIfEmpty();

        T returnItem = items.get(1);

        if (size() == 1) {
            indexes.remove(returnItem);
            priorities.remove(1);
            return items.remove(1);
        }

        final T lastItem = items.remove(size());
        items.set(1, lastItem);
        priorities.set(1, priorities.remove(size()));
        indexes.put(lastItem, 1);
        indexes.remove(returnItem);

        sink(1);
        return returnItem;
    }

    private void sink(int index) {
        int ch1 = index * 2;

        while (ch1 <= size()) {
            int ch2 = ch1 + 1 > size() ? ch1 : ch1 + 1;
            int lowestChildIndex = priorities.get(ch1) < priorities.get(ch2) ? ch1 : ch2;
            if (priorities.get(lowestChildIndex) < priorities.get(index)) {
                swap(lowestChildIndex, index);
                index = lowestChildIndex;
                ch1 = index * 2;
            }else{
                break;
            }
        }

    }

    @Override
    public int size() {
        return indexes.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        int itemIndex = indexes.get(item);
        priorities.set(itemIndex, priority);

        sink(itemIndex);
        swim(itemIndex);
    }

    public void print() {
        int depth = ((int) (Math.log(items.size()) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < items.size(); i++) {
            System.out.printf("%s (%.1f)    ", items.get(i), priorities.get(i));
            if (i == itemsUntilNext) {
                System.out.println();
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }

        System.out.println("");
        System.out.println(indexes);
    }

    public static void main(String[] args) {

    }
}
