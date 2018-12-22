package com.sharad.symboltables;

import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<? super K>, V> implements SymbolTable<K, V> {

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    @Override
    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Put method called with null key");
        if (value == null)
            delete(key); // null value will remove the key if it exists
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value, 0);
            return node;
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {// move right
                node.right = put(node.right, key, value);
            }
            if (cmp < 0) {// move left
                node.left = put(node.left, key, value);
            }
            node.value = value; // replace value
            node.size = 1 + node.left.size + node.right.size;
            return node;
        }
    }

    @Override
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Get method called with null key");
        return get(root, key);

    }

    private V get(Node x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) { // move right
            return get(x.right, key);
        } else if (cmp < 0) { // move left
            return get(x.left, key);
        } else { // found key return value
            return x.value;
        }
    }

    @Override
    public void delete(K key) {
        if (isEmpty())
            throw new NoSuchElementException("Called delete on empty symbol table");
        if (key == null)
            throw new IllegalArgumentException("delete method is called with null key");
        root = delete(root, key);

    }

    private Node delete(Node x,K key) {
        // first find the key get hold of its left and right subtree links
        // find the min in its right subtree put it on its place adjust the left and right links of it
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null)
            throw new IllegalArgumentException("contains method called with null key");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    @Override
    public K min() {
        if (isEmpty())
            throw new NoSuchElementException("Called min on empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    @Override
    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("Called deleteMin on empty symbol table");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public K max() {
        if (isEmpty())
            throw new NoSuchElementException("Called min on empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    @Override
    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("Called deleteMax on empty symbol table");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public K floor(K key) {
        return null;
    }

    @Override
    public K ceilling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        if (key == null)
            throw new IllegalArgumentException("rank method called with null key");
        return rank(root, key);
    }

    private int rank(Node x, K key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) { // move right and add the count of nodes in its left subtree
            return 1 + (size(x.left)) + rank(x.right, key);
        } else if (cmp < 0) { // keep moving left until you find the key
            return rank(x.left, key);
        } else { // key found tne the size of its left child
            return size(x.left);
        }
    }

    @Override
    public K select(int rank) {
        return null;
    }


    @Override
    public int size(K low, K hi) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K low, K hi) {
        return null;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    public void preOrderTravers() {      // Depth first Search

    }

    public void postOrderTravers() {     // Depth first Search

    }

    public void inOrderTravers() {       // Depth first Search

    }

    public void levelOrderTravers() {    // Breadth first Search

    }
}
