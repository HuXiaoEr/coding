package reviewCode.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
Given an array of size n, find the majority element. 
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

//YES
public class N169_Majority_Element_E {
    public int majorityElement(int[] nums) {
    	Arrays.sort(nums);
    	return nums[nums.length >> 1];
    }
    
    // o(n) time  o(1) space
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
    
    // hash table
    public int majorityElement3(int[] nums) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        //Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
        int ret=0;
        for (int num: nums) {
            if (!myMap.containsKey(num))
                myMap.put(num, 1);
            else
                myMap.put(num, myMap.get(num)+1);
            if (myMap.get(num)>nums.length/2) {
                ret = num;
                break;
            }
        }
        return ret;
    }
    
 // Bit manipulation 
    public int majorityElement5(int[] num) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int ones = 0, zeros = 0;
            for (int j = 0; j < num.length; j++) {
                if ((num[j] & (1 << i)) != 0) ++ones;
                else ++zeros;
            }
            if (ones > zeros) ret |= (1 << i);
        }
        return ret;
    }
    
    public int majorityElement4(int[] nums) {
        int[] bit = new int[32];
        for (int num: nums)
            for (int i=0; i<32; i++) 
                if ((num>>(31-i) & 1) == 1)
                    bit[i]++;
        int ret=0;
        for (int i=0; i<32; i++) {
            bit[i]=bit[i]>nums.length/2?1:0;
            ret += bit[i]*(1<<(31-i));
        }
        return ret;
    }
    
    
    
}
