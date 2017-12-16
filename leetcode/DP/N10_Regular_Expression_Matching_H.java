package leetcode.DP;
/**
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "a*") ¡ú true
isMatch("aa", ".*") ¡ú true
isMatch("ab", ".*") ¡ú true
isMatch("aab", "c*a*b") ¡ú true
 */

//NO
public class N10_Regular_Expression_Matching_H {
	
	/*
	 * dp[i][j] means if s[0..i-1] matches p[0..j-1] or not.   i/j means the numbers of data in str/regex.
	 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	 3, If p.charAt(j) == '*': 
   		here are two sub conditions:
        1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
        2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	 */
	public boolean isMatch(String str, String regex) {
        boolean[][] dp = new boolean[str.length() + 1][regex.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i < regex.length() + 1; i++) {
            if(regex.charAt(i - 1) == '*') dp[0][i] = dp[0][i-2];  //¡ï
        }
        for(int i = 1; i <= str.length(); i++) {
            for(int j = 1; j <= regex.length(); j++) {
                if(str.charAt(i-1) == regex.charAt(j-1) || regex.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    if(regex.charAt(j-1) == '*') {
                    	//There is no need to count the case where a* is used a SINGLE time. 
                    	//In each step when you see an * you either use it or you don't.
                        dp[i][j] = dp[i][j-2];
                        if(str.charAt(i-1) == regex.charAt(j-2) || regex.charAt(j-2) == '.') {
                            dp[i][j] |= dp[i-1][j];
                        }
                    }
                }
            }
        }
        return dp[str.length()][regex.length()];
    }
    
}
