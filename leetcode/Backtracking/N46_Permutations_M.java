package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

//NO
//YES 
public class N46_Permutations_M {
	
	//my code  beats 91%
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null) return res;
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;
    }
	private void backtrack(int start, int[] nums, List<List<Integer>> res, ArrayList<Integer> list) {
		if(start == nums.length){
			res.add(new ArrayList(list));
			return;
		}
		for(int i = start; i < nums.length; i++){
			exch(nums, start, i);
			list.add(nums[start]);
			backtrack(start+1, nums, res, list);
			list.remove(list.size()-1);
			exch(nums, start, i);
		}
	}
	private void exch(int[] nums, int start, int i) {
		int temp = nums[start];
		nums[start] = nums[i];
		nums[i] = temp;
	}
	
	//
	public List<List<Integer>> permute3(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), nums);
		return list;
	}
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tempList.contains(nums[i]))
					continue; // element already exists, skip бябя
				tempList.add(nums[i]);
				backtrack(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
	
	//iterative solution
	public List<List<Integer>> permute2(int[] num) {
	    LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
	    res.add(new ArrayList<Integer>());
	    for (int n : num) {
	        int size = res.size();
	        for (; size > 0; size--) {
	            List<Integer> r = res.pollFirst();
	            for (int i = 0; i <= r.size(); i++) {
	                List<Integer> t = new ArrayList<Integer>(r);
	                t.add(i, n);
	                res.add(t);
	            }
	        }
	    }
	    return res;
	}
}
