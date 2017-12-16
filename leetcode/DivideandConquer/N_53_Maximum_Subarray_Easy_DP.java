package leetcode.DivideandConquer;

/**
Find the contiguous subarray within an array (containing at least one number) 
which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, 
try coding another solution using the divide and conquer approach, 
which is more subtle.
 */

//Yes
public class N_53_Maximum_Subarray_Easy_DP {
	boolean isNotValidInput;
	/**
	 * Mine DP
	 * o(n)time  
	 * o(1)space
	 */
	public int maxSubArray(int[] nums) {
		if(nums == null || nums.length == 0)
		{
			isNotValidInput = true;
			return 0;
		}
        int max = nums[0];
        int temp = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
        	if(temp > 0) temp += nums[i];
        	else temp = nums[i];
        	if(temp > max) max = temp;
        }
		return max;
    }
	
	/**
	 * Not Mine
	 *Pre-Sum Array Solution - O(n) time, O(n) space
	 *The basic idea is to use pre-sum array, 
	 *max = Math.max(max, sum[i] - minSum). 
	 *(minSum is the minimum sum before A[i])
	*/
	public int maxSubArray2(int[] A) {
		if (A == null || A.length == 0) return 0;
		int max = A[0], minSum = Integer.MAX_VALUE;
		int sum[] = new int[A.length];
		sum[0] = A[0];	
		for (int i = 1; i < A.length; i++) {
			sum[i] = sum[i-1] + A[i];
			minSum = Math.min(0, Math.min(minSum, sum[i-1]));
			max = Math.max(max, sum[i] - minSum); 
		}
		return max;
	}
	
	/**
	 * https://discuss.leetcode.com/topic/25396/c-an-clear-o-n-divide-and-conquer-solution-with-comments
	 */
	
	//Divider and conquer solution 
	public int maxSubArray4(int[] nums) {
	    /*
	    Divide-and-conquer method.
	    The maximum summation of subarray can only exists under following conditions:
	    1. the maximum summation of subarray exists in left half.
	    2. the maximum summation of subarray exists in right half.
	    3. the maximum summation of subarray exists crossing the midpoints to left and right. 
	    1 and 2 can be reached by using recursive calls to left half and right half of the subarraies. 
	    Condition 3 can be found starting from the middle point to the left,
	    then starting from the middle point to the right. Then adds up these two parts and return. 
	    
	    T(n) = 2*T(n/2) + O(n)
	    this program runs in O(nlogn) time
	    */
	    
	    int maxsum = subArray(nums, 0, nums.length-1);
	    return maxsum;
	}
	private int subArray(int[] A, int left, int right){
	    if (left == right){
	        //base case
	        return A[left];
	    }
	    int mid = left + (right-left)/2;
	    int leftsum = subArray(A, left, mid); //left part of the subarray sum, condition 1
	    int rightsum = subArray(A, mid+1, right); //right part of the subarray sum, condition 2
	    int middlesum = midSubArray(A, left, mid, right); //cross part of the subarray sum, condition 3
	    // System.out.println(leftsum);
	    // System.out.println(rightsum);
	    // System.out.println(middlesum);
	    
	    //following if condition will return middlesum if lost the "=" under the conditon of leftsum = rightsum, which may be problematic if leftsum = rightsum < middlesum.
	    //for example: [-1, -1, -2, -2]
	    if (leftsum >= rightsum && leftsum >= middlesum){
	        return leftsum;
	    }
	    if (rightsum >= leftsum && rightsum >= middlesum){
	        return rightsum;
	    }
	    return middlesum;
	}
	private int midSubArray(int[] A, int left, int mid, int right){
	    int leftsum = Integer.MIN_VALUE;
	    int rightsum = Integer.MIN_VALUE;
	    int sum = 0;
	    for (int i = mid; i >= left; i--){
	        sum += A[i];
	        if (leftsum < sum){
	            leftsum = sum;
	        }
	    }
	    sum = 0;
	    for (int j = mid + 1; j <= right; j++){
	        sum += A[j];
	        if (rightsum < sum){
	            rightsum = sum;
	        }
	    }
	    return leftsum + rightsum;
	}
}
