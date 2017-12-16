package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
The set [1,2,3,бн,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */

//NO
public class N60_Permutation_Sequence_M {
	
	//Mine code -> TLE
	public String getPermutation(int n, int k) {
        int[] flag = new int[1];
        String[] res = new String[1];
        res[0] = "";
        flag[0] = k;
        backtrack(1, n, flag, new ArrayList<Integer>(), res);
    	return res[0];
    }
	private void backtrack(int count, int n, int[] flag, ArrayList<Integer> list, String[] res) {
		if(count > n){
			flag[0]--;
			if(flag[0] == 0){
				for(int i = 0; i < list.size(); i++)
					res[0] += list.get(i);
			}
		}
		for(int i = 1; i <= n; i++){
		    if(list.contains(i)) continue;
			if(!res[0].equals("")) return;
			list.add(i);
			backtrack(count+1, n, flag, list, res);
			list.remove(list.size()-1);
		}
	}
	
	// Not Mine
	public String getPermutation2(int n, int k) {
		int pos = 0;
		List<Integer> numbers = new ArrayList<>();
		int[] factorial = new int[n + 1];
		StringBuilder sb = new StringBuilder();

		// create an array of factorial lookup
		int sum = 1;
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			sum *= i;
			factorial[i] = sum;
		}
		// factorial[] = {1, 1, 2, 6, 24, ... n!}

		// create a list of numbers to get indices
		for (int i = 1; i <= n; i++) {
			numbers.add(i);
		}
		// numbers = {1, 2, 3, 4}

		k--;

		for (int i = 1; i <= n; i++) {
			int index = k / factorial[n - i];
			sb.append(String.valueOf(numbers.get(index)));
			numbers.remove(index);
			k -= index * factorial[n - i];
		}

		return String.valueOf(sb);
	}
	//same idea
	public String getPermutation3(int n, int k) {
	    LinkedList<Integer> list = new LinkedList<>();
	    for (int i = 1; i <= n; i++) list.add(i);
	    int fact = 1;
	    for (int i = 2; i <= n; i++) fact *= i; // factorial
	    
	    StringBuilder strBuilder = new StringBuilder();
	    for (k--; n > 0; n--) {
	        fact /= n;
	        strBuilder.append(list.remove(k / fact));
	        k %= fact;
	    }
	    
	    return strBuilder.toString();
	}
}
