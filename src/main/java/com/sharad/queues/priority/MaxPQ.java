package com.sharad.queues.priority;

import java.util.Comparator;
import java.util.Iterator;

public class MaxPQ<T extends Comparable<T>> implements Iterable<T> {

    // in the array representation of a binary max heap
    // Invariant:
        /*
            The element at index 1 (as we leave index 0 empty for ease of calculation)
            of the array is having ma value compare to rest of the elements in heap
            the child of elements at index k will be at indexes(2k and 2k+1)
        */

    private int size;

    private T[] keys;

    private Comparator<T> comparator;

    // constructors
    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int initCapacity) {
        this.keys = (T[]) new Object[initCapacity];
        this.size = 0;
    }

    public MaxPQ(int initCapacity, Comparator<T> comparator) {
        this.keys = (T[]) new Object[initCapacity];
        this.size = 0;
        this.comparator = comparator;
    }

    public MaxPQ(Comparator<T> comparator) {
        this(1);
        this.size = 0;
        this.comparator = comparator;
    }

    public MaxPQ(T[] keys) {
        this.size = keys.length;
        this.keys = (T[]) new Object[keys.length + 1];
        for (int i = 0; i < keys.length; i++) {
            this.keys[i + 1] = keys[i];
        }
        // create max heap by sink operation

    }

    private void sink(int k) {
        /*
            Invariant for this method is
            element at index 2k and 2k+1 should be less then the element at index k
            for indexes 2k lees then the size of heap other wise swap them
            keep doing it until element reaches at right place
         */
        while (2 * k < size) {

        }
    }

    // methods
    public void insert(T key) {
    }

    public T max() {
        return null;
    }

    public T deleteMax() {
        return null;
    }

    public int size() {
        return size - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
