package leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, 
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
 */

// YES TWO HALF
public class N581_Shortest_Unsorted_Continuous_Subarray_E {
	
	// bad performance : beats 0.81%
    public static int findUnsortedSubarray(int[] nums) {
    	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    	int min = -1;
    	int max = -1;
    	HashMap<Integer, List<Integer>> hash = new HashMap<>();
    	for(int i = 0; i < nums.length; i++){
    		minHeap.add(nums[i]);
    		if(!hash.containsKey(nums[i])) hash.put(nums[i], new ArrayList<Integer>());
    		hash.get(nums[i]).add(i);
    	}
    	int idx = 0;
    	for(int i = 0; i < nums.length; i++){
    		int tem = minHeap.poll();
    		if(min == -1 && !hash.get(tem).contains(idx)) min = idx;
    		if(!hash.get(tem).contains(idx)) max = idx;
    		idx++;
    	}
    	return (min == -1 && max == -1) ? 0 : max - min + 1; // 注意 不是直接返回 max - min + 1；考虑原先是有序的特殊情况
    }
    public static void main(String[] args) {
		System.out.println(findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
	}
    
    // o(n) time  o(1) space 
    // initialize end = -2 is a smart move. we can always return end - beg + 1, no need to check if the array is already sorted.
    /*
     * I use the variables beg and end to keep track of minimum subarray A[beg...end] which must be sorted for the entire array A to be sorted. 
     * If end < beg < 0 at the end of the for loop, then the array is already fully sorted.
     */
    public int findUnsortedSubarray3(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
          max = Math.max(max, A[i]);
          min = Math.min(min, A[n-1-i]);
          if (A[i] < max) end = i;
          if (A[n-1-i] > min) beg = n-1-i; 
        }
        return end - beg + 1;
    }
    
    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int[] temp = nums.clone();
        Arrays.sort(temp);
        
        int start = 0;
        while (start < n  && nums[start] == temp[start]) start++;
        
        int end = n - 1;
        while (end > start  && nums[end] == temp[end]) end--;
        
        return end - start + 1;
    }
}
