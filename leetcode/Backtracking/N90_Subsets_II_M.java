package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

//YES
public class N90_Subsets_II_M {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	Arrays.sort(nums);
    	backtrack(nums, 0, new ArrayList<Integer>(), res);
    	return res;
    }
	private void backtrack(int[] nums, int start, ArrayList<Integer> list, List<List<Integer>> res) {
		res.add(new ArrayList<Integer>(list));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i-1]) continue;
			list.add(nums[i]);
			backtrack(nums, i+1, list, res);
			list.remove(list.size()-1);
		}
	}
}
