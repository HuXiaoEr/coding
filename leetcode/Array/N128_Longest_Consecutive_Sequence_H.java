package leetcode.Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.


 */


public class N128_Longest_Consecutive_Sequence_H {
	
	
	/*
	 * We will use HashMap. 
	 * The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. 
	 * For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.
	 * 
	 * Whenever a new element n is inserted into the map, do two things:
	 * (1)See if n - 1 and n + 1 exist in the map, 
	 *    and if so, it means there is an existing sequence next to n. 
	 *    Variables left and right will be the length of those two sequences, 
	 *    while 0 means there is no sequence and n will be the boundary point later. 
	 *    Store (left + right + 1) as the associated value to key n into the map.
	 * (2)Use left and right to locate the other end of the sequences to the left and right of n respectively, 
	 *    and replace the value with the new length.
	 * Everything inside the for loop is O(1) so the total time is O(n). 
	 * Please comment if you see something wrong. Thanks.
	 * 
	 */
	public int longestConsecutive(int[] num) {
	    int res = 0;
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int n : num) {
	        if (!map.containsKey(n)) {
	            int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
	            int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
	            // sum: length of the sequence n is in
	            int sum = left + right + 1;
	            map.put(n, sum);
	            // keep track of the max length 
	            res = Math.max(res, sum);
	            // extend the length to the boundary(s)
	            // of the sequence
	            // will do nothing if n has no neighbors
	            map.put(n - left, sum);
	            map.put(n + right, sum);
	        }
	        else {
	            // duplicates
	            continue;
	        }
	    }
	    return res;
	}
	
	
	/*
	 * First turn the input into a set of numbers. 
	 * That takes O(n) and then we can ask in O(1) whether we have a certain number.
	 * 
	 * Then go through the numbers. 
	 * If the number x is the start of a streak (i.e., x-1 is not in the set), 
	 * then test y = x+1, x+2, x+3, ... and stop at the first number y not in the set. 
	 * The length of the streak is then simply y-x and we update our global best with that. 
	 * Since we check each streak only once, this is overall O(n). 
	 */
	public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        int best = 0;
        for(int n : set) {
            if(!set.contains(n - 1)) {  // only check for one direction
                int m = n + 1;
                while(set.contains(m)) {
                    m++;
                }
                best = Math.max(best, m - n);
            }
        }
        return best;
    }
	
	
	//Using a set to collect all elements that hasn't been visited. 
	//search element will be O(1) and eliminates visiting element again.
	public int longestConsecutive3(int[] nums) {
	    if(nums == null || nums.length == 0) return 0;
	    
	    Set<Integer> set = new HashSet<Integer>();
	    
	    for(int num: nums) set.add(num);
	    int max = 1;
	    for(int num: nums) {
	        if(set.remove(num)) {//num hasn't been visited
	            int val = num;
	            int sum = 1;
	            while(set.remove(val-1)) val--;
	            sum += num - val;
	            
	            val = num;
	            while(set.remove(val+1)) val++;
	            sum += val - num;
	            
	            max = Math.max(max, sum);
	        }
	    }
	    return max;
	}


}
