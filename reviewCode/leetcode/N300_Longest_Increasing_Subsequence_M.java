package reviewCode.leetcode;

public class N300_Longest_Increasing_Subsequence_M {
	int res = 0;
    public int lengthOfLIS(int[] nums) {
    	if(nums == null) return 0;
    	int[] dp = new int[nums.length];
    	for(int i = 0; i < nums.length; i++){
    		int len = 1;
    		for(int j = i-1; j >= 0; j--){
    			if(nums[i] > nums[j]) len = Math.max(len, 1+dp[j]);
    		}
    		dp[i] = len;
    		res = Math.max(res, dp[i]);
    	}
    	return res;
    }

}
