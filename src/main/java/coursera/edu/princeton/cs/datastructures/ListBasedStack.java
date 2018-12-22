package coursera.edu.princeton.cs.datastructures;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListBasedStack<T> implements Iterable<T> {

    int size;

    Node first = null;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void push(T item) {
        Node oldFirst = first;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = oldFirst;
        size++;
        first=newNode;
        first.next=oldFirst;
    }

    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        T item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return first.item;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    private class Node {
        T item;
        Node next;
    }

    public static void main(String[] args) {
        ListBasedStack<String> stack = new ListBasedStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }

}
