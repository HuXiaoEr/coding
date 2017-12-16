package leetcode.twoPointers;

import java.util.HashMap;

/**
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

//NO
//two pointers + hashmap
//★★ For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions. 
//A general way is to use a hashmap assisted with two pointers. 
//N3_Longest_Substring_Without_Repeating_Characters_M
public class N76_Minimum_Window_Substring_H {
	
	/*
	 *  1. Use two pointers: start and end to represent a window.
	 *  2. Move end to find a valid window.
	 *  3. When a valid window is found, move start to find a smaller window.
	 *  
	 *  To check if a window is valid, we use a map to store (char, count) for chars in t. 
	 *  And use counter for the number of chars of t to be found in s. 
	 *  The key part is m[s[end]]--;. 
	 *  We decrease count for each char in s. If it does not exist in t, the count will be negative.
	 *  
	 */
	public String minWindow(String s, String t) {
	    HashMap<Character,Integer> map = new HashMap<>();
	    for(char c : s.toCharArray()) map.put(c,0);
	    for(char c : t.toCharArray()){
	        if(map.containsKey(c)) map.put(c,map.get(c)+1);  ////map初始化
	        else return "";
	    }
	    // counter represents the number of chars of t to be found in s.
	    int start =0, end=0, minStart=0,resMinLen = Integer.MAX_VALUE, flagCounter = t.length();
	    // Move end to find a valid window.
	    while(end < s.length()){
	        char c1 = s.charAt(end);
	        // If char in s exists in t, decrease counter
	        if(map.get(c1) > 0) flagCounter--;
	        // Decrease map.get(c1). If char does not exist in t, map.get(c1) will be negative.
	        map.put(c1,map.get(c1)-1); //★★  all char in s decrease 1
	        end++;  //由于flagCounter == 0后  end++，所以resMinLen = end-start(没有加一)
	        // When we found a valid window, move start to find smaller window.
	        while(flagCounter == 0){
	        	//update minimum inside the inner while loop.
	            if(resMinLen > end-start){
	            	resMinLen = end-start;
	                minStart = start;
	            }
	            char c2 = s.charAt(start);
	            // When char exists in t, increase counter.
	            if(map.get(c2) >= 0) flagCounter++; 
	            map.put(c2, map.get(c2)+1);  //★★  all char in s increase 1
	            start++;
	        }
	    }
	    return resMinLen == Integer.MAX_VALUE ? "" : s.substring(minStart,minStart+resMinLen);
	}
}
