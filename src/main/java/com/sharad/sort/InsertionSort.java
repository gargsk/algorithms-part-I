package com.sharad.sort;

import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class InsertionSort {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                // invarient the elements in the array with indexes less then i are sorted
                // and to the right of i are not visistes at all.
                if(less(a[j],a[j-1])){
                    exch(a, j, j-1);
                }else{
                    break;
                }
            }
        }
    }

    public static void sort(Object[] a, Comparator c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                // invarient the elements in the array with indexes less then i are sorted
                // and to the right of i are not visistes at all.
                if(less(a[j],a[j-1],c)){
                    exch(a, j, j-1);
                }else{
                    break;
                }
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less(Object v, Object w, Comparator c) {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i <= a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator c) {
        for (int i = 1; i <= a.length; i++) {
            if (less(a[i], a[i - 1], c)) {
                return false;
            }
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Selection.sort(a);
        show(a);
    }
}
