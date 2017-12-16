package reviewCode.leetcode;

import java.util.Arrays;

// NO
public class N416_Partition_Equal_Subset_Sum_M {
    public boolean canPartition(int[] nums) {
        int target = 0;
        for(int i = 0; i < nums.length; i++) target += nums[i];
        if((target % 2) != 0) return false;
        target /= 2;
        int[] dp = new int[target+1];
        dp[0] = 1;
        return findTarget(nums, target, 0, dp) >= 1;
    }
	private int findTarget(int[] nums, int target, int start, int[] dp) {
		if(dp[target] != -1) return dp[target];
		int res = 0;
		for(int i = start; i < nums.length; i++){
			if(target - nums[i] >= 0){
				exch(nums, start, i);
				res = findTarget(nums, target-nums[i], start+1, dp);
				if(res >= 1){
					dp[target] = 1;
					return res;
				}
				exch(nums, i, start);
			}
		}
		return res;
	}
	private void exch(int[] nums, int start, int i) {
		int temp = nums[start];
		nums[start] = nums[i];
		nums[i] = temp;
	}
}
