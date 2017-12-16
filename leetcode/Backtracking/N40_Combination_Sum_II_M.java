package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
 */

//YES
public class N40_Combination_Sum_II_M {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	List<List<Integer>> res = new ArrayList<>();
    	Arrays.sort(candidates);
    	backtrack(candidates, target, new ArrayList<Integer>(), 0, res);
    	return res;
    }
	private void backtrack(int[] candidates, int target, ArrayList<Integer> list, int start, List<List<Integer>> res) {
		if(target < 0) return;
		else if(target == 0) res.add(new ArrayList<>(list));
		else{
			for(int i = start; i < candidates.length; i++){
				if(i > start && candidates[i] == candidates[i-1]) continue; // skip duplicates бя
				list.add(candidates[i]);
				backtrack(candidates, target-candidates[i], list, i+1, res);
				list.remove(list.size()-1);
			}
		}
	}
}
