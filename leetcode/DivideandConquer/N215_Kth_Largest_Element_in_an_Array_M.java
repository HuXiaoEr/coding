package leetcode.DivideandConquer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 �� k �� array's length.
 */

//YES
//YES TWO
//heap
public class N215_Kth_Largest_Element_in_an_Array_M {
	
	//my code
	//O(N lg K) running time + O(K) memory
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  //��
        for(int i = 0; i < nums.length; i++){
        	if(i < k) minHeap.add(nums[i]);
        	else if(nums[i] > minHeap.peek()){
        		minHeap.poll();
        		minHeap.add(nums[i]);
        	}
        }
    	return minHeap.peek();
    }
    
    //not mine
    //O(N lg N) running time + O(1) memory
    public int findKthLargest2(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }
    
    //O(N) best case / O(N^2) worst case running time + O(1) memory
    public int findKthLargest3(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private boolean less(int v, int w) {
        return v < w;
    }
}
