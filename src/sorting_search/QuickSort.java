package sorting_search;

public class QuickSort {

    private void quickSort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int j = parition(a, lo, hi);
        quickSort(a, lo, j - 1);
        quickSort(a, j+1, hi);
    }

    private int parition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while(true) {

        }
    }
}