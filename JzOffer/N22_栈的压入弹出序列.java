package JzOffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// over
// 栈中元素均不相等 有影响吗
public class N22_栈的压入弹出序列 {
	
	// my code
	// better
	public boolean IsPopOrder(int[] a, int[] b) {
		if (a == null || b == null || a.length != b.length || a.length == 0)
			return false;
		int j = 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[j])
				s.push(a[i]);
			else {
				j++;
				while (!s.isEmpty() && s.peek() == b[j]) {
					s.pop();
					j++;
				}
			}
		}
		return s.isEmpty();
	}

	//mine, not good
	public boolean IsPopOrder2(int [] pushA,int [] popA) {
		Deque<Integer> stack = new LinkedList<Integer>();
	    int i = 1;
	    int j = 0;
	    stack.push(pushA[0]);
		while(j < popA.length)
		{
			if(stack.peek() == popA[j])
			{
				stack.pop();
				j++;
			}
			else
			{
				if(i == pushA.length) return false;
				stack.push(i);
				i++;
			}
		}
		return true;
    }
	
	
	
	// better
	public boolean IsPopOrder3(int [] pushA,int [] popA) {
		if(pushA.length == 0 || popA.length == 0)
			return false;
		Stack<Integer> s = new Stack<Integer>();
		//用于标识弹出序列的位置
		int popIndex = 0;
		for(int i = 0; i< pushA.length;i++){
			s.push(pushA[i]);
			//如果栈不为空，且栈顶元素等于弹出序列
			while(!s.empty() &&s.peek() == popA[popIndex]){
				//出栈
				s.pop();
				//弹出序列向后一位
				popIndex++;
			}
		}
		return s.empty();
	}
}
