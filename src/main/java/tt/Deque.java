package tt;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n;

    private Node qhead;

    private Node qtail;

    public Deque() {
        this.n = 0;
        this.qhead = null;
        this.qtail = null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null)
            throw new IllegalArgumentException();
        // save the reference to current tail
        Node temp = qtail; // hold the reference of current tail
        qtail = new Node(); // create new node and now tail is pointing to a detached node
        qtail.item = item;  // set the item value to new node
        qtail.next = null;  // this should point to old tail is any
        qtail.previous = temp; // there is no node behind this
        if (isEmpty())
            qhead = qtail;
        else
            temp.next = qtail;
        n++;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null)
            throw new IllegalArgumentException();
        Node temp = qhead;
        qhead = new Node();
        qhead.item = item;
        qhead.previous = null;
        qhead.next = temp;
        if (isEmpty())
            qtail = qhead;
        else
            temp.previous = qhead;
        n++;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = qtail.item;
        qtail = qtail.previous;
        n--;
        if (isEmpty())
            qtail = null;
        else
            qtail.next = null;
        return item;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = qhead.item;
        qhead = qhead.next;
        n--;
        if (isEmpty())
            qhead = null;
        else
            qhead.previous = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new Iterator<Item>() {
            private Node current = qhead;
            @Override
            public boolean hasNext() {
                return current!=null;
            }
            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Item item = current.item;
                current=current.next;
                return item;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private class Node {
        Item item;
        Node next;
        Node previous;
    }
}
