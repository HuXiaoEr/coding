package JzOffer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

// 均 over
public class N30_最小的k个数 {
	/**
	 * 法一：
	 * 时间复杂度O(n)
	 * 会修改输入的数组
	 */
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(input == null || input.length == 0 || k > input.length || k <= 0) return res;
        int index = partition(input, 0, input.length-1);
        while(index != k-1)
        {
        	if(index < k-1) index = partition(input, index+1, input.length-1);
        	else index = partition(input, 0, index-1);
        }
        for(int i = 0; i < k; i++)
        {
        	res.add(input[i]);
        }
        return res;
    }

	private int partition(int[] input, int lo, int hi) {
		int small = lo;
		for(int i = lo; i <= hi; i++)
		{
			if(input[i] < input[lo])
			{
				small++;
				if(small != i) exch(input, small, i);
			}
		}
		exch(input, small, lo);
		return small;
	}

	private void exch(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	// over
	/**
	 * 法二：
	 * 时间复杂度O(nlog(k))
	 * 适合处理海量数据
	 * 不会修改输入的数组
	 */
	public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();
        if(input == null || input.length == 0 || k > input.length || k <= 0) return res;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
        	public int compare(Integer o1, Integer o2)
        	{
        		return o2 - o1;
        	}
        }
        		);
        for(int i = 0; i < input.length; i++)
        {
        	if(maxHeap.size() < k) maxHeap.add(input[i]);
        	else if(maxHeap.peek() > input[i])
        	{
        		maxHeap.poll();
        		maxHeap.add(input[i]);
        	}
        }
        for(int i : maxHeap)
        {
        	res.add(i);
        }
        /**
        Iterator<Integer> it = maxHeap.iterator();
        while(it.hasNext()) res.add(it.next());
        */
        return res;
    }
}
