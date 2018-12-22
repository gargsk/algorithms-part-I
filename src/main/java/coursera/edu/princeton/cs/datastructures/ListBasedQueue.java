package coursera.edu.princeton.cs.datastructures;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListBasedQueue<T> implements Iterable<T> {

    Node first;
    Node last;
    int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.item = t;
        last.next = null;
        if (isEmpty())
            first = last;
        else {
            oldLast.next = last;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        T item = first.item;
        first = first.next;
        size--;
        if (isEmpty())
            last = null;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node currNode = first;

            @Override
            public boolean hasNext() {
                return currNode != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T item = currNode.item;
                currNode = currNode.next;
                return item;
            }
        };
    }

    private class Node {
        T item;
        Node next;
    }

    public static void main(String[] args) {
        ListBasedQueue<String> queue = new ListBasedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
