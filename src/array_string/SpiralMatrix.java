package array_string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null) return new ArrayList<>();
        int row = matrix.length;
        if(row == 0) return new ArrayList<>();
        int col = matrix[0].length;
        int leftC = 0;
        int topR = 0;
        int rightC = col - 1;
        int bottomR = row -1;
        List<Integer> elements = new ArrayList<>();
        while(isValidMatrix(leftC, topR, rightC, bottomR)) {
            collectCol(elements, matrix, topR, leftC, rightC, 1);
            collectRow(elements, matrix, rightC, topR + 1 , bottomR, 1);
            if(topR != bottomR) collectCol(elements, matrix, bottomR, rightC - 1, leftC, -1);
            if(leftC != rightC) collectRow(elements, matrix, leftC, bottomR - 1, topR + 1, -1);

            leftC++;
            topR++;
            rightC--;
            bottomR--;
        }
        return elements;
    }
    private void collectRow(List<Integer> elements, int[][] matrix, int col, int beginR, int endR, int delta) {
        if(delta > 0) {
            for(int r = beginR; r <= endR; r += delta) {
                elements.add(matrix[r][col]);
            }
        } else {
            for(int r = beginR; endR <= r; r += delta) {
                elements.add(matrix[r][col]);
            }
        }
    }
    private void collectCol(List<Integer> elements, int[][] matrix, int row, int beginC, int endC, int delta) {
        if(delta > 0) {
            for(int c = beginC; c <= endC; c += delta) {
                elements.add(matrix[row][c]);
            }
        } else {
            for(int c = beginC; endC <= c; c += delta) {
                elements.add(matrix[row][c]);
            }
        }
    }
    private boolean isValidMatrix(int leftC, int topR, int rightC, int bottomR) {
        return leftC <= rightC && topR <= bottomR;
    }
}
