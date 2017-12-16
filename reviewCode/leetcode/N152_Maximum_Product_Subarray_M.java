package reviewCode.leetcode;
/**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 */

//NO
public class N152_Maximum_Product_Subarray_M {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
        	int maxc = max;  //important  NO TWO
        	int minc = min;
        	max = Math.max(nums[i], Math.max(maxc*nums[i], minc*nums[i]));
        	min = Math.min(nums[i], Math.min(maxc*nums[i], minc*nums[i]));
        	res = Math.max(res, max);
        }
    	return res;
    }
}
