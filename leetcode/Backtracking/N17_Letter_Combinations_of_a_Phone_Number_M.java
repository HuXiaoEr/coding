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

	//my code
	public List<String> letterCombinations(String digits) {
    	List<String> res = new ArrayList<>();
    	if(digits.length() == 0 || digits.contains("1") || digits.contains("0")) return res;
    	List<String>[] mapping = (ArrayList<String>[])new ArrayList[10];
    	mapping[2] = new ArrayList<String>(); //★
    	mapping[3] = new ArrayList<String>(); //★
    	mapping[4] = new ArrayList<String>(); //★
    	mapping[5] = new ArrayList<String>(); //★
    	mapping[6] = new ArrayList<String>(); //★
    	mapping[7] = new ArrayList<String>(); //★
    	mapping[8] = new ArrayList<String>(); //★
    	mapping[9] = new ArrayList<String>(); //★
    	mapping[2].add("a");
    	mapping[2].add("b");
    	mapping[2].add("c");
    	mapping[3].add("d");
    	mapping[3].add("e");
    	mapping[3].add("f");
    	mapping[4].add("g");
    	mapping[4].add("h");
    	mapping[4].add("i");
    	mapping[5].add("j");
    	mapping[5].add("k");
    	mapping[5].add("l");
    	mapping[6].add("m");
    	mapping[6].add("n");
    	mapping[6].add("o");
    	mapping[7].add("p");
    	mapping[7].add("q");
    	mapping[7].add("r");
    	mapping[7].add("s");
    	mapping[8].add("t");
    	mapping[8].add("u");
    	mapping[8].add("v");
    	mapping[9].add("w");
    	mapping[9].add("x");
    	mapping[9].add("y");
    	mapping[9].add("z");
    	backtrack(mapping, 0, digits, "",  res);
    	return res;
    }
	private void backtrack(List<String>[] mapping, int start, String digits, String string, List<String> res) {
		if(start == digits.length()){
		    res.add(string);
		    return;
		} 
		for (int j = 0; j < mapping[Integer.parseInt(digits.charAt(start)+"")].size(); j++) {
			backtrack(mapping, start + 1, digits, string + mapping[Integer.parseInt(digits.charAt(start)+"")].get(j), res);
		}
	}
	// my yes code
	//backtracking
	public List<String> letterCombinations5(String digits) {
    	List<String> res = new ArrayList<>();
    	if(digits.length() == 0 || digits == null || digits.contains("0") || digits.contains("1")) return res;
    	String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    	char[] str = digits.toCharArray();
    	backtrack(str, res, 0, "", mapping);
    	return res;
    }
	private void backtrack(char[] str, List<String> res, int start, String ans, String[] mapping) {
		if(start == str.length){
			res.add(ans);
			return;
		}
        int idx = str[start] - '0';
		String temp = mapping[idx];
		for (int i = 0; i < temp.length(); i++) {
			backtrack(str, res, start + 1, ans + temp.charAt(i), mapping);
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
				String t = ans.remove();
				for (char s : mapping[x].toCharArray())
					ans.add(t + s);
			}
		}
		return ans;
	}
	
	//better than mine
	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
	public List<String> letterCombinations3(String digits) {
		List<String> ret = new LinkedList<String>();
		combination("", digits, 0, ret);
		return ret;
	}
	private void combination(String prefix, String digits, int offset, List<String> ret) {
		if (offset >= digits.length()) {
			ret.add(prefix);
			return;
		}
		String letters = KEYS[(digits.charAt(offset) - '0')];
		for (int i = 0; i < letters.length(); i++) {
			combination(prefix + letters.charAt(i), digits, offset + 1, ret);
		}
	}

}
