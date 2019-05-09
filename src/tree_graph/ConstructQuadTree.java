package tree_graph;

public class ConstructQuadTree {
    public static Node leaf(int val) {
        return new Node(val == 1, true);
    }

    public Node construct(int[][] grid) {
        int n = grid.length;
        int[][] ones = compute(grid, n);
        return construct(ones, n, 0, 0);
    }

    private int[][] compute(int[][] grid, int n) {
        int[][] count = new int[n][n];
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                if (r == 0 && c == 0) {
                    count[r][c] = grid[r][c];
                } else if (r == 0) {
                    count[r][c] = count[r][c - 1] + grid[r][c];
                } else if (c == 0) {
                    count[r][c] = count[r - 1][c] + grid[r][c];
                } else {
                    count[r][c] = count[r][c - 1] + count[r - 1][c] - count[r - 1][c - 1] + grid[r][c];
                }
            }
        }
        return count;
    }

    //0 not leaf, 1 leaf with leaf value being true, -1 leaf with leaf value being false.
    private int isLeaf(int[][] computed, int or, int oc, int er, int ec) {
        int expected = (er - or + 1) * (ec - oc + 1); //expected ones.
        int actual = computed[er][ec];
        if (or -1  < 0 && oc - 1 < 0) {
            //do nothing
        } else if (or - 1 < 0) {
            actual = actual - computed[er][oc - 1];
        } else if (oc - 1 < 0) {
            actual = actual - computed[or - 1][ec];
        } else {
            actual -= computed[er][oc - 1];
            actual -= computed[or - 1][ec];
            actual += computed[or - 1][oc - 1];
        }
        return expected == actual ? 1 : actual == 0 ? -1 : 0;
    }
    private Node construct(int[][] computed, int size, int or, int oc) {
        int leafVal = isLeaf(computed, or, oc, or + size - 1, oc + size - 1);
        if(leafVal != 0) {
            return leaf(leafVal);
        }
        Node node = new Node(false, false);
        int half = size / 2;
        node.topLeft = construct(computed, half, or, oc);
        node.topRight = construct(computed, half, or, oc + half);
        node.bottomLeft = construct(computed, half, or + half, oc);
        node.bottomRight = construct(computed, half, or + half, oc + half);
        return node;
    }

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public static void test() {
        new ConstructQuadTree().construct(new int[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
    }
}
