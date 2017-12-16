package reviewCode.leetcode;

import java.util.Stack;

/**
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.
 */

//NO
public class N84_Largest_Rectangle_in_Histogram_H {
	
	//stack
	//26ms beats 43%
	//http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
	public int largestRectangleArea(int[] height) {
		Stack<Integer> stack = new Stack<Integer>();
		int max_area = 0;
		for(int i=0; i<=height.length; i++){  // i <= height.length {
			int height_bound = (i == height.length) ? 0 : height[i]; // the last element
			if(stack.isEmpty() || height_bound > height[stack.peek()]) stack.push(i);
			else{
				while(!stack.isEmpty() && height_bound <= height[stack.peek()]){
					int h = height[stack.pop()];
					// at the end, the area with the height of the minimal element.
					int index_bound = stack.isEmpty() ? -1 : stack.peek();
					max_area = Math.max(max_area, h*((i-1)-index_bound));
				}
				stack.push(i); //Don't forget
			}
		}
		return max_area;
	}
	
	//divide and conquer
	//18ms beats 80%
	/*
	 * The idea is simple: for a given range of bars, the maximum area can either from left or right half of the bars, 
	 * or from the area containing the middle two bars. 
	 * For the last condition, expanding from the middle two bars to find a maximum area is O(n), 
	 * which makes a typical Divide and Conquer solution with T(n) = 2T(n/2) + O(n). 
	 * Thus the overall complexity is O(nlgn) for time and O(1) for space (or O(lgn) considering stack usage).
	 */
	public int largestRectangleAreaDQ(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        return maxArea(A, 0, A.length - 1);
    }
    int maxArea(int[] A, int l, int r) {
        if (l == r)
            return A[l];
        int m = l + (r - l) / 2;
        int area = maxArea(A, l, m);
        area = Math.max(area, maxArea(A, m + 1, r));
        area = Math.max(area, maxCombineArea(A, l, m, r));
        return area;
    }
    int maxCombineArea(int[] A, int l, int m, int r) {
        int i = m, j = m + 1;
        int area = 0, h = Math.min(A[i], A[j]);
        while (i >= l && j <= r) {
            h = Math.min(h, Math.min(A[i], A[j]));
            area = Math.max(area, (j - i + 1) * h);
            if (i == l)
                ++j;
            else if (j == r)
                --i;
            else {
                if (A[i - 1] > A[j + 1])
                    --i;
                else
                    ++j;
            }
        }
        return area;
    }
}
