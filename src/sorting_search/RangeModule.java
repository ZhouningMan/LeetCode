package sorting_search;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
//public class RangeModule {
//    public static class Node {
//        int left;
//        int right;
//        Node smaller;
//        Node bigger;
//
//        public Node(int left, int right) {
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//    private Node root;
//    public RangeModule() {
//        root = null;
//    }
//
//    public void addRange(int left, int right) {
//        if(left < right) {
//            root = addRange(root, left, right);
//        }
//    }
//
//    private Node addRange(Node root, int left, int right) {
//        if(left == right) return root;
//        if(root == null) {
//            return new Node(left, right);
//        } else if(right < root.left) {
//            root = addRange(root.smaller, left, right);
//        } else if(left > root.right) {
//            root.bigger = addRange(root.bigger, left, right);
//        } else if(left <= root.left && right <= root.right) {
//            root.smaller = removeRange(root.smaller, left, root.left);
//            root.left = left;
//        } else if(left <= root.left && right >= root.right) {
//            root.smaller = removeRange(root.smaller, left, root.left);
//            root.bigger = removeRange(root.bigger, root.right, right);
//            root.left = left;
//            root.right = right;
//        } else if(root.left <= left && right >= root.right) {
//            root.bigger = removeRange(root.bigger, root.right, right);
//            root.right = right;
//        }
//        return root;
//    }
//
//    public boolean queryRange(int left, int right) {
//        System.out.println("query Range [" + left + ", " + right + ")" );
//        if(left < right) {
//            return queryRange(root, left, right) != null;
//        }
//        return false;
//    }
//
//    private Node queryRange(Node root, int left, int right) {
//        if(root == null) return null;
//        if(root.left >= right) {
//            return queryRange(root.smaller, left, right);
//        } else if(root.right <= left) {
//            return queryRange(root.bigger, left, right);
//        } else if(root.left <= left && right <= root.right){
//            return root;
//        }
//        return null;
//    }
//
//    public void removeRange(int left, int right) {
//        if(left < right) {
//            root = removeRange(root, left, right);
//        }
//    }
//
//    private Node removeRange(Node root, int left, int right) {
//        if(left == right) return root;
//        if (root == null) return null;
//        if (left > root.right) {
//            root.bigger = removeRange(root.bigger, left, right);
//        } else if (right <= root.left) {
//            root.smaller = removeRange(root.smaller, left, right);
//        } else {
//            if (left <= root.left && root.left < right && right < root.right) {
//                //[10, 20) remove [8, 15) ==> [15, 20) + remove(root.smaller, 8, 11)
//                root.smaller = removeRange(root.smaller, left, root.left + 1);
//                root.left = right;
//            } else if (root.left < left && right < root.right) {
//                //[10, 20) remove [12, 15) ==> [10, 12) + [15, 20)
//                Node newRoot = new Node(right, root.right);
//                root.right = left;
//                newRoot.smaller = root;
//                newRoot.bigger = root.bigger;
//                root.bigger = null;
//                root = newRoot;
//            } else if (root.left < left && root.right < right) {
//                //[10, 20) remove [12, 25) ==> [10, 12) + remove(root.bigger, 20, 25)
//                root.bigger = removeRange(root.bigger, root.right, right);
//                root.right = left;
//            } else if (left < root.left && right >= root.right) {
//                //[10, 20) remove [8, 25) ==> root = root.right
//                Node leftRoot = removeRange(root.smaller, left, root.left + 1);
//                Node rightRoot = removeRange(root.bigger, root.right, right);
//                if(rightRoot == null) root = leftRoot;
//                else {
//                    Node min = min(rightRoot);
//                    min.bigger = deleteMin(rightRoot);
//                    min.smaller = root.smaller;
//                    root = min;
//                }
//            }
//        }
//        return root;
//    }
//
//    private Node min(Node root) {
//        if(root == null) {
//            return  null;
//        } else if(root.smaller == null) {
//            return root;
//        } else {
//            return min(root.smaller);
//        }
//    }
//
//    private Node deleteMin(Node root) {
//        if(root == null) return null;
//        if(root.smaller == null) return root.bigger;
//        return deleteMin(root.smaller);
//    }
//
//    public static void test() {
//        RangeModule rangeModule = new RangeModule();
//        rangeModule.addRange(10, 180);
//        rangeModule.addRange(150, 200);
//        rangeModule.addRange(250, 500);
//        System.out.println(rangeModule.queryRange(50, 100));
//        System.out.println(rangeModule.queryRange(180, 300));
//        System.out.println(rangeModule.queryRange(600, 1000));
//        rangeModule.removeRange(50, 150);
//        rangeModule.queryRange(50, 100);
//    }
//}
//


//===========================
public class RangeModule {

    TreeMap<Integer, Integer> map;
    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if(left>=right){
            return;
        }
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if(start == null && end == null){
            map.put(left, right);
        }else if(start!=null && map.get(start)>=left){
            //if start != null then it is not possible that end == null
            map.put(start, Math.max(right, Math.max(map.get(start), map.get(end))));
        }else{
            map.put(left, Math.max(right, map.get(end)));
        }
        Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
        Set<Integer> set = new HashSet<>(subMap.keySet());
        map.keySet().removeAll(set);
    }

    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if(start==null){
            return false;
        }
        return right<=map.get(start);
    }

    public void removeRange(int left, int right) {
        if(left>=right){
            return;
        }
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if(end!=null && map.get(end)>right){
            map.put(right, map.get(end));
        }
        if(start!=null && map.get(start)>left){
            map.put(start, left);
        }
        Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
        Set<Integer> set = new HashSet<>(subMap.keySet());
        map.keySet().removeAll(set);
    }

    public static void test() {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10, 180);
        rangeModule.addRange(150, 200);
        rangeModule.addRange(250, 500);
        System.out.println(rangeModule.queryRange(50, 100));
        System.out.println(rangeModule.queryRange(180, 300));
        System.out.println(rangeModule.queryRange(600, 1000));
        rangeModule.removeRange(50, 150);
        rangeModule.queryRange(50, 100);
    }
}
