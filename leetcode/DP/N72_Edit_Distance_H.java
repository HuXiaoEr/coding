package leetcode.DP;
/**
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 */

//NO
//dp
public class N72_Edit_Distance_H {
	
	//my code 
	//À„∑®¥Ì¡À
    public static int minDistance10(String word1, String word2) {
    	if(word1 == null || word2 == null) return 0;
        int res = 0;
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 1; i <= len1; i++){
        	for(int j = 1; j <= len2; j++){
        		if(word2.charAt(j-1) == word1.charAt(i-1))
        			dp[i][j] = dp[i-1][j-1] + 1;
        		else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
        	}
        }
        res = Math.max(len1, len2) - dp[len1][len2];
    	return res;
    }
    
    //Not Mine
    /*
     * dp[i][j] the minimum number of operations to convert word1[0..i - 1] to word2[0..j - 1].
     * 	 dp[i][0] = i;
     * 	 dp[0][j] = j;
     * 	 dp[i][j] = dp[i - 1][j - 1], if word1[i - 1] = word2[j - 1];
     * 	 dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1), otherwise.
     * 
     * If they are not equal, we need to consider three cases:
     * Replace word1[i - 1] by word2[j - 1] (dp[i][j] = dp[i - 1][j - 1] + 1 (for replacement));
     * Delete word1[i - 1] and word1[0..i - 2] = word2[0..j - 1] (dp[i][j] = dp[i - 1][j] + 1 (for deletion));
     * Insert word2[j - 1] to word1[0..i - 1] and word1[0..i - 1] + word2[j - 1] = word2[0..j - 1] (dp[i][j] = dp[i][j - 1] + 1 (for insertion)).
     * 
     */
    public static int minDistance(String word1, String word2) {
    	int len1 = word1.length(), len2 = word2.length();
    	int[][] dp = new int[len1+1][len2+1];
    	for(int i = 1; i <= len1; i++) dp[i][0] = i;
    	for(int j = 1; j <= len2; j++) dp[0][j] = j;
    	for(int i = 1; i <= len1; i++){
    		for(int j = 1; j <= len2; j++){
    			if (word1.charAt(i-1) == word2.charAt(j-1)) 
                    dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
    		}
    	}
    	return dp[len1][len2];
    }
    
    //space optimization
    //best
    //beats 84%
    public static int minDistance2(String word1, String word2) {
    	int len1 = word1.length(), len2 = word2.length();
    	int[] dp = new int[len1+1];
    	for(int i = 1; i <= len1; i++) dp[i] = i; //dp[i][0] = i;
    	for(int j = 1; j <= len2; j++){
    		int pre = dp[0]; //restore dp[0][0]
    		dp[0] = j;  //°Ô°Ô  initialization dp[0][j] = j;
    		for(int i = 1; i <= len1; i++){
    			int temppre = dp[i];  
    			if (word1.charAt(i-1) == word2.charAt(j-1)) 
                    dp[i] = pre;  //calculate dp[i][j]
                else dp[i] = Math.min(pre + 1, Math.min(dp[i - 1] + 1, dp[i] + 1));
    			pre = temppre; //restore dp[i][j-1] to calculate dp[i+1][j]
    		}
    	}
    	return dp[len1];
    }

}
