package leetcode.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

//NO
public class N15_3Sum_M {
	

	
	// TLE : 311 / 313 test cases passed.
    public List<List<Integer>> threeSum2(int[] num) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	List<Integer> list = new ArrayList<Integer>();
    	Arrays.sort(num);
    	backtrack(num, 0, 0, list, res);
    	return res;
    }
    
	private void backtrack(int[] num, int st, int sum, List<Integer> list, List<List<Integer>> res) {
		if(st > num.length) return;		// attention : >  not >= 
		if(list.size() == 3){
			if(sum == 0) res.add(new ArrayList<Integer>(list));
			return;
		}
		for(int i = st; i < num.length; i++){
			if(i > st && num[i] == num[i-1]) continue;
			if(sum + num[i] <= 0){
				list.add(num[i]);
		    	backtrack(num, i+1, sum+num[i], list, res);
		    	list.remove(list.size()-1);
			}
			else break;
		}
	}
	
	// my code : 91 ms  beats 31.5%
    public List<List<Integer>> threeSum(int[] num) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	List<Integer> list = null;
    	Arrays.sort(num);
    	for(int i = 0; i <= num.length-3; i++){
    		if(i > 0 && num[i] == num[i-1]) continue;
    		int val = 0 - num[i];
    		int lo = i + 1;
    		int hi = num.length-1;
    		int sum = 0;
    		while(lo < hi){
    			if(lo > i+1 && num[lo] == num[lo-1]){
    				lo++;
    				continue;
    			}
    			if(hi < num.length-1 && num[hi] == num[hi+1]){
    				hi--;
    				continue;
    			}
    			sum = num[lo] + num[hi];
    			if(sum > val) hi--;
    			else if(sum < val) lo++;
    			else {
    				list = new ArrayList<Integer>();
    				list.add(num[i]);
    				list.add(num[lo]);
    				list.add(num[hi]);
    				res.add(list);
    				hi--;
    			}
    		}
    	}
    	return res;
    }
    
    // two pointers
    //best
    //o(n*n)
    public List<List<Integer>> threeSum22(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>(); 
        for (int i = 0; i < num.length-2; i++) {
        	//skip equal elements to avoid duplicates in the answer without making a set or smth like that.
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            	//bi-directional 2Sum sweep of the remaining part of the array
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        lo++; hi--;
                        //skip equal elements to avoid duplicates in the answer without making a set or smth like that
                        while (lo < hi && num[lo] == num[lo-1]) lo++;
                        while (lo < hi && num[hi] == num[hi+1]) hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
               }
            }
        }
        return res;
    }
}
