package leetcode.Array;

import java.util.HashSet;

/**
A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of array A is an integer within the range [0, N-1].
 */

// YES TWO
// Easy
public class N565_Array_Nesting_M {
    public int arrayNesting(int[] nums) {
    	int res = 0;
        int size = 0;
        boolean[] visited = new boolean[nums.length];
        HashSet<Integer> hash = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
        	if(!visited[i]){
        		int idx = i;
        		while(hash.add(nums[idx])){
        			visited[idx] = true;
        			size++;
        			idx = nums[idx];
        		}
        		res = Math.max(res, size);
        		hash.clear();
        		size = 0;
        	}
        }
        return res;
    }
    
    // shorter
    public int arrayNesting2(int[] a) {
        int maxsize = 0;
        for (int i = 0; i < a.length; i++) {
            int size = 0;
            for (int k = i; a[k] >= 0; size++) {
                int ak = a[k];
                a[k] = -1; // mark a[k] as visited;
                k = ak;
            }
            maxsize = Integer.max(maxsize, size);
        }
        return maxsize;
    }
}
