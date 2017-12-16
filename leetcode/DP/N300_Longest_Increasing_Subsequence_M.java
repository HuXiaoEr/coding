package leetcode.DP;

import java.util.Arrays;

/**
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], 
therefore the length is 4. Note that there may be more than one LIS combination, 
it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
 */


//YES
public class N300_Longest_Increasing_Subsequence_M {
	
	//Mine DP
	//o(n*n)
	public int lengthOfLIS(int[] nums) {
        int res = 1;
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 1; i < nums.length; i++){
        	for(int j = i-1; j >= 0; j--){
        		int max = nums[i] > nums[j] ? dp[j]+1 : 0;
        		dp[i] = Math.max(dp[i], max);
        	}
        	res = Math.max(res, dp[i]);
        }
		return res;
    }
	
	/** HOW ??  TWO */
	//best DP
	//o(nlog(n))
	public int lengthOfLIS2(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }
        return len;
    }
	
}
