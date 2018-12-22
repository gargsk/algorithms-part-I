package com.sharad.queues.priority;

import edu.princeton.cs.algs4.StdRandom;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<T> implements Iterable<T> {

    private T[] keys;

    private int size;

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initialCap) {
        this.keys = (T[]) new Object[initialCap + 1];
        this.size = 0;
    }

    public MinPQ(T[] keys) {
        this.keys = (T[]) new Object[keys.length + 1];
        this.size = keys.length;
        for (int i = 1; i < keys.length; i++) {
            this.keys[i + 1] = keys[i];
        }
        // create min heap with sink
        for (int k = size / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMinHeap(1);

    }


    private void sink(int k) {
        // here k is index and child of this will be at 2*k and 2K+1
        // we need to check whether 2*k shoild be less then the heap size
        while (k * 2 < size) {
            // here k is index and child of this will be at 2*k and 2K+1
            int j = 2 * k;
            if (j < size && greater(j, j + 1))
                j++;
            if (!greater(k, j))
                break;
            exchange(k, j);
            k = j;  // update k to go further down the heap
        }
    }

    private boolean greater(int i, int j) {
        return ((Comparable<T>)keys[i]).compareTo(keys[j]) > 0;

    }

    private void exchange(int k, int j) {
        T temp = keys[k];
        keys[k] = keys[j];
        keys[j] = temp;
    }

    public void insert(T key) {
        // check array size
        if (size == keys.length - 1)
            resize(2 * keys.length);
        keys[++size] = key;
        // swim up
        swim(size);
        assert isMinHeap(1);
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void resize(int capacity) {
        assert capacity > size;
        T[] temp = (T[]) new Object[capacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = keys[i];
        }
        keys = temp;
    }

    public T min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return keys[1];
    }

    public T delMin() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        T min = keys[1];
        // put last element at the root then call sink
        exchange(1, size--);
        sink(1);
        keys[size + 1] = null;
        if (size > 0 && (size == keys.length - 1 / 4))
            resize(keys.length / 2);
        assert isMinHeap(1);
        return min;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isMinHeap(int k) {
        if (k > size) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= size && greater(k, left)) return false;
        if (right <= size && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }


    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<T> {
        // create a new pq
        private MinPQ<T> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MinPQ<T>(size());
            for (int i = 1; i <= size; i++)
                copy.insert(keys[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>();
        for (int i = 0; i <= 10; i++) {
            pq.insert(StdRandom.uniform(1, 100));
        }

        System.out.println(pq.size());

        for (Integer i : pq) {
            System.out.print(i.toString() + " ,");
        }
        System.out.println("\n");

        System.out.println(pq.min());

        pq.delMin();

        System.out.println(pq.size());
    }
}
