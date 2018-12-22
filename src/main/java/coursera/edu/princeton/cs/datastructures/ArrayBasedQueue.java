package coursera.edu.princeton.cs.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBasedQueue<T> implements Iterable<T> {

    int first;
    int last;
    T[] storage;
    int size;

    public ArrayBasedQueue() {
        storage = (T[]) new Object[2];
    }

    public void enqueue(T t) {
        if (size == storage.length)
            resize(2 * storage.length);
        storage[last++] = t;
        if (last == storage.length) last = 0;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        T item = storage[first];
        storage[first] = null;
        size--;
        first++;
        if (first == storage.length)
            first = 0;
        if (size == storage.length / 4)
            resize(storage.length / 2);
        return item;
    }

    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return storage[first - 1];
    }

    private void resize(int capacity) {
        assert capacity >= size;
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = storage[(first + i) % storage.length];
        }
        storage = temp;
        first = 0;
        last  = size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
