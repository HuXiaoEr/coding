package reviewCode.leetcode;

public class N309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown_M {
    public static int maxProfit(int[] prices) {
    	int[] buy = new int[prices.length];
    	int[] sell = new int[prices.length];
    	int[] cooldown = new int[prices.length];
    	buy[0] = - prices[0];
    	
    	for(int i = 1; i < prices.length; i++){
    		buy[i] = cooldown[i-1]-prices[i];
    		sell[i] = Math.max(buy[i-1]+prices[i], cooldown[i-1]+prices[i]);
    		cooldown[i] = Math.max(cooldown[i-1], Math.max(sell[i-1], buy[i-1]));
    	}
        return Math.max(cooldown[prices.length-1], Math.max(sell[prices.length-1], buy[prices.length-1]));
    }
    
    public static void main(String[] args) {
		System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
	}
}
