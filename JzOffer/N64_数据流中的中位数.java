package JzOffer;

import java.util.Comparator;
import java.util.PriorityQueue;

// over
public class N64_数据流中的中位数 {
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
		public int compare(Integer o1, Integer o2)
		{
			return o2 - o1;
		}
	});
	boolean even = true; 
	public void Insert(Integer num) {
	    if(even)
	    {
	    	maxHeap.add(num);
	    	minHeap.add(maxHeap.poll());
	    }
	    else
	    {
	    	minHeap.add(num);
	    	maxHeap.add(minHeap.poll());
	    }
	    even = !even;
    }

    public Double GetMedian() {
        if(even) return (double)(maxHeap.peek() + minHeap.peek()) / 2;
        else return (double)minHeap.peek();
    }
}
