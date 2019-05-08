package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpareMetricMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length == 0 || A[0].length == 0 || B.length == 0 || B[0].length == 0) return null;
        Map<Integer, Set<Integer>> rowNonZeros = new HashMap<>(A.length);
        Map<Integer, Set<Integer>> colNonZeros = new HashMap<>(B[0].length);
        for(int row = 0 ; row < A.length; row++) {
            for(int col = 0; col < A[row].length; col++) {
                Set<Integer> nonZeros = rowNonZeros.computeIfAbsent(row, k->new HashSet<>());
                if(A[row][col] != 0) nonZeros.add(col);
            }
        }

        for(int row = 0; row < B.length; row++) {
            for(int col = 0; col < B[row].length; col++) {
                Set<Integer> nonZeros = colNonZeros.computeIfAbsent(col, k -> new HashSet<>());
                if(B[row][col] != 0) nonZeros.add(row);
            }
        }

        int[][] result = new int[A.length][B[0].length];
        for(int row = 0; row < rowNonZeros.size(); row++) {
            for(int col = 0; col < colNonZeros.size(); col++) {
                Set<Integer> cols = rowNonZeros.get(row);
                Set<Integer> rows = colNonZeros.get(col);
                int product = 0;
                for(Integer i : cols) {
                    if(rows.contains(i)) {
                        product += A[row][i] * B[i][col];
                    }
                }
                result[row][col] = product;
            }
        }
        return result;
    }

}
