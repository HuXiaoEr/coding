package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
 */

//YES
public class N216_Combination_Sum_III_M {
    public List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> res = new ArrayList<>();
    	backtrack(k, n, 1, new ArrayList<Integer>(), res);
    	return res;
    }
	private void backtrack(int k, int n, int start, ArrayList<Integer> list, List<List<Integer>> res) {
		if(k == 0){
			if(n == 0) res.add(new ArrayList<Integer>(list));
			return;
		}
		for(int i = start; i <= 9; i++){
			list.add(i);
			backtrack(k-1, n-i, i+1, list, res);
			list.remove(list.size()-1);
		}
	}
}
