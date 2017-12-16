package leetcode.twoPointers;

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
	
	
	
	// my code
	public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] hash = new int[256];
        for(int i = 0; i < hash.length; i++) hash[i] = -1;
        int maxLen = Integer.MIN_VALUE;
        int len = 0;
        int lo = 0;
        for(int i = 0; i < s.length(); i++){
        	char c = s.charAt(i);
     	    //the two pointers can only move forward
        	// donnot forget "hash[c] + 1 > lo"   exa "abba"
        	if(hash[c] != -1 && hash[c] + 1 > lo){
        		lo = hash[c] + 1;
        	}
        	maxLen = Math.max(maxLen, i-lo+1);
        	hash[c] = i;
        }
        return maxLen;
    }
		
		// not mine
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
	
	// my code
	public int lengthOfLongestSubstring44(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int max = 1;
		char[] str = s.toCharArray();
		String dp = str[0] + "";
		for (int i = 1; i < str.length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(str[i]);
			char[] temp = dp.toCharArray();
			int len = 1;
			for (int j = 0; j < temp.length; j++) {
				if (str[i] != temp[j]) {
					len++;
					sb.append(temp[j]);
				} else
					break;
			}
			dp = sb.toString();
			if (len > max)
				max = len;
		}
		return max;
	}

	
	
	
	// best
	// O(n)
	// the two pointers can only move forward
	// N76_Minimum_Window_Substring_H
	public int lengthOfLongestSubstring3(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			map.put(c, 0); //// map初始化
		}
		int start = 0, end = 0, resMaxLen = 0, flagCounter = 0;
		while (end < s.length()) {
			char c1 = s.charAt(end);
			if (map.get(c1) > 0)
				flagCounter++; // 如果没有map的初始化,map.get(c1) ==
								// null，此行NullPointerException
			map.put(c1, map.get(c1) + 1);
			end++; // end++，resMaxLen = end-start(没有加一)
			while (flagCounter > 0) {
				char c2 = s.charAt(start);
				if (map.get(c2) > 1)
					flagCounter--;
				map.put(c2, map.get(c2) - 1);
				start++;
			}
			// update maximum after the inner while loop to guarantee that the
			// substring is valid.
			resMaxLen = Math.max(resMaxLen, end - start);
		}
		return resMaxLen;
	}
}
