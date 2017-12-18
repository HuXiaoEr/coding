package JzOffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// δover
public class N65_�������ڵ����ֵ {
	
	// best
    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if(num == null || num.length == 0 || size > num.length || size < 1) return res;
    	Deque<Integer> queue = new LinkedList<Integer>();
    	for(int i = 0; i < num.length; i++){
    		int begin = i - size + 1;
    		if(queue.isEmpty()) queue.add(i);
    		else{
    			if(begin > queue.peekFirst()) queue.pollFirst();
        		while(!queue.isEmpty() && num[i] > num[queue.peekLast()]) queue.pollLast();
        		queue.add(i);
    		}
    		if(begin >= 0){
    			res.add(num[queue.peekFirst()]);
    		}
    	}
    	return res;
    }
	
	//
	public static ArrayList<Integer> maxInWindows2(int [] num, int size)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
		if(num == null || size <= 0 || size > num.length) return res;  //important
		
        Deque<Integer> deque = new LinkedList<Integer>();
        for(int i = 0; i < size; i++)
        {
        	if(deque.isEmpty() || num[i] <= num[deque.getLast()]) deque.add(i); // <= �п�
        	else 
        	{
        		while(!deque.isEmpty() && num[i] > num[deque.getLast()]) deque.removeLast(); // �п�
        		deque.add(i);
        	}
        }
        for(int i = size; i < num.length; i++)
        {
        	res.add(num[deque.peek()]);
        	if(i - deque.peek() >= size) deque.poll(); // >=
        	while(!deque.isEmpty() && num[i] > num[deque.getLast()]) deque.removeLast(); // �п�
        	deque.add(i);
        }
        res.add(num[deque.peek()]); // ���
		return res;
    }
	public static void main(String[] args) {
		ArrayList<Integer> res = maxInWindows(new int[]{2,3,4,2,6,2,5,1}, 3);
	}
}
