package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

//YES
//backtracking
//bit manipulation
public class N78_Subsets_M {

	//mine backtracking
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums == null || nums.length == 0) return res;
		backtrack(nums, 0, new ArrayList<Integer>(), res);
		return res;
    }
	private void backtrack(int[] nums, int start, List<Integer> list, List<List<Integer>> res) {
		if(start <= nums.length){  // <=  not <   careful the last one
			res.add(new ArrayList<Integer>(list));
		}
		for(int i = start; i < nums.length; i++){
			list.add(nums[i]);
			backtrack(nums, i+1, list, res);
			list.remove(list.size()-1);
		}
	}
	
	//bit manipulation
	/*
	 *  Number of subsets is 2^n
	 *  Time complexity : O(n*2^n)
	 *  n = S.length
	 */
	public List<List<Integer>> subsets2(int[] S) {
		Arrays.sort(S);
		int totalNumber = 1 << S.length;
		List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
		for (int i=0; i<totalNumber; i++) {
			List<Integer> set = new LinkedList<Integer>();
			for (int j=0; j<S.length; j++) {
				if ((i & (1<<j)) != 0) {
					set.add(S[j]);
				}
			}
			collection.add(set);
		}
		return collection;
	}
}
