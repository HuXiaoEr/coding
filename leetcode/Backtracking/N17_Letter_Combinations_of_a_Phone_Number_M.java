package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

1 
2 abc
3 def
4 ghi
5 jkl
6 mno
7 pqs
8 tuv
9 wxyz

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
 */

//YES
public class N17_Letter_Combinations_of_a_Phone_Number_M {


	// my yes code
	//backtracking
    public List<String> letterCombinations(String digits) {
    	List<String> res = new ArrayList<String>();
    	if(digits == null || digits.length() == 0) return res;
    	String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    	com(digits, 0, "", map, res);
    	return res;
    }
	private void com(String digits, int st, String str, String[] map, List<String> res) {
		if(st >= digits.length()){
			res.add(str);
			return;
		}
		for(int i = 0; i < map[digits.charAt(st)-'0'].length(); i++){
			com(digits, st+1, str+map[digits.charAt(st)-'0'].charAt(i), map, res);
		}
	}
	
	// better
	//BFS solution (using a queue)
	public List<String> letterCombinations2(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			while (ans.peek().length() == i) {  //peek() : 此列表的头，如果此列表为空，则返回 null 
				String t = ans.remove();  // NoSuchElementException - 如果此列表为空
				for (char s : mapping[x].toCharArray())
					ans.add(t + s);
			}
		}
		return ans;
	}
}
