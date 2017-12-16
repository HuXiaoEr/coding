package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

//YES
public class N77_Combinations_M {
	
	//mine code
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>(); 
		if(n < 1 || k > n || k <= 0) return res;  // k < 0 ??  n < 1 ??  n > k ??
		backtrack(1, n, k, new ArrayList<Integer>(), res);
		return res;
    }
	private void backtrack(int start, int n, int k, ArrayList<Integer> list, List<List<Integer>> res) {
		if(k == 0){
			res.add(new ArrayList<Integer>(list));
			return;
		}
		//for(int i = start; i <= n; i++){
		for (int i = start, max = n - k + 1; i <= max; i++) { // a small trick to end search early and speed up greatly бя
			list.add(i);
			backtrack(i+1, n, k-1, list, res);
			list.remove(list.size()-1);
		}
	}
	
	//another idea
	//faster than mine  why??
	/*
	 * C(n,k)=C(n-1,k-1)+C(n-1,k)
	 * Situation one, number n is selected, so we only need to select k-1 from n-1 next.
	 * Situation two, number n is not selected, and the rest job is selecting k from n-1.
	 */
	public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k > n || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        result = combine(n - 1, k - 1);
        for (List<Integer> list : result) {
            list.add(n);
        }
        result.addAll(combine(n - 1, k));
        return result;
    }
}
