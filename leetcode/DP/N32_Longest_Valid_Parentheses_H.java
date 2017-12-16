package leetcode.DP;

import java.util.Stack;

/**
 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

 */

//NO
//Attention : substring
//dp
public class N32_Longest_Valid_Parentheses_H {

        public int longestValidParentheses3(String s) {
            Stack<Integer> stack = new Stack<Integer>();
            int max=0;
            int left = -1;
            for(int j=0;j<s.length();j++){
                if(s.charAt(j)=='(') stack.push(j);
                else {
                    if (stack.isEmpty()) left=j;
                    else{
                        stack.pop();
                        if(stack.isEmpty()) max=Math.max(max,j-left);
                        else max=Math.max(max,j-stack.peek());
                    }
                }
            }
            return max;
        }

    //dp
    // longest[] represents the longest length of valid parentheses which is end at i
    //longest[i] = 0  If s[i] is '('
    //           = longest[i-2] + 2  if s[i-1] is '('
    //           = longest[i-1] + 2 + longest[i-longest[i-1]-2]   if s[i-1] is ')' and s[i-longest[i-1]-1] == '('
    //For example, input "()(())", at i = 5, longest array is [0,2,0,0,2,0], longest[5] = longest[4] + 2 + longest[1] = 6.
    public int longestValidParentheses(String str) {
        if(str.length() <= 1) return 0;
        int curMax = 0;
        char[] s = str.toCharArray();
        int[] longest = new int[str.length()];
        for(int i=1; i < s.length; i++){
            if(s[i] == ')'){
                if(s[i-1] == '('){
                    longest[i] = (i-2) >= 0 ? (longest[i-2] + 2) : 2;
                    curMax = Math.max(longest[i],curMax);
                }
                else{ // if s[i-1] == ')', combine the previous length.
                    if(i-longest[i-1]-1 >= 0 && s[i-longest[i-1]-1] == '('){
                        longest[i] = longest[i-1] + 2 + ((i-longest[i-1]-2 >= 0)?longest[i-longest[i-1]-2]:0);
                        curMax = Math.max(longest[i],curMax);
                    }
                }
            }
            //else if s[i] == '(', skip it, because longest[i] must be 0
        }
        return curMax;
    }

    //another dp
    public int longestValidParentheses2(String s) {
        char[] S = s.toCharArray();
        int[] V = new int[S.length];
        int open = 0;
        int max = 0;
        for (int i=0; i<S.length; i++) {
            if (S[i] == '(') open++;
            if (S[i] == ')' && open > 0) {
                // matches found
                V[i] = 2+ V[i-1];
                // add matches from previous
                if(i-V[i]>0)
                    V[i] += V[i-V[i]];
                open--;
            }
            if (V[i] > max) max = V[i];
        }
        return max;
    }


}

