package JzOffer;

import java.util.LinkedList;
import java.util.Stack;

public class N7_两个栈实现队列_两个队列实现栈 {
	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    /*
     * better
     */

    public void add(int node) 
    {
    	stack1.push(node);
    }
    public int poll() 
    {
    	if(stack1.empty()&&stack2.empty())
    	{
    		throw new RuntimeException("Queue is empty!");
    	}
    	if(stack2.empty())
    	{
    		while(!stack1.empty())
    		{
    			stack2.push(stack1.pop());
    		}
    	}
    	return stack2.pop();
    }
    public int peek1() 
    {
    	if(stack1.empty()&&stack2.empty())
    	{
    		throw new RuntimeException("Queue is empty!");
    	}
    	if(stack2.empty())
    	{
    		while(!stack1.empty())
    		{
    			stack2.push(stack1.pop());
    		}
    	}
    	return stack2.peek();
    }
    
    // 两个队列实现栈
    LinkedList<Integer> queue1 = new LinkedList<Integer>();
    LinkedList<Integer> queue2 = new LinkedList<Integer>();
    
    public void push(int node){
    	queue1.add(node);
    }
    public int pop(){
    	if(queue1.isEmpty() && queue2.isEmpty()){
    		throw new RuntimeException("Stack is empty");
    	}
    	if(!queue1.isEmpty()){
    		move(queue1, queue2);
    		return queue1.poll();
    	}
    	else{
    		move(queue2, queue1);
    		return queue2.poll();
    	}
    }
    public int peek(){
    	if(queue1.isEmpty() && queue2.isEmpty()){
    		throw new RuntimeException("Stack is empty");
    	}
    	int top = 0;
    	if(!queue1.isEmpty()){
    		move(queue1, queue2);
    		top = queue1.peek();
    		queue2.add(queue1.poll());
    	}
    	else{
    		move(queue2, queue1);
    		top = queue2.peek();
    		queue1.add(queue2.poll());
    	}
    	return top;
    }
	private void move(LinkedList<Integer> from, LinkedList<Integer> to) {
		while(from.size() > 1) to.add(from.poll());
	}
    
    public static void main(String[] args) {
    	N7_两个栈实现队列_两个队列实现栈 s = new N7_两个栈实现队列_两个队列实现栈();
    	s.add(1);
    	s.add(2);
    	s.add(3);
    	s.add(3);
    	s.add(4);
    	System.out.println(s.poll());
    	System.out.println(s.poll());
    	System.out.println(s.poll());
    	System.out.println(s.poll());
    	System.out.println(s.poll());
	}
    
    
    
    
    
    
    
    
    
}
