package reviewCode.leetcode;

public class N121_Best_Time_to_Buy_and_Sell_Stock_E {
    public int maxProfit(int[] prices) {
    	if(prices == null) return 0;
    	int max = 0;
    	int profit = 0;
    	for(int i = 1; i < prices.length; i++){
    		profit = profit+prices[i]-prices[i-1] >= 0 ? profit+prices[i]-prices[i-1] : 0;
    		max = Math.max(max, profit);
    	}
    	return max;
    }
}
