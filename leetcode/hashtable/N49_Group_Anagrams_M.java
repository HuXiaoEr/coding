package leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
 */

//YES
public class N49_Group_Anagrams_M {
	
	//hash table
	//31ms beats 43.85%
    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> res = new ArrayList<List<String>>();
    	if(strs == null) return res;
        HashMap<String, List<String>> hashtable = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
        	char[] arri = strs[i].toCharArray();
        	Arrays.sort(arri);
        	String temp = new String(arri);
        	if(!hashtable.containsKey(temp)) hashtable.put(temp, new ArrayList<String>());
        	hashtable.get(temp).add(strs[i]);
        }
        
         //better 25ms beats 92%
         return new ArrayList<List<String>>(hashtable.values());
        
        //for(String key : hashtable.keySet()) res.add(hashtable.get(key));
        //return res;
    }
    
    
    //Not mine
    //beat 100%!!! use prime number
	public static List<List<String>> groupAnagrams2(String[] strs) {
		int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
				103 };// ×î¶à10609¸öz
		List<List<String>> res = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (String s : strs) {
			int key = 1;
			for (char c : s.toCharArray()) {
				key *= prime[c - 'a'];
			}
			List<String> t;
			if (map.containsKey(key)) {
				t = res.get(map.get(key));
			} else {
				t = new ArrayList<>();
				res.add(t);
				map.put(key, res.size() - 1);
			}
			t.add(s);
		}
		return res;
	}
    
    
}
