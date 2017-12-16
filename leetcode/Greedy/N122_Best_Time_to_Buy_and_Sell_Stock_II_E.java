package leetcode.Greedy;
/**
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

//NO
public class N122_Best_Time_to_Buy_and_Sell_Stock_II_E {
	/*
	 * the code has any conflict with the requirement "you can't sell and buy at the same time position". 
	 * Accumulating the gain profit every day бя
	 * does not have to mean that I sell and buy the stock every day. 
	 * I just check my account and say :"wow, I gained more xx profit!" when the stock price is increasing.
	 */
	public int maxProfit(int[] prices) {
	    int total = 0;
	    for (int i=0; i< prices.length-1; i++) {
	        if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
	    }
	    return total;
	}
	
	public int maxProfit2(int[] prices) {
	    int profit = 0, i = 0;
	    while (i < prices.length) {
	        // find next local minimum
	        while (i < prices.length-1 && prices[i+1] <= prices[i]) i++;
	        int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
	        // find next local maximum
	        while (i < prices.length-1 && prices[i+1] >= prices[i]) i++;
	        profit += i < prices.length ? prices[i++] - min : 0;
	    }
	    return profit;
	}
}
