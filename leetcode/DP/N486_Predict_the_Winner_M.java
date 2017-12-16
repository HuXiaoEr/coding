package leetcode.DP;

/**
Given an array of scores that are non-negative integers. 
Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. 
Each time a player picks a number, that number will not be available for the next player. 
This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. 
You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. 
No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1. 1 <= length of the array <= 20.
2. Any scores in the given array are non-negative integers and will not exceed 10,000,000.
3. If the scores of both players are equal, then player 1 is still the winner.
 */

//NO
//Minimax °Ô
public class N486_Predict_the_Winner_M {
	
	//wrong 
	public boolean PredictTheWinner(int[] nums) {
        return PredictTheWinner(nums, 0, nums.length-1, 0, 0, true);
    }
	private boolean PredictTheWinner(int[] nums, int lo, int hi, int sum1, int sum2, boolean flag) {
		if(lo > hi) return sum1 > sum2;
		if(flag == true) return PredictTheWinner(nums, lo+1, hi, sum1+nums[lo], sum2, false) && PredictTheWinner(nums, lo, hi-1, sum1+nums[hi], sum2, false);
		else return PredictTheWinner(nums, lo+1, hi, sum1, sum2+nums[lo], true) && PredictTheWinner(nums, lo, hi-1, sum1, sum2+nums[hi], true);
	}
	
	/*
	 * dp[i][j] means the max sum we can get if the array starts from i and ends to j
	 * dp[i][j] = max( min (dp[i + 1][j - 1], dp[i + 2][ j]) + v[i], min (dp[i][j - 2], dp[i + 1][ j - 1]) + v[j]}) .
	 */
	public boolean PredictTheWinner4(int[] nums) {
		int n = nums.length, sum = 0;
		if (n % 2 == 0) return true;  //°Ô
		int[][] dp = new int[n][n];
		for(int i = 0; i < n; i++) {
			dp[i][i] = nums[i];
			sum += nums[i];
		}
		for (int j = 0; j < n; j++) {
			for (int i = j - 1; i >= 0; i--) {
				int a = (i + 1 < n && j - 1 >= 0) ? dp[i + 1][j - 1] : 0;
				int b = (i + 2 < n) ? dp[i + 2][j] : 0;
				int c = (j - 2 >= 0) ? dp[i][j - 2] : 0;
				dp[i][j] = Math.max(Math.min(a, b) + nums[i], Math.min(a, c) + nums[j]);
			}
		}
		return dp[0][n - 1] * 2 >= sum;
    }
	
	/*
	 * dp[i][j] saves how much more scores that the first-in-action player will get from i to j than the second player
	 * dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
	 * 
	 * --> similar to 0/1±≥∞¸Œ Ã‚
	 */
	public boolean PredictTheWinner5(int[] nums) {
		int n = nums.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = nums[i];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				int a = i + 1 < n ? dp[i + 1][j] : 0;
				dp[i][j] = Math.max(nums[i] - a, nums[j] - dp[i][j - 1]);
			}
		}
		return dp[0][n - 1] >= 0;
	}
	//o(n) space
	public boolean PredictTheWinner6(int[] nums) {
	    if (nums == null) { return true; }
	    int n = nums.length;
	    if ((n & 1) == 0) { return true; } // Improved with hot13399's comment.
	    int[] dp = new int[n];
	    for (int i = n - 1; i >= 0; i--) {
	        for (int j = i; j < n; j++) {
	            if (i == j) {
	                dp[i] = nums[i];
	            } else {
	                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
	            }
	        }
	    }
	    return dp[n - 1] >= 0;
	}
	
	
	/*
	 * Explanation
		So assuming the sum of the array it SUM, so eventually player1 and player2 will split the SUM between themselves. 
		For player1 to win, he/she has to get more than what player2 gets. 
		If we think from the prospective of one player, then what he/she gains each time is a plus, while, 
		what the other player gains each time is a minus. Eventually if player1 can have a >0 total, player1 can win.

		Helper function simulate this process. In each round:
		if e==s, there is no choice but have to select nums[s]
		otherwise, this current player has 2 options:
		--> nums[s]-helper(nums,s+1,e): this player select the front item, leaving the other player a choice from s+1 to e
		--> nums[e]-helper(nums,s,e-1): this player select the tail item, leaving the other player a choice from s to e-1
		Then take the max of these two options as this player's selection, return it.
	 */
	public boolean PredictTheWinner2(int[] nums) {
        return helper(nums, 0, nums.length-1)>=0;
    }
	/* Helper function returns the maximum gain for the current player in this round with the selection choice from s to e. 
	 * Gain: plus his/her own selection in this round and minus the opponent's gain in the next round.
	 */
    private int helper(int[] nums, int s, int e){        
        return s==e ? nums[e] : Math.max(nums[e] - helper(nums, s, e-1), nums[s] - helper(nums, s+1, e));
    }	
    //add a cache
    public boolean PredictTheWinner3(int[] nums) {
        return helper(nums, 0, nums.length-1, new Integer[nums.length][nums.length])>=0;
    }
    private int helper(int[] nums, int s, int e, Integer[][] mem){    
        if(mem[s][e]==null)
            mem[s][e] = s==e ? nums[e] : Math.max(nums[e]-helper(nums,s,e-1,mem),nums[s]-helper(nums,s+1,e,mem));
        return mem[s][e];
    }
    
    
}
