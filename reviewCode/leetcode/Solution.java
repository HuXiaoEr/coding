package reviewCode.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
	
    public boolean isValid(String s) {
    	
    	Deque<Character> stack = new LinkedList<Character>();
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == '(') stack.push(')');
    		else if(s.charAt(i) == '{') stack.push('}');
    		else if(s.charAt(i) == '[') stack.push(']');
    		else if(!stack.isEmpty() && s.charAt(i) == stack.peek()){
    			stack.pop();
    		}
    		else return false;
    	}
    	return stack.isEmpty();
    }
	public static void main(String[] args) {
		System.out.println(new Solution().isValid("[]"));
		//LinkedList<String> ans = new LinkedList<String>();
		//System.out.println(ans.remove());
	}
	
	

}


