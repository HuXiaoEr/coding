package leetcode.DP;
/**
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that 
adjacent houses have security system connected 
and it will automatically contact the police 
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight 
without alerting the police.
 */

//Yes
public class N_198_House_Robber_Easy_DP {
	boolean isNotValidInput;
	/**
	 * Mine DP
	 * Not best
	 * o(n*n) time
	 * o(1) space
	 */
	public int rob(int[] nums) {
        if(nums == null || nums.length == 0)
        {
        	isNotValidInput = true;
        	return 0;
        }
        for(int i = 2; i < nums.length; i++)
        {
        	int copy = nums[i];
        	for(int j = i-2; j >= 0; j--)
        	{
        		nums[i] = (copy+nums[j]) > nums[i] ? (copy+nums[j]) : nums[i];
        	}
        }
        return nums.length == 1 ? nums[0] : Math.max(nums[nums.length-1], nums[nums.length-2]);
    }
	
	/*
	 * Not Mine
	 * best
	 * o(n) time
	 * o(1) space
	 */
	public static int rob2(int[] nums) 
	{
		int ifRobbedPrevious = 0; 	// max monney can get if rob current house
	    int ifDidntRobPrevious = 0; // max money can get if not rob current house
	    
	    // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
	    for(int i=0; i < nums.length; i++) 
	    {
	    	// If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
	        int currRobbed = ifDidntRobPrevious + nums[i];
	        
	        // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
	        int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious); 
	        
	        // Update values for the next round
	        ifDidntRobPrevious  = currNotRobbed;
	        ifRobbedPrevious = currRobbed;
	    }
	    
	    return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
	}
	
	/*
	 * Not Mine
	 * better
	 * o(n) time
	 * o(1) space
	 */
	/**
	 * M(k) = money at the kth house
	 * P(0) = 0
	 * P(1) = M(1)
	 * P(k) = max(P(k−2) + M(k), P(k−1))
	 */
	public int rob3(int[] nums) {  
	    if(nums.length==0) return 0;
	    if(nums.length==1) return nums[0];

	    //Initialize an arrays to store the money
		int[] mark = new int[nums.length];

	    //We can infer the formula from problem:mark[i]=max(num[i]+mark[i-2],mark[i-1])
	    //so initialize two nums at first.
		mark[0] = nums[0];
		mark[1] = Math.max(nums[0], nums[1]);

	    //Using Dynamic Programming to mark the max money in loop.
		for(int i=2;i<nums.length;i++){
			mark[i] = Math.max(nums[i]+mark[i-2], mark[i-1]);
		}
		return mark[nums.length-1];
	}
	public int rob4(int[] num) {
        int last = 0;
        int now = 0;
        int tmp;
        for (int n :num) {
            tmp = now;
            now = Math.max(last + n, now);
            last = tmp;
        }
        return now;        
    }
	
	
}
