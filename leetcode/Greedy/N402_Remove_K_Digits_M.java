package leetcode.Greedy;

import java.util.Deque;
import java.util.LinkedList;

/**
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ¡Ý k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */

//NO
public class N402_Remove_K_Digits_M {

	//NO  mine code
	//  22 / 33 test cases passed
	//wrong answer when "5337"  expected->"33"   output->"57"
    public String removeKdigits(String num, int k) {
        char[] str = num.toCharArray();
        Deque<Character> res = new LinkedList<>();
        for(int i = 0; i < num.length(); i++){
        	if(k > 0){
        		if(str[i] == '1') res.push(str[i]);
            	else if(str[i] == '0'){
            		while(!res.isEmpty() && k > 0)
            		{
            			res.pop();
            			k--;
            		}
            	}
            	else k--;
        	}
        	else res.push(str[i]);
        }
        while(!res.isEmpty() && k > 0) res.pop();
        if(res.isEmpty()) return "0";      
        String res1 = "";
        while(!res.isEmpty()) res1 += res.removeLast();
    	return res1;
    }
    
    
    //Not Mine
    public String removeKdigits2(String num, int k) {
        int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while (top > 0 && stk[top-1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stk[top++] = c;
        }
        // find the index of first non-zero digit
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits? "0": new String(stk, idx, digits - idx);
    }
    
    
    
    public static void main(String[] args) {
    	char[] str = new char[2];
    	str[0] = '1';
    	System.out.println(new String(str));
	}
}
