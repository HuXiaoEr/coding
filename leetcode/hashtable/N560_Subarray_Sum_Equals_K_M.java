package leetcode.hashtable;

import java.util.HashMap;

/**
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

// YES TWO
public class N560_Subarray_Sum_Equals_K_M {
	
	// 301ms -> beats 12.32%
	// o(n*n) time  o(n) space
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length+1];
        for(int i = 1; i <= nums.length; i++){
        	sum[i] = sum[i-1] + nums[i-1];
        	for(int j = i-1; j >= 0; j--){
        		if(sum[i]-sum[j] == k) count++;
        	}
        }
    	return count;
    }
    // best
    // 53ms -> beats 37.41%
    // o(n) time  o(n) space
    public int subarraySum2(int[] nums, int k) {
    	HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
    	hash.put(0, 1);
        int count = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
        	sum += nums[i];
        	if(hash.containsKey(sum-k)) count += hash.get(sum-k);
        	if(!hash.containsKey(sum)) hash.put(sum, 0);
        	hash.put(sum, hash.get(sum)+1);
        }
    	return count;
    }
    
    
}
