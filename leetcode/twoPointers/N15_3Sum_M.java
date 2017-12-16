package leetcode.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

//NO
public class N15_3Sum_M {
	
	//my code : TLE  ->  311 / 313 test cases passed
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
    	if(nums == null || nums.length < 3) return res;
    	Arrays.sort(nums);
    	for(int i = 0; i < nums.length; i++){
    		if(i > 0 && nums[i] == nums[i-1]) continue;
    		for(int j = i+1; j < nums.length; j++){
    			if(j != i+1 && nums[j] == nums[j-1]) continue;
    			for(int k = j+1; k < nums.length; k++){
    				if(k != j+1 && nums[k] == nums[k-1]) continue;
    				if(nums[i] + nums[j] + nums[k] == 0){
    					List<Integer> list = new ArrayList<>();
    	    			list.add(nums[i]);
    	    			list.add(nums[j]);
    	    			list.add(nums[k]);
    	    			res.add(list);
    				}
    			}
    		}
    	}
    	return res;
    }
    
    //best
    //o(n*n)
    public List<List<Integer>> threeSum2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>(); 
        for (int i = 0; i < num.length-2; i++) {
        	//skip equal elements to avoid duplicates in the answer without making a set or smth like that.
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            	//bi-directional 2Sum sweep of the remaining part of the array
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        lo++; hi--;
                        //skip equal elements to avoid duplicates in the answer without making a set or smth like that
                        while (lo < hi && num[lo] == num[lo-1]) lo++;
                        while (lo < hi && num[hi] == num[hi+1]) hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
               }
            }
        }
        return res;
    }
}
