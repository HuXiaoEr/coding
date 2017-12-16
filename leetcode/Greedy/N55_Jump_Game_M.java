package leetcode.Greedy;
/**
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 */

//NO
public class N55_Jump_Game_M {
	
	//Mine Code  -> TLE
	public boolean canJump(int[] nums) {
    	return canJump(nums, 0);
    }
	private boolean canJump(int[] nums, int start) {
		if(start >= nums.length-1) return true;
		boolean res = false;
		for(int i = start+1; i <= start+nums[start]; i++){
			res |= canJump(nums, i);
			if(res) return true;
		}
		return false;
	}
	
	//б¤ Not Mine
	public boolean canJump2(int[] nums) {
    	int i = 0; 
    	for(int reach = 0; i < nums.length && i <= reach; i++){
    		reach = Math.max(i+nums[i], reach);
    	}
    	return i == nums.length;
    }
	//best
    public boolean canJump3(int[] nums) {
    	int i = 0, maxreach = 0; 
    	for(; i < nums.length && i <= maxreach && maxreach < nums.length-1; i++){
    		maxreach = Math.max(i+nums[i], maxreach);
    	}
    	return maxreach >= nums.length-1;
    }
    
    /*
     * Idea is to work backwards from the last index. Keep track of the smallest index that can "jump" to the last index. 
     * Check whether the current index can jump to this smallest index.
     */
    boolean canJump4(int A[], int n) {
        int last=n-1,i,j;
        for(i=n-2;i>=0;i--){
            if(i+A[i]>=last)last=i;
        }
        return last<=0;
    }
    
    public boolean canJump5(int[] nums) {
        if(nums.length < 2) return true;
        for(int curr = nums.length-2; curr>=0;curr--){
            if(nums[curr] == 0){  //бя
                int neededJumps = 1;
                while(neededJumps > nums[curr]){
                    neededJumps++;
                    curr--;
                    if(curr < 0) return false;
                }
            }
        }
        return true;
     }
	
}
