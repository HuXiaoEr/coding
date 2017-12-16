package leetcode.String;

/**
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
 */

//No
public class N5_Longest_Palindromic_Substring_M {
	
	//Not Mine
	//worst o(n*n)time
	//o(1) space
	private int lo, maxLen;
	public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2)
			return s;
		
	    for (int i = 0; i < len-1; i++) {
	     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
	     	extendPalindrome(s, i, i+1); //assume even length.
	    }
	    return s.substring(lo, lo + maxLen);
	}
	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}
	
	//DP
	//o(n*n) time
	//o(n*n) space
	/*
	 * dp(i, j) represents whether s(i ... j)  can form a palindromic substring
	 * dp[i, j] = 1 if i == j
           	    = s[i] == s[j]    if j = i + 1
                = s[i] == s[j] && dp[i + 1][j - 1]    if j > i + 1  
	 */
	public String longestPalindrome4(String s) {
		int n = s.length();
		String res = null;
		boolean[][] dp = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
				if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}
		return res;
	}
	//DP °Ô
	//o(n*n) time
	//o(n) space
	/*
	 * dp(i, j) represents whether s(i ... j)  can form a palindromic substring
	 * dp[i, j] = 1 if i == j
	           	= s[i] == s[j]    if j = i + 1
	            = s[i] == s[j] && dp[i + 1][j - 1]    if j > i + 1  
	 */
	public String longestPalindrome44(String s) {
		int n = s.length();
		String res = null;
		boolean[] dp = new boolean[n];
		for(int i = n-1; i >= 0; i--){
			for(int j = n-1; j >= i; j--){
				dp[j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[j-1]);
				if (dp[j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}
		return res;
	}
	
	
	// Best
	// Manacher's Algorithm  
	// o(n) time  ±º‰∏¥‘”∂»£ø
	// o(n) space
	// https://articles.leetcode.com/longest-palindromic-substring-part-ii/
	// my code
	public String longestPalindrome55(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++){
			sb.append("#" + s.charAt(i));
		}
		sb.append("#");
		String str = sb.toString();
		int[] p = new int[str.length()];
		int l = 0;
		int c = 0;
		int r = 0;
		p[0] = 0;
		int max = 0;
		for(int i = 1; i < str.length(); i++){
			if(i > r){
				c = i;
				int len = expand(str, i, i);
				l = c - len;
				r = c + len;
				p[c] = len;
				if(p[c] > p[max]) max = c;
			}
			else{
				int isym = 2 * c - i;
				if(p[isym] < r - i) p[i] = p[isym];  // attention : <   not <=
				else{
					c = i;
					int len = expand(str, i, r);
					l = c - len;
					r = c + len;
					p[c] = len;
					if(p[c] > p[max]) max = c;
				}
			}
		}
		return s.substring((max-p[max]) >> 1, (max+p[max] + 1) >> 1);
	}
	private int expand(String str, int i, int r) {
		
		int len = r - i;
		int lo = 2 * i - r - 1;
		int hi = r + 1;
		while(lo >= 0 && hi < str.length() && str.charAt(lo) == str.charAt(hi)){
			len++;
			lo--;
			hi++;
		}
		return len;
	}



	//Novel
	/*For friends who are confused about the key idea to check only new palindrome 
	  with length = current length +2 or +1, I add some more explanation here.
	Example: "xxxbcbxxxxxa", (x is random character, not all x are equal) now we 
          are dealing with the last character 'a'. The current longest palindrome
          is "bcb" with length 3.
	1. check "xxxxa" so if it is palindrome we could get a new palindrome of length 5.
	2. check "xxxa" so if it is palindrome we could get a new palindrome of length 4.
	3. do NOT check "xxa" or any shorter string since the length of the new string is 
   	   no bigger than current longest length.
	4. do NOT check "xxxxxa" or any longer string because if "xxxxxa" is palindrome 
   	   then "xxxx" got  from cutting off the head and tail is also palindrom. It has 
   	   length > 3 which is impossible.'
	 */
	public class Solution {
	    public String longestPalindrome(String s) {
	        String res = "";
	        int currLength = 0;
	        for(int i=0;i<s.length();i++){
	            if(isPalindrome(s,i-currLength-1,i)){
	                res = s.substring(i-currLength-1,i+1);
	                currLength = currLength+2;
	            }
	            else if(isPalindrome(s,i-currLength,i)){
	                res = s.substring(i-currLength,i+1);
	                currLength = currLength+1;
	            }
	        }
	        return res;
	    }
	    public boolean isPalindrome(String s, int begin, int end){
	        if(begin<0) return false;
	        while(begin<end){
	        	if(s.charAt(begin++)!=s.charAt(end--)) return false;
	        }
	        return true;
	    }
	}
	//similar
	public boolean isPalindrome(String s, int startIndex, int endIndex) {
		for(int i = startIndex, j = endIndex; i <= j; i++, j--) 
				if (s.charAt(i) != s.charAt(j)) return false;
		return true;
	}
	public String longestPalindrome7(String s) {
		int n = s.length();
		int longestLen = 0;
		int longestIndex = 0;
		
		for(int currentIndex = 0; currentIndex < n; currentIndex++) {
			if(isPalindrome(s,currentIndex - longestLen, currentIndex)){
				longestLen += 1;
				longestIndex = currentIndex;
			} else if(currentIndex - longestLen - 1 >= 0 && 
					  isPalindrome(s, currentIndex - longestLen - 1, currentIndex)) {
				longestLen += 2;
				longestIndex = currentIndex;
			}	
		}
		longestIndex++;
		return s.substring(longestIndex - longestLen, longestIndex);
	}
	
}
