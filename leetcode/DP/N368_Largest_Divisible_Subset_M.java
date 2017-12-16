package leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Given a set of distinct positive integers, 
find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]
 */

//No
public class N368_Largest_Divisible_Subset_M {
	
	//Mine DP
	//Not right
	//o(n*n) time 
	//o(n*n) space
	//WHY??
	public List<Integer> largestDivisibleSubset(int[] nums) {
		if(nums == null || nums.length == 0) return new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
        int maxLength = -1;
        int maxIndex = -1;
        for(int i = 0; i < nums.length; i++){
        	List<Integer> temp = new ArrayList<>();
        	int tempMax = -1;
        	int tempIndex = -1;
        	for(int j = 0; j < i; j++){
        		boolean flag = true;
        		for(int k : res.get(j)){
        			flag = flag && ((nums[i] % k) == 0 || (k % nums[i]) == 0);
        			if(flag == false) break;
        		}
        		if(flag == true && res.get(j).size() > tempMax){
        			tempMax = res.get(j).size();
        			tempIndex = j;
        		}
        	}
        	if(tempMax != -1) temp.addAll(res.get(tempIndex));
        	temp.add(nums[i]);
        	res.add(temp);
        	if(temp.size() > maxLength){
        		maxLength = temp.size();
        		maxIndex = i;
        	}
        	System.out.println(temp + " " + tempIndex);
        }
		return res.get(maxIndex);
    }
	
	//Not Mine  best
	//similar to LIS бя
	//o(n*n) time
	//o(n) space
	public List<Integer> largestDivisibleSubset2(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums); //бя
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }
	public static void main(String[] args) {
		System.out.println(new N368_Largest_Divisible_Subset_M().largestDivisibleSubset(new int[]{4,8,10,240}));
	}
}
