package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hueryang on 2017/7/17.
 */

//宸茬粡鍋氳繃浜� 
//YES
public class N39_Combination_Sum_M {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null) return res;
        Arrays.sort(candidates);
        backtrack(0, candidates, target, res, new ArrayList<Integer>());
        return res;
    }
    private static  void backtrack(int s, int[] candidates, int target, List<List<Integer>> res, ArrayList<Integer> list) {
        if(target < 0) return;
        if(target == 0){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = s; i < candidates.length; i++) {
            list.add(candidates[i]);
            backtrack(i, candidates, target-candidates[i], res, list);
            list.remove(list.size()-1);
        }
    }
    public static void main(String[] args){
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
    }

}
