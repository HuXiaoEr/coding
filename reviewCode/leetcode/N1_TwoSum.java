package reviewCode.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class N1_TwoSum {
	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		if(nums == null || nums.length < 2) return res;
		HashMap<Integer, Integer> hash = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			if(hash.containsKey(target-nums[i])){
				res[0] = i;
				res[1] = hash.get(target-nums[i]);
				break;
			}
			hash.put(nums[i], i);
		}
		return res;
	}
}
