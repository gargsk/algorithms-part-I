package tt;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n;

    public RandomizedQueue() {
        // construct an empty randomized queue
        this.q = (Item[]) new Object[1];
        this.n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size() == q.length)
            resize(q.length * 2);
        q[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        // generate a random number
        int randomIndx = StdRandom.uniform(size());
        Item returnItem = q[randomIndx];
        if (randomIndx == size() - 1) {
            q[randomIndx] = null;
        } else {
            q[randomIndx] = q[size() - 1];
            q[size() - 1] = null;
        }
        if (size() == q.length / 4)
            resize(q.length / 2);
        n--;
        return returnItem;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        if (isEmpty())
            throw new NoSuchElementException();
        int randomIndx = StdRandom.uniform(size());
        Item returnItem = q[randomIndx];
        return returnItem;
    }

    private void resize(int capacity) {
        Item[] tempArray = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempArray[i] = q[i];
        }
        q = tempArray;
    }

    @Override
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        int size = n;
        int[] ind;

        public RandomIterator() {
            this.ind = new int[size];
            for (int i = 0; i < n; i++) {
                ind[i] = i;
            }
            StdRandom.shuffle(ind);
        }

        @Override
        public boolean hasNext() {
            return size < 0;
        }

        @Override
        public Item next() {
            {
                if (!hasNext())
                    throw new java.util.NoSuchElementException();
                return q[ind[size++]];
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
