package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */

//NO
public class N47_Permutations_II_M {

	public List<List<Integer>> permuteUnique(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
	    return list;
	}
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
	    if(tempList.size() == nums.length){
	        list.add(new ArrayList<>(tempList));
	    } else{
	        for(int i = 0; i < nums.length; i++){
	        	
	        	/*
	        	 * The problem for Permutation II is that different orders of duplicates should only be considered as one permutation. 
	        	 * In other words, you should make sure that when these duplicates are selected, there has to be бя one fixed order бя. 
	        	 * 
	        	 * Now take a look at code 1 and 2.
	        	 * Code 2 makes sure when duplicates are selected, the order is ascending (index from small to large).
	        	 *  However, code 1 means the descending order.
	        	 */
	        	
	        	// if(i > 0 && nums[i] == nums[i - 1] && use[i - 1]) continue;   also work  code 1
	            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;  //бябя   code 2
	            used[i] = true; 
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, used);
	            used[i] = false; 
	            tempList.remove(tempList.size() - 1);
	        }
	    }
	}
	
	//another idea
	public List<List<Integer>> permuteUnique2(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		permutating(ans, nums, 0);
		return ans;
	}
	private void permutating(List<List<Integer>> ans, int[] nums, int start) {
		if (start == nums.length) {
			List<Integer> li = new ArrayList<>();
			for (int n : nums) {
				li.add(n);
			}
			ans.add(li);
			return;
		}
		for (int i = start; i < nums.length; i++) {
			if (i != start && nums[start] == nums[i]) {
				continue;
			}
			swap(nums, start, i);
			permutating(ans, Arrays.copyOf(nums, nums.length), start + 1);
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	//iterative solution  
	//It builds the permutations for i-1 first elements of an input array 
	//and tries to insert the ith element into all positions of each prebuilt i-1 permutation. 
	public List<List<Integer>> permuteUnique3(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < num.length; i++) {
            Set<String> cache = new HashSet<>();
            while (res.peekFirst().size() == i) {
                List<Integer> l = res.removeFirst();
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>(l.subList(0,j));
                    newL.add(num[i]);
                    newL.addAll(l.subList(j,l.size()));
                    if (cache.add(newL.toString())) res.add(newL);
                }
            }
        }
        return res;
    }
}
