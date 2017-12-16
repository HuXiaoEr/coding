package leetcode.DP;
/**
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */

//YES

public class N91_Decode_Ways_M {
	
///////////////////////////////////// Not Good ////////////////////////////////////////////////////////////////////////
	/*
	 * Mine DP
	 * careful "10" "01"
	 * "01" -> prenum == 0 ?
	 * "10" -> i - 2 == 0 ?
	 */
	public int numDecodings(String s) {
    	if(s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()+1];
        if(s.charAt(0)-48 != 0) dp[1] = 1;  //dp[0] = 0
        for(int i = 2; i <= s.length(); i++){
        	int num = s.charAt(i-1) - 48;
        	int prenum = s.charAt(i-2) - 48;
        	int a = (num == 0 ? 0 : (i-1 >= 0 ? dp[i-1] : 0));
        	int b = prenum == 0 ? 0 : ((prenum*10+num >= 1 && prenum*10+num <= 26) ? (i-2 >= 0 ? (i == 2 ? 1 : dp[i-2]) : 0) : 0); //бя
        	dp[i] = a + b;
        }
    	return dp[s.length()];
    }
	//space optimization o(1)
	//better than 1
	public int numDecodings2(String s) {
    	if(s == null || s.length() == 0) return 0;
    	int pre1 = 0, pre2 = 0, now = 0;
        if(s.charAt(0)-48 == 0) now = pre1 = 0;
        else now = pre1 = 1;
        for(int i = 2; i <= s.length(); i++){
        	int num = s.charAt(i-1) - 48;
        	int prenum = s.charAt(i-2) - 48;
        	int a = (num == 0 ? 0 : (i-1 >= 0 ? pre1 : 0));
        	int b = prenum == 0 ? 0 : ((prenum*10+num >= 1 && prenum*10+num <= 26) ? (i-2 >= 0 ? (i == 2 ? 1 : pre2) : 0) : 0); //бя

        	now = a + b;
        	pre2 = pre1;
        	pre1 = now;
        }
    	return now;
    }
///////////////////////////////////// Not Good ////////////////////////////////////////////////////////////////////////
	
	// б¤б¤
	//best mine
	//optimization
	public int numDecodings4(String s) {
    	if(s == null || s.length() == 0) return 0;
    	int pre1 = 0, pre2 = 0, now = 0;
    	
    	//similar to dp[0] = 1  бя
    	//Input "" should return 1 instead of 0
    	//There is 1 way to decoding "", the decoding result is "".
    	pre2 = 1;  
    	
        if(s.charAt(0)-48 == 0) now = pre1 = 0;
        else now = pre1 = 1;
        for(int i = 2; i <= s.length(); i++){
        	int num = s.charAt(i-1) - 48;  //or Integer.valueOf(s.substring(i-1, i));  Integer.valueOf(s.substring(i-2, i));
        	int prenum = s.charAt(i-2) - 48;
        	int a = (num == 0 ? 0 : (i-1 >= 0 ? pre1 : 0));
        	//int b = prenum == 0 ? 0 : ((prenum*10+num >= 1 && prenum*10+num <= 26) ? (i-2 >= 0 ? (i == 2 ? 1 : pre2) : 0) : 0); //бя
        	int b = (prenum*10+num >= 10 && prenum*10+num <= 26) ? pre2 : 0; //бя
        	now = a + b;
        	pre2 = pre1;
        	pre1 = now;
        }
    	return now;
    }
	
}
