package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
 */

// NO TWO
// binary index tree ??
public class N315_Count_of_Smaller_Numbers_After_Self_H {
	
	
	// merge sort
	int[] count;
	public List<Integer> countSmaller3(int[] nums) {
	    List<Integer> res = new ArrayList<Integer>();     

	    count = new int[nums.length];
	    int[] indexes = new int[nums.length];
	    int[] auxIndex = new int[nums.length];
	    for(int i = 0; i < nums.length; i++){
	    	indexes[i] = i;
	    }
	    mergesort(nums, indexes, 0, nums.length - 1, auxIndex);
	    for(int i = 0; i < count.length; i++){
	    	res.add(count[i]);
	    }
	    return res;
	}
	private void mergesort(int[] nums, int[] indexes, int start, int end, int[] auxIndex){
		if(end <= start){
			return;
		}
		int mid = (start + end) / 2;
		mergesort(nums, indexes, start, mid, auxIndex);
		mergesort(nums, indexes, mid + 1, end, auxIndex);
		
		merge(nums, indexes, start, mid, end, auxIndex);
	}
	private void merge(int[] nums, int[] indexes, int start, int mid, int end, int[] auxIndex){
		int left_index = start;
		int right_index = mid+1;
		int rightcount = 0;
		for(int i = start; i <= end; i++){
			auxIndex[i] = indexes[i];
		}
		for(int i = start; i <= end; i++){
			if(left_index > mid) indexes[i] = auxIndex[right_index++];
			else if(right_index > end){
				indexes[i] = auxIndex[left_index++];
				count[indexes[i]] += rightcount;
			}
			else if(nums[auxIndex[left_index]] <= nums[auxIndex[right_index]]){  // 非 nums[indexes[left_index]] <= nums[indexes[right_index]]
				indexes[i] = auxIndex[left_index++];
				count[indexes[i]] += rightcount;
			}
			else{
				indexes[i] = auxIndex[right_index++];
				rightcount++;
			}
		}
	}
	
	// binary search
	public List<Integer> countSmaller(int[] nums) {
	    Integer[] ans = new Integer[nums.length];
	    List<Integer> sorted = new ArrayList<Integer>();
	    for (int i = nums.length - 1; i >= 0; i--) {
	        int index = findIndex(sorted, nums[i]);
	        ans[i] = index;
	        sorted.add(index, nums[i]);
	    }
	    return Arrays.asList(ans);
	}
	//第一个大于等于target的坐标
	private int findIndex(List<Integer> sorted, int target) {
	    if (sorted.size() == 0) return 0;
	    int start = 0;
	    int end = sorted.size() - 1;
	    while(start <= end) {
	        int mid = start + (end - start) / 2;
	        if(sorted.get(mid) < target) {
	            start = mid + 1;
	        }else {
	            end = mid - 1;
	        }
	    }
	    return start;
	}
	
	// BST 
	// recursive
	// iterative ?? 
	class Node {
        Node left, right;
        int val, sum, dup = 1;
        public Node(int v, int s) {
            val = v;
            sum = s;
        }
    }
    public List<Integer> countSmaller2(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }
    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
        if (node == null) {
            node = new Node(num, 0);
            ans[i] = preSum;
        } else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else {
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }
    
    
}
