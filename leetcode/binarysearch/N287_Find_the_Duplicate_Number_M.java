package leetcode.binarysearch;
/**
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */


public class N287_Find_the_Duplicate_Number_M {
	
	// N142_Linked_List_Cycle_II_M
	// o(n) time  o(1) space
	public int findDuplicate(int[] nums) {
		if (nums.length > 1) {
			int slow = 0;
			int fast = 0;  // starting point : 0  类似于 head
			slow = nums[slow];
			fast = nums[nums[fast]];  
			while (slow != fast) {
				slow = nums[slow];
				fast = nums[nums[fast]];
			}
			fast = 0;  // starting point : 0 ()
			while (fast != slow) {
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		return -1;
	}
	
	// binary search
	// O(1) space and O(nlogn) time
	// 注意 where 的条件和 right 的取值
	public int findDuplicate2(int[] nums) {
		int low = 1;  // 注意 
		int high = nums.length - 1;
	    while (low <= high) {
	        int mid = low + ((high - low) >> 1);
	        int cnt = 0;
	        for (int a : nums) {
	            if (a <= mid) ++cnt;
	        }
	        if(cnt > mid) high = mid - 1;  // 严格大于
	        else low = mid + 1;
	    }
	    return low;
	}
	public int findDuplicate3(int[] nums) {
		int low = 1;  // 注意 
		int high = nums.length - 1;
	    while (low < high) {  // 此时 不能 low <= high
	        int mid = low + ((high - low) >> 1);
	        int cnt = 0;
	        for (int a : nums) {
	            if (a <= mid) ++cnt;
	        }
	        if(cnt > mid) high = mid;   // 严格大于
	        else low = mid + 1;
	    }
	    return low;
	}

}
