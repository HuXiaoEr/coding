package leetcode.Array;

import java.util.LinkedList;

/**
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ¡Ü k ¡Ü input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

 */

// NO TWO
public class N239_Sliding_Window_Maximum_H {
	
	
	public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int[] result = new int[n - k + 1];
        
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peek()];
            }
        }
        return result;
    }
	
	// ??
	// https://discuss.leetcode.com/topic/26480/o-n-solution-in-java-with-two-simple-pass-in-the-array
	public static int[] slidingWindowMax(final int[] in, final int w) {
	    final int[] max_left = new int[in.length];
	    final int[] max_right = new int[in.length];

	    max_left[0] = in[0];
	    max_right[in.length - 1] = in[in.length - 1];

	    for (int i = 1; i < in.length; i++) {
	        max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

	        final int j = in.length - i - 1;
	        max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
	    }

	    final int[] sliding_max = new int[in.length - w + 1];
	    for (int i = 0, j = 0; i + w <= in.length; i++) {
	        sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
	    }

	    return sliding_max;
	}
}
