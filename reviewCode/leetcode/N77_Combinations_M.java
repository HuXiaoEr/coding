package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.List;

public class N77_Combinations_M {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if(n < 1 || k > n || k <= 0) return res;
		backtrack(n, k, 1, new ArrayList<Integer>(), res);
		return res;
	}
	private void backtrack(int n, int k, int start, List<Integer> list, List<List<Integer>> res) {
		if(k == 0){
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for(int i = start; i <=n; i++){
			list.add(i);
			backtrack(n, k-1, i+1 , list, res);
			list.remove(list.size()-1);
		}
	}
}
