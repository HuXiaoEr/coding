package leetcode.String;

import java.util.HashMap;

/**
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 */


public class N13_Roman_to_Integer_E {

	// my code
	public int romanToInt(String s) {
    	if(s == null || s.length() == 0) return 0;
        int res = 0;
        HashMap<Character, Integer> hash = new HashMap<>();
        hash.put('I', 1);
        hash.put('V', 5);
        hash.put('X', 10);
        hash.put('L', 50);
        hash.put('C', 100);
        hash.put('D', 500);
        hash.put('M', 1000);
        int temp = hash.get(s.charAt(s.length()-1));
        boolean flag = false;
        int pre = -1;
        for(int i = s.length()-2; i >= 0; i--){
        	if(hash.get(s.charAt(i)) >= hash.get(s.charAt(i+1))){
        		res += temp;
        		temp = hash.get(s.charAt(i));
        	}
        	else{
        		temp -= hash.get(s.charAt(i));
        	}
        }
        res += temp;
    	return res;
    }
	
	// not my code
	// better
	public static int romanToInt2(String s) {
		if (s == null || s.length() == 0) return -1;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int len = s.length(), result = map.get(s.charAt(len - 1));
		for (int i = len - 2; i >= 0; i--) {
			if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
				result += map.get(s.charAt(i));
			else
				result -= map.get(s.charAt(i));
		}
		return result;
	}
	
}
