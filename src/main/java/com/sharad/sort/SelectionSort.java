package com.sharad.sort;


import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class SelectionSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            // the invarent is every element in the array to the left of current min should be be less then the
            // element in the right side of min
            // so in start we assume at min=0th index is min and scan the array from index 1 to n-1
            // to find is there any element right to oth index is smaller then the element at 0th index
            // if we find one we will swap it
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])){ // found one so capture its index in min
                    min=j;
                }
                // this for loop will continue till end as we need to scan the entire sub array and keep updating
                // min with the index which has smallest item
            }
            exch(a,i,min); // swap the element at the current min with smallest one
        }

    }

    public static void sort(Object[] a, Comparator c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            // the invarent is every element in the array to the left of current min should be be less then the
            // element in the right side of min
            // so in start we assume at min=0th index is min and scan the array from index 1 to n-1
            // to find is there any element right to oth index is smaller then the element at 0th index
            // if we find one we will swap it
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min], c)){ // found one so capture its index in min
                    min=j;
                }
                // this for loop will continue till end as we need to scan the entire sub array and keep updating
                // min with the index which has smallest item
            }
            exch(a,i,min); // swap the element at the current min with smallest one
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
            if (less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator c) {
        for (int i = 1; i <= a.length; i++) {
            if (less(a[i], a[i-1], c)){
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
