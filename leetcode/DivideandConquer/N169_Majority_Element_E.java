package leetcode.DivideandConquer;

import java.util.Arrays;

/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

//YES
public class N169_Majority_Element_E {
    
	//My code
	public int majorityElement(int[] nums) {
    	Arrays.sort(nums);
    	return nums[nums.length >> 1];
    }
	
	//Not Mine
	//best : o(n) time o(1) space
    public int majorityElement2(int[] num) {
        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;
        }
        return major;
    }
    
    //Bit Manipulation
    public int majorityElement3(int[] num) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int ones = 0, zeros = 0;
            for (int j = 0; j < num.length; j++) {
                if ((num[j] & (1 << i)) != 0) {
                    ++ones;
                }
                else
                    ++zeros;
            }
            if (ones > zeros)
                ret |= (1 << i);
        }
        return ret;
    }
}
