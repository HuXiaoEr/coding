package leetcode.DP;
/**
Given a string s, find the longest palindromic subsequence's length in s. 
You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 */
//Explain:如果一个字符串从左向右写和从右向左写是一样的，这样的字符串就叫做palindromic string，如aba,或者abba。
//★不一定连续

//No
public class N516_Longest_Palindromic_Subsequence_M {
	
	//DP
	/*2D-DP
	 *dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
	  State transition:
	  dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
	  otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
	  Initialization: dp[i][i] = 1
	 */
	public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
	
	//1D-DP
	//...
	
	//another 2D-DP idea
	public int longestPalindromeSubseq5(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = 0;i < len;i++){
            dp[i][i] = 1;
        }
        //for each interval length
        for(int l = 2;l <= len;l++){
            //for each interval with the same length
            for(int st = 0;st <= len-l;st++){
                int ed = st+l-1;
                //if left end equals to right end or not
                dp[st][ed] = s.charAt(st)==s.charAt(ed)? dp[st+1][ed-1]+2 : Math.max(dp[st+1][ed], dp[st][ed-1]);
            }
        }
        return dp[0][len-1];
    }
	//brute force
	//O(2^n) ??
	int longestPalindromeSubseq2(String s) {
        return longestPalindromeSubseq(0,s.length()-1,s); 
    }
    int longestPalindromeSubseq(int l, int r, String s) {
        if(l==r) return 1;
        if(l>r) return 0;  //happens after "aa" 
        return s.charAt(l)==s.charAt(r) ? 2 + longestPalindromeSubseq(l+1,r-1, s) : 
            Math.max(longestPalindromeSubseq(l+1,r, s),longestPalindromeSubseq(l,r-1, s)); 
    }
    
    //O(n^2) Memoization
    public int longestPalindromeSubseq3(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }
    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j)      return 0;
        if (i == j)     return 1;
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
    
    /*
     * Longest common subsequence
     * 
     * If we reverse the string, 
     * then it becomes an equivalent problem: 'find the longest common subsequence' 
     * -> a typical DP method could solve it. 
     * You may traverse from the last element for the first loop, 
     * so that you don't need to reverse the string. What else shall I say?
     */
	
}
