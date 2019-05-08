package tree_graph;

import java.util.*;

public class ClosestBSTValueII {
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        ArrayList<Integer> valsList = new ArrayList<>();
        inorder(root, valsList);
        //can be optimized
        Integer[] valsArray = new Integer[valsList.size()];
        valsList.toArray(valsArray);
        int pos = Arrays.binarySearch(valsArray, (int)(target + 1));
        int insertionPoint = pos < 0 ? -pos - 1 : pos;

        List<Integer> result = new ArrayList<>();
        int backward = insertionPoint - 1;
        int forward = insertionPoint;
        while(k-- > 0) {
            if(backward < 0) {
                result.add(valsArray[forward++]);
            } else if(forward >= valsArray.length) {
                result.add(valsArray[backward--]);
            } else {
                Integer pre = valsArray[backward];
                Integer succ = valsArray[forward];
                if(Math.abs(pre - target) > Math.abs(succ - target)) {
                    forward++;
                    result.add(succ);
                } else {
                    backward--;
                    result.add(pre);
                }
            }
        }
        return  result;
    }

    private void inorder(TreeNode root, List<Integer> values) {
        if(root == null) return;
        inorder(root.left, values);
        values.add(root.val);
        inorder(root.right, values);
    }


    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> prec = new ArrayDeque<>();
        Deque<Integer> succ = new ArrayDeque<>();
        inorder(root, target, prec, succ);

        List<Integer> result = new ArrayList<>();
        while(k-- > 0) {
            if(prec.isEmpty()){
                result.add(succ.pop());
            } else if(succ.isEmpty()) {
                result.add(prec.pop());
            } else if(target - prec.peek() > succ.peek() - target){
                result.add(succ.pop());
            } else {
                result.add(prec.pop());
            }
        }
        return result;
    }

    private void inorder(TreeNode root,double target, Deque<Integer> prec, Deque<Integer> succ) {
        if(root == null) return;
        inorder(root.left, target, prec, succ);
        if(root.val < target) {
            prec.push(root.val);
        } else {
            succ.offer(root.val);
        }
        inorder(root.right, target, prec, succ);
    }
}
