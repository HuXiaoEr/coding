package reviewCode.leetcode;

import java.util.HashMap;

/**
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

//YES
public class N3_Longest_Substring_Without_Repeating_Characters_M {
	
	 //my code
	 public int lengthOfLongestSubstring(String s) {
	        if(s == null || s.length() == 0) return 0;
	    	int max = 1;
	    	char[] str = s.toCharArray();
	    	String dp = str[0]+"";
	        for(int i = 1; i < str.length; i++){
	        	StringBuilder sb = new StringBuilder();
	        	sb.append(str[i]);
	        	char[] temp = dp.toCharArray();
	        	int len = 1;
	        	for(int j = 0; j < temp.length; j++){
	        		if(str[i] != temp[j]){
	        			len++;
	        			sb.append(temp[j]);
	        		}
	        		else break;
	        	}
	        	dp = sb.toString();
	        	if(len > max) max = len;
	        }
	    	return max;
	    }
	 
	   //best
	   //O(n)
	   //the two pointers can only move forward
	   public int lengthOfLongestSubstring2(String s) {
	        if (s.length()==0) return 0;
	        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	        int max=0;
	        for (int i=0, j=0; i<s.length(); ++i){
	            if (map.containsKey(s.charAt(i))){
	                j = Math.max(j,map.get(s.charAt(i))+1);
	            }
	            map.put(s.charAt(i),i);
	            max = Math.max(max,i-j+1);
	        }
	        return max;
	    }
}
