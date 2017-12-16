package leetcode.DP;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). 
Please reload the code definition to get the latest changes. 
 */

//NO
//NO TWO
public class N139_Word_Break_M {
	
	//Not Mine
	//DP
	//breakable[i] stores whether the substring s.substring(0,i) is breakable or not. 
	//breakable[i+1] |= breakable[j]&&dict.contains(s.substring(j,i+1)), j>=0 && j<i+1
	public boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        
        //o(k*n) time  k = S.length()
        /* First DP
        for(int i = 1; i <= s.length(); i++){
            for(String str: dict){
                if(str.length() <= i){
                    if(f[i - str.length()]){
                        if(s.substring(i-str.length(), i).equals(str)){
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }*/
        
        //o(k*k) time  k = S.length()
        //Second DP
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }
	
	//??TWO BFS
/*
People have posted elegant solutions using DP. 
The solution I post below using BFS is no better than those. Just to share some new thoughts.

We can use a graph to represent the possible solutions. 
The vertices of the graph are simply the positions of the first characters of the words 
and each edge actually represents a word. 
For example, the input string is "nightmare", there are two ways to break it, "night mare" and "nightmare". 
The graph would be

0-->5-->9

|__ __ _^

The question is simply to check if there is a path from 0 to 9. 
The most efficient way is traversing the graph using BFS with the help of a queue and a hash set. 
The hash set is used to keep track of the visited nodes to avoid repeating the same work.

For this problem, the time complexity is O(n^2) and space complexity is O(n), the same with DP. 
This idea can be used to solve the problem word break II. 
We can simple construct the graph using BFS, save it into a map and then find all the paths using DFS.
 */
	//BFS DFS method ??
	public boolean wordBreak2(String s, Set<String> dict) {
	    if (dict.contains(s)) return true;
	    Queue<Integer> queue = new LinkedList<Integer>();
	    queue.offer(0);
	    // use a set to record checked index to avoid repeated work.
	    // This is the key to reduce the running time to O(N^2).
	    Set<Integer> visited = new HashSet<Integer>();
	    visited.add(0);
	    while (!queue.isEmpty()) {
	        int curIdx = queue.poll();
	        for (int i = curIdx+1; i <= s.length(); i++) {
	            if (visited.contains(i)) continue;
	            if (dict.contains(s.substring(curIdx, i))) {
	                if (i == s.length()) return true;
	                queue.offer(i);
	                visited.add(i);
	            }
	        }
	    }
	    return false;
	}
	
}
