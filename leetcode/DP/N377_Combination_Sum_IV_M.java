package leetcode.DP;

import java.util.Arrays;

/**
Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
 */
//I think if there are negative numbers in the array, we must add a requirement that each number is only used one time, 
//or either positive number or negative number should be used only one time, 
//otherwise there would be infinite possible combinations.
//For example, we are given:
//{1, -1}, target = 1,
//it's obvious to see as long as we choose n 1s and (n-1) -1s, it always sums up to 1, n can be any value >= 1.


//YES
//consider  39. Combination Sum
public class N377_Combination_Sum_IV_M {
	
	//Mine DP
	//same idea to N322_Coin_Change
	//down-top 7ms
	public int combinationSum4(int[] nums, int target) {
        int[] count = new int[target+1];
        count[0] = 1; // the target is 0, so there is only one way to get zero, which is using 0.
        for(int i = 1; i <= target; i++){
        	for(int num : nums){
        		if(i >= num) count[i] += count[i-num];
        	}
        }
		return count[target];
    }
	
	// best
	// Not Mine DP
	// top-down faster 1ms
	// Because down-top has more "intermediate steps" than the top-down one.
	private int[] dp;
	public int combinationSum4_2(int[] nums, int target) {
	    dp = new int[target + 1];
	    Arrays.fill(dp, -1);
	    dp[0] = 1;
	    return helper(nums, target);
	}
	private int helper(int[] nums, int target) {
	    if (dp[target] != -1) {
	        return dp[target];
	    }
	    int res = 0;
	    for (int i = 0; i < nums.length; i++) {
	        if (target >= nums[i]) {
	            res += helper(nums, target - nums[i]);
	        }
	    }
	    dp[target] = res;
	    return res;
	}
	
	// My code TWO
	// TLE
	int res;
    public int combinationSum43(int[] nums, int target) {
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
