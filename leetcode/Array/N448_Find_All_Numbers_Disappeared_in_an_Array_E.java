package leetcode.Array;

import java.util.ArrayList;
import java.util.List;

/**
Given an array of integers where 1 ¡Ü a[i] ¡Ü n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 */

// NO TWO
public class N448_Find_All_Numbers_Disappeared_in_an_Array_E {
	
	
	public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        // iterate through the input array and mark elements as negative using nums[nums[i] -1] = -nums[nums[i]-1]. 
        // all the numbers that we have seen will be marked as negative
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) nums[val] = -nums[val];
        }
        // if a value is not marked as negative, it implies we have never seen that index before, so just add it to the return list.
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) ret.add(i+1);
        }
        return ret;
    }
	
	// another idea
	// if nums[i] != i + 1 and nums[i] != nums[nums[i] - 1], then we swap nums[i] with nums[nums[i] - 1]
	// 	- > put nums[i] in the right place
	// In the end the array will be sorted and if nums[i] != i + 1, then i + 1 is missing.
	/*  the example run as follow
	 *  [4,3,2,7,8,2,3,1]
		[7,3,2,4,8,2,3,1]
		[3,3,2,4,8,2,7,1]
		[2,3,3,4,8,2,7,1]
		[3,2,3,4,8,2,7,1]
		[3,2,3,4,1,2,7,8]
		[1,2,3,4,3,2,7,8]
	 */
	// Since every swap we put at least one number to its correct position, the time is O(n)
	public List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
        	// every swap we put at least one number to its correct position
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) res.add(i + 1);
        }
        return res;
    }
}
