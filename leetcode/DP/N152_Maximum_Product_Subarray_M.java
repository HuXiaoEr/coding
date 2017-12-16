package leetcode.DP;

/**
Find the contiguous subarray within an array (containing at least one number) 
which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 */

//NO
//YES TWO
public class N152_Maximum_Product_Subarray_M {
	
	//Not Mine DP
	public int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;  //important  NO TWO
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]); //бя
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]); //бя
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
}
