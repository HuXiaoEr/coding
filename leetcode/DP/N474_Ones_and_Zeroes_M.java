package leetcode.DP;
/**
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. 
On the other hand, there is an array with strings consisting of only 0s and 1s.

★ Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. ★
Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 */

//NO
public class N474_Ones_and_Zeroes_M {
	/*
	 * 题意理解错误
	 * 
    public int findMaxForm(String[] strs, int m, int n) {
        boolean[] flag = new boolean[strs.length];
        int[][] count = new int[strs.length][2];
        for(int i = 0; i < strs.length; i++){
        	int count0 = 0;
        	int count1 = 0;
        	for(int j = 0; j < strs[i].length(); j++){
        		if(strs[i].charAt(j) == '0') count0++;
        		if(strs[i].charAt(j) == '1') count1++;
        	}
        	count[i][0] = count0;
        	count[i][1] = count1;
        }
        
    	int max = findMaxForm(count, flag, m, n);
    	return max == -1 ? 0 : max;
    }
	private int findMaxForm(int[][] count, boolean[] flag, int m, int n) {
		if(m < 0 || n < 0) return -1;
		if(m == 0 && n == 0) return 0;
		int tempmax = -1;
		for(int i = 0; i < count.length; i++){
			if(!flag[i]){
				flag[i] = true;
				int tempres = findMaxForm(count, flag, m-count[i][0], n-count[i][1]);
				if(tempres != -1) tempmax = Math.max(tempmax, tempres+1);
				flag[i] = false;
			}
		}
		//System.out.println(tempmax+"SS");
		return tempmax;
	}
	*/
	public static void main(String[] args) {
		
		System.out.println(new N474_Ones_and_Zeroes_M().findMaxForm(new String[]{"0","1","10"}, 1, 1));
	}
	
	// 0-1 knapsack problem
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] dp = new int[m+1][n+1];
		for(int i = 0; i < strs.length; i++){
			int count0 = 0;
			int count1 = 0;
			for(int j = 0; j < strs[i].length(); j++){
				if(strs[i].charAt(j) == '0') count0++;
				if(strs[i].charAt(j) == '1') count1++;
			}
			for(int j = m; j >= count0; j--){
				for(int k = n; k >= count1; k--){
					dp[j][k] = Math.max(dp[j][k], 1+dp[j-count0][k-count1]);
					System.out.println("j=" + j + " k=" + k + " dp[j][k]=" + dp[j][k]);
				}
			}
			
			/*
			 * wrong
			for(int j = m; j > 0; j--){
				for(int k = n; k > 0; k--){
					if(j >= count0 && k >= count1) dp[j][k] = Math.max(dp[j][k], 1+dp[j-count0][k-count1]);
				}
			}
			*/
			/*
			 * right
			for(int j = m; j >= 0; j--){  ★
				for(int k = n; k >= 0; k--){  ★
					if((j >= count0) && (k >= count1)) dp[j][k] = Math.max(dp[j][k], 1+dp[j-count0][k-count1]);
				}
			}
			*/
		}
		return dp[m][n];
	}
}
