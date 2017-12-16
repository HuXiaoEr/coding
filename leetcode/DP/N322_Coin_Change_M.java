package leetcode.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
 */

//NO
public class N322_Coin_Change_M {
	
	//DP
	//Not Mine
	//better
	public static int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount <= 0)
			return 0;
		int[] minNumber = new int[amount + 1]; //бя
		for (int i = 1; i <= amount; i++) {
			minNumber[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i && minNumber[i - coins[j]] != Integer.MAX_VALUE)
					minNumber[i] = Integer.min(minNumber[i], 1 + minNumber[i - coins[j]]);
			}
		}
		if (minNumber[amount] == Integer.MAX_VALUE)
			return -1;
		else
			return minNumber[amount];
	}
	//same idea
	//not better
	public int coinChange2(int[] coins, int amount) {
	    if (amount < 1) return 0;
	    int[] dp = new int[amount + 1]; 
	    Arrays.fill(dp, Integer.MAX_VALUE);
	    dp[0] = 0;
	    for (int coin : coins) {
	        for (int i = coin; i <= amount; i++) {
	            if (dp[i - coin] != Integer.MAX_VALUE) {
	                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
	            }
	        }
	    }
	    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}
	
	//Recursive DFS
	public int coinChange3(int[] coins, int amount) {
        if(amount==0)
            return 0;
        int n = amount+1;
        for(int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = coinChange(coins, amount-coin);
                if(next >= 0)
                    curr = 1+next;
            }
            if(curr > 0)
                n = Math.min(n,curr);
        }
        int finalCount = (n==amount+1) ? -1 : n;
        return finalCount;
    }
	//same idea 
	//better
	public int coinChange4(int[] coins, int amount) {
	    if(amount<1) return 0;
	    return helper(coins, amount, new int[amount]);
	}
	private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
	    if(rem<0) return -1; // not valid
	    if(rem==0) return 0; // completed
	    if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
	    int min = Integer.MAX_VALUE;
	    for(int coin : coins) {
	        int res = helper(coins, rem-coin, count);
	        if(res>=0 && res < min)
	            min = 1+res;
	    }
	    count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
	    return count[rem-1];
	}
}
