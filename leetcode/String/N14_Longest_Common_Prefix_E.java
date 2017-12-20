package leetcode.String;

import java.util.Arrays;

public class N14_Longest_Common_Prefix_E {
    public String longestCommonPrefix(String[] strs) {
    	if(strs == null) return null;
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        StringBuilder res = new StringBuilder();
        for(int j = 0;;j++){
        	for(int i = 0; i < strs.length-1; i++){
        		if(j >= strs[i].length() || j >= strs[i+1].length()) return res.toString();
        		else if(strs[i].charAt(j) == strs[i+1].charAt(j)) continue;
        		else return res.toString();
        	}
        	res.append(strs[0].charAt(j));
        }
    }
    
    // not my code
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null) return null;
        if (strs.length == 0) return "";
        
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last  = strs[strs.length - 1].toCharArray();
         
        int i = 0, len = Math.min(first.length, last.length);
        while (i < len && first[i] == last[i]) i++;
        return strs[0].substring(0, i);
    }
    
    // not my code
    public String longestCommonPrefix3(String[] strs) {
        int len = strs.length;
		if (len == 0)
			return "";
		int minlen = 0x7fffffff;
		for (int i = 0; i < len; ++i) 
			minlen = Math.min(minlen, strs[i].length());
		for (int j = 0; j < minlen; ++j) 
			for (int i = 1; i < len; ++i) 
				if (strs[0].charAt(j) != strs[i].charAt(j)) 
					return strs[0].substring(0, j);
		return strs[0].substring(0, minlen);
    }
}
