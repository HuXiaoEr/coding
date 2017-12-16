package leetcode.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

//YES
public class N20_Valid_Parentheses_E {
	
	//my code
	public boolean isValid(String s) {
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put('(', ')');
        mapping.put('{', '}');
        mapping.put('[', ']');
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
        	char c = s.charAt(i);
        	if(c == '(' || c == '{' || c == '[') stack.push(c);
        	else if(!stack.isEmpty() && mapping.get(stack.pop()) == c) continue;
        	else return false;
        }
    	return stack.isEmpty();  // attention : stack.isEmpty()
    }
	
	//excellent
	public boolean isValid2(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}
	
	//without ( ) [] {} in code
	public boolean isValid3(String s) {
	    char [] arr = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for(char ch : arr){
			if(stack.isEmpty()) stack.push(ch);
			else{
				char top = stack.peek();
				if(ch - top == 1 || ch - top == 2) stack.pop();
				else stack.push(ch);
			}
		}
		return stack.isEmpty();
	}
}
