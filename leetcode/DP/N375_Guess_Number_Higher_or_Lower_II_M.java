package leetcode.DP;
/**
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. 
You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n �� 1, find out how much money you need to have to guarantee a win.
 */

//NO
//looking for the minimum amount of money you need to guarantee a win
//Minimax ��
public class N375_Guess_Number_Higher_or_Lower_II_M {

	/*
	 * dp[i][j]: minimum number of money to guarantee win for subproblem [i, j].
	 * Target: dp[1][n]
	 * Corner case: dp[i][i] = 0 (because the only element must be correct)
	 * -> dp[i][j] = min (i<=k<=j) { k + max(dp[i][k-1], dp[k+1][j]) }
	 * 
	 * Equation: we can choose k (i<=k<=j) as our guess, and pay price k. After our guess, the problem is divided into two subproblems. 
	 * Notice we do not need to pay the money for both subproblems. 
	 * We only need to pay the worst case (because the system will tell us which side we should go) to guarantee win.
	 */
	//bottom up solution
	public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];
        for (int len = 1; len < n; len++) {
            for (int from = 1, to = from + len; to <= n; from++, to++) {
                dp[from][to] = Integer.MAX_VALUE;
                for (int k = from; k <= to; k++)
                    dp[from][to] = Math.min(dp[from][to], k + Math.max(dp[from][k - 1], dp[k + 1][to]));
            }
        }
        return dp[1][n];
    }
	
	//up bottom solution
	public int getMoneyAmount2(int n) {
        int[][] table = new int[n+1][n+1];
        return DP(table, 1, n);
    }
    int DP(int[][] t, int s, int e){
        if(s >= e) return 0;
        if(t[s][e] != 0) return t[s][e];
        int res = Integer.MAX_VALUE;
        for(int x=s; x<=e; x++){
            int tmp = x + Math.max(DP(t, s, x-1), DP(t, x+1, e));
            res = Math.min(res, tmp);
        }
        t[s][e] = res;
        return res;
    }
}
