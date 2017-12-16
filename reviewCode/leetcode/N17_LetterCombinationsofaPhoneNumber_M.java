package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.List;

//YES
public class N17_LetterCombinationsofaPhoneNumber_M {
	public List<String> letterCombinations(String digits) {
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
}
