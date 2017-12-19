package reviewCode.leetcode;

public class Solution {

	// my code
	// 995ms -> beats 0.45%
    public boolean isMatch(String s, String p) {
    	if(s == null || p == null) return false;
    	boolean[] dp = new boolean[p.length()+1];
        dp[0] = true;
        boolean pre = dp[0];
        boolean now = false;
        for(int i = 1; i <= p.length(); i++){
            if(p.charAt(i-1) == '*') dp[i] = dp[i-2];
        }
        for(int i = 1; i <= s.length(); i++){
        	for(int j = 1; j <= p.length(); j++){
        			now = dp[j];
        			if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
        				dp[j] = pre;
        			}
        			else if(p.charAt(j-1) == '*'){
        				if(j >= 2 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')){
        					dp[j] = dp[j-1] || dp[j-2] || now;
        				}
        				else{
        					dp[j] = dp[j-2];
        				}
        			}
        			pre = now;
        		}
        }
        return dp[p.length()];

    }

	public static void main(String[] args) {
		System.out.println(new Solution().isMatch("aab", "c*a*b"));
	}

}
