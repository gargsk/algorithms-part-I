package com.sharad.symboltables;

public interface SymbolTable<K extends Comparable<? super K>, V> {

    void put(K key, V value);

    V get(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    K min();

    K max();

    K floor(K key);

    K ceilling(K key);

    int rank(K key);

    K select(int rank);

    void deleteMin();

    void deleteMax();

    int size(K low, K hi);

    Iterable<K> keys(K low, K hi);

    Iterable<K> keys();

}
