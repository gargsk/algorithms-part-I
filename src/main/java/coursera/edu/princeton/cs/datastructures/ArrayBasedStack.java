package coursera.edu.princeton.cs.datastructures;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBasedStack<T> implements Iterable<T> {
    T[] storage;
    int size;

    public ArrayBasedStack() {
        this.storage = (T[]) new Object[2];
        this.size = 0;
    }

    public static void main(String[] args) {
        ArrayBasedStack<String> stack = new ArrayBasedStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(T item) {
        if (size == storage.length)
            resize();
        storage[size++] = item;
    }

    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        T item = storage[size - 1];
        storage[size - 1] = null;
        size--;
        resize();
        return item;
    }

    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return storage[size];
    }

    private void resize() {
        if (size == storage.length) {
            T[] doubleStorage = (T[]) new Object[2 * storage.length];
            for (int i = 0; i < storage.length; i++) {
                doubleStorage[i] = storage[i];
            }
            storage = doubleStorage;
        } else if (size <= storage.length / 4 + 1) {
            T[] doubleStorage = (T[]) new Object[storage.length / 2];
            for (int i = 0; i < storage.length / 4 + 1; i++) {
                doubleStorage[i] = storage[i];
            }
            storage = doubleStorage;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i;

        public ReverseArrayIterator() {
            i = size - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return storage[i--];
        }
    }

}
