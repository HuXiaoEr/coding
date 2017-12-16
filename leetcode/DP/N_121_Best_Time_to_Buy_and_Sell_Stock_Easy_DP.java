package leetcode.DP;

/**
Say you have an array for which 
the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 
(not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
 */

//No
//YES TWO
public class N_121_Best_Time_to_Buy_and_Sell_Stock_Easy_DP {
	
	//Mine, Not right
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length == 0) return 0;
        int min = prices[0];
        int max = prices[0];
        boolean flag = false;
        for(int i = 1; i < prices.length; i++)
        {
        	if(!flag)
        	{
        		if(prices[i] <= prices[i-1])
        		{
        			min = max = prices[i];
        		}
        		else
        		{
        			flag = true;
        			max = prices[i];
        		}
        	}
        	else
        	{
        		if(prices[i] < min) min = prices[i];
        		if(prices[i] > max) max = prices[i];
        	}
        }
		return max-min;
    }
	
	/**
	 * Not Mine
	 * using Kadane's Algorithm, same as "max subarray problem"
	 * All the straight forward solution should work, 
	 * but if the interviewer twists the question slightly 
	 * by giving the difference array of prices, 
	 * Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, 
	 * you might end up being confused. 
	 * 
	 * the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) 
	 * of the original array, and find a contiguous subarray giving maximum profit. 
	 * If the difference falls below 0, reset it to zero.
	 * 
	 * maxCur = current maximum value
	 * maxSoFar = maximum value found so far
	 */
	//best
	public int maxProfit2(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
	
	//Not Mine
	//straight forward solution
	public int maxProfit3(int[] prices) {
        // The trivial case.
        if (prices.length == 0)
            return 0;
            
        // The sequence will be divided into several segments, such that the first element
        // (which is the variable p) of each segment is strictly less than the previous one.

        // Every segment (subsequence) will yield a local optimum (which is the variable maxProfit).
        int maxProfit = 0;
        int min = prices[0];
        for (int p : prices){
            if (p >= min)
                maxProfit = (p - min > maxProfit) ? p - min : maxProfit;
            else
                min = p;
        }
        return maxProfit;
    }
}
