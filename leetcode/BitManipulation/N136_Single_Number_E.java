package leetcode.BitManipulation;
/**
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 */

//YES
public class N136_Single_Number_E {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
        	res ^= nums[i];
        }
    	return res;
    }
}
