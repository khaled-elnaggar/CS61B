package es.datastructur.synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("String Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1 == rb.length) ? 0 : last + 1;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("String Buffer underflow");
        }
        T retItem = rb[first];
        first = (first + 1 == rb.length) ? 0 : first + 1;
        fillCount -= 1;
        return retItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("String Buffer underflow");
        }
        T retItem = rb[first];
        return retItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    private class ARBIterator implements Iterator<T> {
        int pos;
        int passed;

        public ARBIterator() {
            pos = first;
            passed = 0;
        }

        @Override
        public boolean hasNext() {
            return passed < fillCount;
        }

        @Override
        public T next() {
            T retItem = rb[pos];
            pos = (pos + 1 == rb.length) ? 0 : pos + 1;
            passed += 1;
            return retItem;
        }

    }

    /**
     * Return size of the buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return the number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != ArrayRingBuffer.class){
            return false;
        }
        ArrayRingBuffer<T> ARB = (ArrayRingBuffer<T>) o;
        if(this.fillCount() != ARB.fillCount()){
            return false;
        }
        Iterator<T> thisIterator = iterator();
        Iterator<T> otherIterator = ARB.iterator();
        while(thisIterator.hasNext() && otherIterator.hasNext()){
            if(thisIterator.next() != otherIterator.next()){
                return false;
            }
        }
        return true;
    }
}
