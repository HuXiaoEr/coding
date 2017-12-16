package leetcode.DP;
/**
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. 
The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. 
In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, 
the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. 
A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, 
leaving the remaining elements in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?
 */

//»¹Î´Ã÷°×
//NO
//Greedy
public class N376_Wiggle_Subsequence_M {
	
	//careful same element fp:[0,0]
	//not pass all -> wrong answer
	public int wiggleMaxLength(int[] nums) {
		if(nums == null) return 0;
        int max = 0;
        int[] dp = new int[nums.length];
        boolean[] flag = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
        	int length = 1;
        	//flag[i] = nums[i] > nums[i-1];
        	for(int j = i-1; j >= 0; j--){
        		if((j == 0 && nums[i] != nums[0]) || (nums[i] > nums[j]) != flag[j]){
        			if(dp[j] >= length){
        				length = dp[j] + 1;
        				flag[i] = nums[i] > nums[j];
        			}
        		}
        	}
        	dp[i] = length;
        	max = Math.max(max, dp[i]);
        }
		return max;
    }
	
	//both get the maxlength and  the Wiggle series
	public int wiggleMaxLength2s(int[] nums) {
		if (nums.length == 0 || nums.length == 1) {
			return nums.length;
		}
		int k = 0;
		while (k < nums.length - 1 && nums[k] == nums[k + 1]) {  //Skips all the same numbers from series beginning eg 5, 5, 5, 1
			k++;
		}
		if (k == nums.length - 1) {
			return 1;
		}
		int result = 2;     // This will track the result of result array
		boolean smallReq = nums[k] < nums[k + 1];       //To check series starting pattern
		for (int i = k + 1; i < nums.length - 1; i++) {
			if (smallReq && nums[i + 1] < nums[i]) {
				nums[result] = nums[i + 1];
				result++;
				smallReq = !smallReq;    //Toggle the requirement from small to big number
			} else {
				if (!smallReq && nums[i + 1] > nums[i]) {
					nums[result] = nums[i + 1];
					result++;
					smallReq = !smallReq;    //Toggle the requirement from big to small number
				}
			}
		}
		return result;
	}
}
