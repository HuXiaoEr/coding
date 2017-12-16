package leetcode.DP;

import java.util.Arrays;

/**
Note: This is an extension of House Robber.

After robbing those houses on that street, 
the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
 */

//No
//DP
public class N213_House_Robber_II_M {
	
	//Not Mine
	//Best
	/*
	 * Since every house is either robbed or not robbed and at least half of the houses are not robbed, 
	 * the solution is simply the larger of two cases with consecutive houses, 
	 * i.e. house i not robbed, break the circle, solve it, 
	 * or house i + 1 not robbed. Hence, the following solution. 
	 * I chose i = n and i + 1 = 0 for simpler coding. 
	 * But, you can choose whichever two consecutive ones.
	 * 
	 * this problem can simply be decomposed into two House Robber problems.
	 * (1) n-1 not robbed -- Rob houses 0 to n - 2;
	 * (2) 0 not robbed   -- Rob houses 1 to n - 1.
	 */
	public int rob(int[] nums) {
	    if (nums.length == 1) return nums[0];
	    return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
	}
	private int rob(int[] num, int lo, int hi) {
	    int include = 0, exclude = 0;
	    for (int j = lo; j <= hi; j++) {
	        int i = include, e = exclude;
	        include = e + num[j];
	        exclude = Math.max(e, i);
	    }
	    return Math.max(include, exclude);
	}
	
	//Not Best
	/*
	 * (1) i not robbed
	 * (2) i robbed
	 * Same as the solution above, if i is not robbed, it will break the circle, 
	 * we can just pass the rest n-1 elements. 
	 * But if i is robbed, then i-1 and i+1 can not be robbed, 
	 * it also will break the circle. We can just pass the rest n-3 elements and plus nums[i]. 
	 * This idea is easy to understand to me because ith house should be either robbed or not robbed. 
	 * Here I pick i = 0.
	 */
	public int rob0(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        return Math.max(rob1(Arrays.copyOfRange(nums, 1, nums.length)), nums[0] + rob1(Arrays.copyOfRange(nums, 2, nums.length-1)));
    }
    public int rob1(int[] nums) {
        int preno = 0;
        int preyes = nums[0];
        for (int i = 1; i < nums.length; i++)
        {
            int tmp = preyes;
            preyes = preno + nums[i];
            preno = Math.max(preno, tmp);
        }
        return Math.max(preyes, preno);
    }
}
