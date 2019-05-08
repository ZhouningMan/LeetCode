package sorting_search;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int low = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int high = rows * cols - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            int r = mid / cols;
            int c = mid % cols;
            if(matrix[r][c] == target) return true;
            else if(matrix[r][c] > target) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }
}
