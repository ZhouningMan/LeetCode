package backtracking;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if(board == null || word == null || word.isEmpty()) return false;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(dfs(board, visited, word, new StringBuilder(), r, c)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, String word, StringBuilder sb, int row, int col) {
        if(row < 0 || col < 0 || row >= visited.length || col >= visited[0].length) return false;
        if(visited[row][col]) return false;
        sb.append(board[row][col]);
        visited[row][col] = true;
        String s = sb.toString();
        if(sb.length() == word.length()) {
            backtrack(visited, sb, row, col);
            return s.equals(word);
        }
        if(!word.startsWith(s)) {
            backtrack(visited, sb, row, col);
            return false;
        }

        if(dfs(board, visited, word, sb, row + 1, col)) return true;
        if(dfs(board, visited, word, sb, row -1 , col)) return true;
        if(dfs(board, visited, word, sb, row, col + 1)) return true;
        if(dfs(board, visited, word, sb, row, col -1)) return true;
        //back track;
        backtrack(visited, sb, row, col);
        return false;
    }

    private void backtrack(boolean[][] visited, StringBuilder sb, int row, int col) {
        sb.deleteCharAt(sb.length() - 1);
        visited[row][col] = false;
    }
}

class Impl2 {

    public boolean exist(char[][] board, String word) {
        if(board == null || word == null || word.isEmpty()) return false;
        int rows = board.length;
        int cols = board[0].length;
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(dfs(board, word, 0, r, c)) return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int id, int row, int col) {
        if(id == word.length()) return true;
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length ||
            word.charAt(id) != board[row][col]) return false;
        board[row][col] = '#'; //minor optimization assuming # can't be part of the string
        int next = id + 1;
        if(dfs(board, word, next, row+1, col) ||
            dfs(board, word, next, row-1, col) ||
            dfs(board, word, next, row, col+1) ||
            dfs(board, word, next, row, col - 1)) return true;
        board[row][col] = word.charAt(id);
        return false;
    }
}
