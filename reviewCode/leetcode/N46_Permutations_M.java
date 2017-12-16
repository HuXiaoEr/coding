package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class N46_Permutations_M {
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
}
