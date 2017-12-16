package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
Given a list of non-negative numbers and a target integer k, 
write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, 
that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

//NO
public class N523_Continuous_Subarray_Sum_M {
	
	//Mine DP -> MLE
	public boolean checkSubarraySum(int[] nums, int k) {
    	int length = nums.length;
        int[][] dp = new int[length][length];
        for(int i = 0; i < length; i++) dp[i][i] = nums[i];
        for(int i = 0; i < length; i++){
        	for(int j = i+1; j < length; j++){
        		dp[i][j] = dp[i][j-1] + nums[j];
        		if(flag(dp[i][j], k)) return true;
        	}
        }
    	return false;
    }
	private boolean flag(int num, int k) {
	    if(num == 0 && k == 0) return true;
	    else if(k == 0) return false;
		while((num % k) == 0 && num != 0) num = (num % k);
		return num == 0;
	}
	
	//Not Mine
	//o(n) time  o(k) space
	/*
	 * For e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42] and the reminders are [5,1,1,5,0]. 
	 * We got reminder 5 at index 0 and at index 3. 
	 * That means, in between these two indexes we must have added a number which is multiple of the k. 
	 */
	public boolean checkSubarraySum2(int[] nums, int k) {
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};; //бя
	    int runningSum = 0;
	    for (int i=0;i<nums.length;i++) {
	        runningSum += nums[i];
	        if (k != 0) runningSum %= k; //бя
	        Integer prev = map.get(runningSum);
	        if (prev != null) {
	            if (i - prev > 1) return true; //бя
	        }
	        else map.put(runningSum, i);
	    }
	    return false;
	}
	
	//same idea, but better version than checkSubarraySum3()
	public boolean checkSubarraySum4(int[] nums, int k) {
		int n = nums.length;
		int[] sums = new int[n+1];
		for (int i = 1; i <= n; i++) {
			sums[i] = sums[i-1] + nums[i-1];
			for (int j = 0; j < i-1; j++) {
				int dif = sums[i]-sums[j];
				if (dif == 0 && k == 0) return true;
				else if (k == 0) continue;
				else if ((sums[i]-sums[j]) % k == 0) return true;
			}
		}
		return false;
	}
	//Not smart solution, but easy to understand
	public boolean checkSubarraySum3(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return false;
        int[] preSum = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+2; j <= nums.length; j++) {
                if (k == 0) {
                    if (preSum[j] - preSum[i] == 0) {
                        return true;
                    }
                } else if ((preSum[j] - preSum[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
