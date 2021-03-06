package reviewCode.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
Given an array of n positive integers and a positive integer s, 
find the minimal length of a contiguous subarray of which the sum �� s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

//NO
public class N209_Minimum_Size_Subarray_Sum_M {
	
	// o(n) time
	// two pointers
	// the given array contains only positive integers, so the subarray sum can only increase by including more elements.
	public int minSubArrayLen(int s, int[] a) {
		if (a == null || a.length == 0)
			return 0;
		int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
		while (j < a.length) {
			sum += a[j++];
			while (sum >= s) {
				min = Math.min(min, j - i);
				sum -= a[i++];
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	
	// o(nlog(n))
	public int minSubArrayLen2(int s, int[] a) {
		int[] sums = new int[a.length+1];
        int n = a.length;
        for(int i = 1; i <= n; i++) sums[i] = a[i-1] + sums[i-1];  // important
        int minlen = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) { 
            if (sums[i] >= s) {
                int p = upper_bound(sums, 0, i, sums[i] - s);
                if (p != -1) minlen = Math.min(minlen, i - p + 1);
            }
        }
        return minlen == Integer.MAX_VALUE ? 0 : minlen;
    }
	// binary search
	//search for the first element that is greater than target
	private int upper_bound(int[] sums, int left, int right, int target) {
		int l = left;
		int r = right;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (sums[m] <= target) l = m + 1;
            else r = m;
        }
        return sums[r] > target ? r : -1;
	}
		
}
