package reviewCode.leetcode;

import java.util.PriorityQueue;

public class N215_Kth_Largest_Element_in_an_Array_M {
	 public int findKthLargest(int[] nums, int k) {
		 int res = 0;
		 int idx = partition(nums, 0, nums.length-1);
		 while(idx != nums.length-k){
			 if(idx < nums.length-k) idx = partition(nums, idx+1, nums.length-1);
			 else if(idx > nums.length-k) idx = partition(nums, 0, idx-1);
		 }
		 return nums[idx];
	 }
	private int partition(int[] nums, int lo, int hi) {
		int small = lo;
		for(int i = lo+1; i <= hi; i++){
			if(nums[i] < nums[lo]){
				small++;
				if(small != i) exch(nums, small, i);
			}
		}
		exch(nums, small, lo);
		return small;
	}
	private void exch(int[] nums, int small, int i) {
		int temp = nums[small];
		nums[small] = nums[i];
		nums[i] = temp;
	}
}
