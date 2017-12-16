package reviewCode.leetcode;
/**
Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. 
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.
*/

// NO TWO
public class N540_Single_Element_in_a_Sorted_Array_M {
	
	// binary search
	public static int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mid % 2 == 1) mid--; // midÎªÅ¼Êý
            if (nums[mid] != nums[mid + 1]) end = mid; // in left
            else start = mid + 2; // in right
        }
        return nums[start];
    }
    public static void main(String[] args) {
		int res = singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8});
		System.out.println(res);
	}
}
