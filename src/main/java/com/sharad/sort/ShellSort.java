package com.sharad.sort;

import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class ShellSort {

    // h sort and ecah iteration reduce the value of h

    public static void sort(Comparable[] a) {

        int N = a.length;

        int h = 1;
        // find the right value of h  to start with
        // using knuth method
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        //
        // now do the insertion dor t with jump f h

        while (h >= 1) {

            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }

    }

    public static void sort(Object[] a, Comparator c) {
        int N = a.length;

        int h = 1;
        // find the right value of h  to start with
        // using knuth method
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        //
        // now do the insertion dor t with jump f h

        while (h >= 1) {

            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h], c); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
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
