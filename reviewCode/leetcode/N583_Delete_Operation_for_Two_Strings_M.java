package reviewCode.leetcode;

/**
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, 
where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
 */

// YES TWO
public class N583_Delete_Operation_for_Two_Strings_M {
	
	// my code ->  67ms 47.7%
    public int minDistance(String word1, String word2) {
    	if(word1 == null && word2 == null) return 0;
    	if(word1 == null || word2 == null) return -1;
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 1; i <= len1; i++){
        	for(int j = 1; j <= len2; j++){
        		if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
        		else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        	}
        }
        return len1 + len2 - dp[len1][len2]*2;
    }
    
    // o(n) space solution ?? 
    // https://discuss.leetcode.com/topic/96522/short-java-dp-solution-o-n-space-48ms
}
