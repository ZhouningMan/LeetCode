package backtracking;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
//    public List<List<Integer>> getFactors(int n) {
//        List<List<Integer>> res = new ArrayList<>();
//        backtrack(res, new ArrayList<>(), n, 2);
//        return res;
//    }
//
//    public void backtrack(List<List<Integer>> res, List<Integer> temp, int n, int start){
//        System.out.println("temp: " + temp + ". n: " + n + ". start: " + start);
//        for(int i = start; i <= Math.sqrt(n); i++){
//            if(n%i == 0){
//                temp.add(i);
//                backtrack(res, temp, n/i, i);
//                temp.remove(temp.size()-1);
//            }
//        }
//        if(temp.size() != 0){
//            temp.add(n);
//            res.add(new ArrayList<>(temp));
//            temp.remove(temp.size()-1);
//        }
//    }

    public static void test() {
        new FactorCombinations().getFactors(12);
    }


    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if(n <= 1) return result;
        helper(n, 2, new ArrayList<>(), result);
        return result;
    }

    private void helper(int n, int start,  List<Integer> factors, List<List<Integer>> result) {
        //every composite number has a prime factor less than or equal to its square root.
        //we need to iterate from start to avoid duplicate
        //3 x 4 ==> 3 x 2 x 2 which is a duplicate from 2 x 6 => 2 x 2 x 3
        for(int i = start; i <= Math.sqrt(n);  ++i) {
            if(n % i == 0) {
                factors.add(i);
                int factor = n / i;
                factors.add(factor);
                result.add(new ArrayList<>(factors)); //where factors are collected
                factors.remove(factors.size() - 1);
                helper(factor, i, factors, result);
                factors.remove(factors.size() -1 ); //remove i
            }
        }
     }
}
