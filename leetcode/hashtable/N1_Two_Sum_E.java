package leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

//NO
public class N1_Two_Sum_E {
	
	//wrong algs : 11 / 19 test cases passed
	//nums[]中元素可能重复 ★
    public int[] twoSum(int[] nums, int target) {
        //if(nums.length == 2) return new int[]{0,1};
    	HashMap<Integer, Integer> idx = new HashMap<>();
    	for(int i = 0; i < nums.length; i++){
            if(idx.containsKey(nums[i])) return new int[]{idx.get(nums[i]),i};
            else idx.put(nums[i], i);
        }
        Arrays.sort(nums);
        int[] res = new int[2];
        int s = 0;
        int e = nums.length-1;
        while(s < e){
        	int temp = nums[s] + nums[e];
        	if(temp == target){
        		res[0] = idx.get(nums[s]);
        		res[1] = idx.get(nums[e]);
        		break;
        	}
        	else if(temp < target) s++;
        	else e--;
        }
    	return res;
    }
    
    //not mine
    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }
}
