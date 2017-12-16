package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
add spaces in s to construct a sentence where each word is a valid dictionary word. 
You may assume the dictionary does not contain duplicate words.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). 
Please reload the code definition to get the latest changes.
 */

//NO
public class N140_Word_Break_II_H {
	
	//My code ->  29 / 37 test cases passed  TLE
    public List<String> wordBreak(String s, List<String> wordDict) {
    	List<String> res = new ArrayList<>();
    	backtrack(0, s, "", wordDict, res);
    	return res;
    }
	private void backtrack(int st, String s, String str, List<String> wordDict, List<String> res) {
		if(st >= s.length()){
			res.add(str.substring(0, str.length()-1));
			return;
		}
		for(int len = 1; len <= s.length()-st; len++){
			String temp = s.substring(st, st+len);
			if(!wordDict.contains(temp)) continue;
			backtrack(st+len, s, str+temp+" ", wordDict, res);
		}
	}
	
	//Using DFS directly will lead to TLE, 
	//so I just used HashMap to save the previous results to prune duplicated branches, as the following:
	public List<String> wordBreak(String s, Set<String> wordDict) {
	    return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
	}       
	// DFS function returns an array including all substrings derived from s.
	List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
	    if (map.containsKey(s)) 
	        return map.get(s);
	        
	    LinkedList<String>res = new LinkedList<String>();     
	    if (s.length() == 0) {
	        res.add("");
	        return res;
	    }               
	    for (String word : wordDict) {
	        if (s.startsWith(word)) {
	            List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
	            for (String sub : sublist) 
	                res.add(word + (sub.isEmpty() ? "" : " ") + sub);               
	        }
	    }       
	    map.put(s, res);
	    return res;
	}
	
	//another idea considering the wordDict may be large
	HashMap<String,List<String>> map = new HashMap<String,List<String>>();
    public List<String> wordBreak3(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) {
            return res;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++) {
            String t = s.substring(i);
            if(wordDict.contains(t)) {
                List<String> temp = wordBreak(s.substring(0 , i) , wordDict);
                if(temp.size() != 0) {
                    for(int j = 0 ; j < temp.size() ; j++) {
                        res.add(temp.get(j) + " " + t);
                    }
                }
            }
        }
        map.put(s , res);
        return res;
    }
}
