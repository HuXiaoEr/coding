package reviewCode.leetcode;

import java.util.Stack;

/**
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

// 数字是超过1位，eg : 100[leet]
// 怎么提取数字，eg : ac100[df]

// YES TWO HALF
public class N394_Decode_String_M {
    public static String decodeString(String s) {
    	int start = -1;
    	int end = -1;
    	int flag = 0;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 0; i < s.length(); i++){
        	if(Character.isLetter(s.charAt(i)) && start == -1) sb.append(s.charAt(i));
        	else if(Character.isDigit(s.charAt(i)) && start == -1) count = 10 * count + s.charAt(i)-'0';
        	else if(s.charAt(i) == '['){
        		if(flag == 0) start = i;
        		flag++;
        	}
        	else if(s.charAt(i) == ']'){
        		flag--;
        		if(flag == 0){
        			end = i;
        			break;
        		}
        	}
        }
        System.out.println( start +" " + end);
        if(start == -1 && end == -1) return s;
        String str = decodeString(s.substring(start+1, end));
        for(int i = 0; i < count; i++){
        	sb.append(str);
        }
        if(end != s.length()-1) sb.append(decodeString(s.substring(end+1)));
        System.out.println(sb.length());
    	return sb.toString();
    }
    
    // better with stack
    public static String decodeString2(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();
    }
    public static void main(String[] args) {
		System.out.println(decodeString("100[leetcode]"));
	}
}
