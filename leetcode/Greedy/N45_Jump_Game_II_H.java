package leetcode.Greedy;
/**
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
 */

//NO
public class N45_Jump_Game_II_H {
	
	//similar to BFS
	//greedy
	/*
	 * The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd], 
	 * curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. 
	 * Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, 
	 * then keep the above steps, as the following:
	 */
    public int jump(int[] nums) {
    	int step_count = 0;
    	int last_jump_max = 0;
    	int current_jump_max = 0;
    	for(int i=0; i<nums.length-1; i++) {
    	    current_jump_max = Math.max(current_jump_max, i+nums[i]);
    	    if( i == last_jump_max ) {
    	        step_count++;
    	        last_jump_max = current_jump_max;
    	    } 
    	}
    	return step_count;
    }
}
