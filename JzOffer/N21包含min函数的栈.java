package JzOffer;

import java.util.Deque;
import java.util.LinkedList;

// over
public class N21°üº¬minº¯ÊýµÄÕ» {
	private Deque<Integer> stack = new LinkedList<Integer>();
	private Deque<Integer> stackMin = new LinkedList<Integer>();
	private int min;  //not necessary
	public void push(int node) {
        if(stack.isEmpty() || node < min) min = node;
        stack.push(node);
        stackMin.push(min);
    }
    
    public void pop() {
        if(!stack.isEmpty())
        {
        	stack.pop();
        	stackMin.pop();
        	min = stackMin.peek();
        }
        else throw new RuntimeException();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
    	return stackMin.peek();
    }
}
