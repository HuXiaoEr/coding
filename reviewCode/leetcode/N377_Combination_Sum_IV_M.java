package reviewCode.leetcode;

import java.util.Arrays;

public class N377_Combination_Sum_IV_M {
	
	// TLE
	int res;
    public int combinationSum4(int[] nums, int target) {
    	Arrays.sort(nums);
    	backtrack(nums, target);
    	return res;
    }
	private void backtrack(int[] nums, int target) {
		if(target == 0){
			res++;
			return;
		}
		for(int i = 0; i < nums.length; i++){
			if(target - nums[i] < 0) break;
			backtrack(nums, target-nums[i]);
		}
	}
}
