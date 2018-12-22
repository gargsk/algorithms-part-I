package com.sharad.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shuffling {

    public static Comparable[] shuffle(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int index = StdRandom.uniform(i+1);
            // exchange the object at index with the object at index i
            exch(a,i,index);
        }
        return a;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {0,1,2,3,4,5,6,7,8,9};
        Shuffling.shuffle(a);
        show(a);
    }
}

