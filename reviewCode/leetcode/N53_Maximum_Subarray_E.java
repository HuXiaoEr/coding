package reviewCode.leetcode;

//YES
public class N53_Maximum_Subarray_E {
	public int maxSubArray(int[] nums) {
		int subsum = 0;
		int maxsum = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++){
			subsum = Math.max(subsum+nums[i], 0);
			maxsum = Math.max(maxsum, subsum);
		}
		return maxsum;
	}
}
