package leetcode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a string t, check if s is subsequence of t.

 You may assume that there is only lower case English letters in both s and t.
 t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters
 without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

 Example 1:
 s = "abc", t = "ahbgdc"

 Return true.

 Example 2:
 s = "axc", t = "ahbgdc"

 Return false.

 Follow up:
 If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence.
 In this scenario, how would you change your code?
 */

//NO
public class N392_Is_Subsequence_M {

    //Mine MLE
    public boolean isSubsequence(String s, String t) {
        if(s == null) return true;
        if(t == null) return false;
        return s.length() == lcs(s, t);
    }
    private int lcs(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= t.length(); j++){
                if(s.charAt(i-1) == t.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[s.length()][t.length()];
    }
    //Not right
    //wrong
    public boolean isSubsequence2(String s, String t) {
        if(s == null) return true;
        if(t == null) return false;
        boolean[][] dp = new boolean[s.length()+1][t.length()+1];
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= t.length(); j++){
                if(s.charAt(i-1) == t.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = dp[i][j-1] || dp[i-1][j];
            }
        }
        return dp[s.length()][t.length()];
    }

    //straight forward solution
    public boolean isSubsequence3(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }
    //much faster than 3
    //better
    /**
     * I checked the origin code of func "indexOf" and "charAt". These two solution both traversed the char of String one by one to search the first occurrence specific char.
     The difference is that indexOf only call once function then traversed in "String.value[]" arr, but we used multiple calling function "charAt" to get the value in "String.value[]" arr.
     The time expense of calling function made the difference.
     */
    public boolean isSubsequence4(String s, String t)
    {
        if(t.length() < s.length()) return false;
        int prev = 0;
        for(int i = 0; i < s.length();i++)
        {
            char tempChar = s.charAt(i);
            prev = t.indexOf(tempChar,prev);
            if(prev == -1) return false;
            prev++;
        }

        return true;
    }

    //for the follow up
    /**
     * Follow-up
     * If we check each sk in this way, then it would be O(kn) time where k is the number of s and t is the length of t.
     * This is inefficient.
     * Since there is a lot of s, it would be reasonable to preprocess t to generate something that is easy to search for if a character of s is in t.
     * Sounds like a HashMap, which is super suitable for search for existing stuff.
     */
    public boolean isSubsequence5(String s, String t) {
        if (s == null || t == null) return false;
        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>(); //<character, index>
        //preprocess t
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<Integer>());
            }
            map.get(curr).add(i);
        }

        int prev = -1;  //index of previous character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == null)  {
                return false;
            } else {
                List<Integer> list = map.get(c);
                prev = binarySearch(prev, list, 0, list.size() - 1);
                if (prev == -1) {
                    return false;
                }
                prev++;
            }
        }
        return true;
    }
    //int index = Collections.binarySearch(list, prev)
    //list.get(index)
    private int binarySearch(int index, List<Integer> list, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < index) {
                start = mid + 1;
            } else {              // >= ☆
                end = mid - 1;
            }
        }
        return start == list.size() ? -1 : list.get(start);
    }
}
