package leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */

/**
 * Your MinStack object will be instantiated and called as such:
 * N155_Min_Stack_E obj = new N155_Min_Stack_E();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

//YES
public class N155_Min_Stack_E {

	private Deque<Integer> stack;
	private Deque<Integer> minStack;
    /** initialize your data structure here. */
    public N155_Min_Stack_E() {
        stack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        // java.lang.NullPointerException
        // be careful of -> minStack.isEmpty()
        if(minStack.isEmpty() || x < minStack.peek()) minStack.push(x);
        else minStack.push(minStack.peek());
    }
    
    public void pop() {
    	if(stack.isEmpty()) return;
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
    
// ====================== not mine ===============================================
    
    // use only one stack
    
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push2(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop2() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.peek()==min){
        	stack.pop(); 
        	min=stack.pop(); }
        else stack.pop();
    }

    public int top2() {
        return stack.peek();
    }

    public int getMin2() {
        return min;
    }
    
    // another idea
    /*
     * The idea is to store the gap between the min value and the current value;
     * The problem for my solution is the cast. I have no idea to avoid the cast. 
     * Since the possible gap between the current value and the min value could be Integer.MAX_VALUE-Integer.MIN_VALUE;
     */
    public class MinStack {
        long min;
        Stack<Long> stack;

        public MinStack(){
            stack=new Stack<>();
        }
        
        public void push(int x) {
            if (stack.isEmpty()){
                stack.push(0L);
                min=x;
            }else{
                stack.push(x-min);//Could be negative if min value needs to change
                if (x<min) min=x;
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;
            
            long pop=stack.pop();
            
            if (pop<0)  min=min-pop;//If negative, increase the min value
            
        }

        public int top() {
            long top=stack.peek();
            if (top>0){
                return (int)(top+min);
            }else{
               return (int)(min);
            }
        }

        public int getMin() {
            return (int)min;
        }
    }
    
    
    //clever than up one 
    class MinStack2
    {
         class Element
        {
            final int value;
            final int min;
            Element(final int value, final int min)
            {
                this.value = value;
                this.min = min;
            }
        }
        final Stack<Element> stack = new Stack<>();
        
        public void push(int x) {
            final int min = (stack.empty()) ? x : Math.min(stack.peek().min, x);
            stack.push(new Element(x, min));
        }

        public void pop()
        {
            stack.pop();
        }

        public int top()
        {
            return stack.peek().value;
        }

        public int getMin()
        {
            return stack.peek().min;
        }
    }
}

