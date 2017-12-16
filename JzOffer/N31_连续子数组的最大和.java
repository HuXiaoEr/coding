package JzOffer;

public class N31_连续子数组的最大和 {
	boolean invalidInput;
	public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0)
        {
        	invalidInput = true;
        	return 0;
        }
        int maxSubSum = array[0];
        int sumNow = array[0];
        for(int i = 1; i < array.length; i++)
        {
        	sumNow = Math.max(array[i], sumNow+array[i]);
        	maxSubSum = Math.max(maxSubSum, sumNow);
        }
		return maxSubSum;
    }
}
