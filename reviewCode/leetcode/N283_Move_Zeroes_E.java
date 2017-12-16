package reviewCode.leetcode;
/**
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */


public class N283_Move_Zeroes_E {
	
	// YES HALF
	// Bad Bad
	public void moveZeroes(int[] nums) {
		int end = nums.length-1;
        for(int i = 0; i < end; ){  // i < end
        	if(nums[i] == 0){
        		for(int j = i; j < end; j++) exch(nums, j, j+1);
        		end--;
        	}
        	else i++;  // i++
        }
    }
	private void exch(int[] nums, int j, int i) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	// Shift non-zero values as far forward as possible
	// Fill remaining space with zeros
	public void moveZeroes2(int[] nums) {
	    if (nums == null || nums.length == 0) return;        

	    int insertPos = 0;
	    for (int num: nums) {
	        if (num != 0) nums[insertPos++] = num;
	    }        

	    while (insertPos < nums.length) {
	        nums[insertPos++] = 0;
	    }
	}
	
	// 1 ms
	public void moveZeroes3(int[] nums) {
	    int j = 0;
	    for(int i = 0; i < nums.length; i++) {
	        if(nums[i] != 0) {
	            int temp = nums[j];
	            nums[j] = nums[i];
	            nums[i] = temp;
	            j++;
	        }
	    }
	}
	
	// in - place
	public void moveZeroes4(int[] nums) {
	    int count = 0;
	    for (int i = 0; i < nums.length; i++) {
	        if (nums[i] == 0) {
	            count++;
	        } else {
	            nums[i - count] = nums[i];
	            if (count != 0) {nums[i] = 0;}
	        }
	    }
	    return;
	}
	
	
	
	
	public static void main(String[] args) {
		new N283_Move_Zeroes_E().moveZeroes(new int[]{0,1,0,3,12});
	}
}
