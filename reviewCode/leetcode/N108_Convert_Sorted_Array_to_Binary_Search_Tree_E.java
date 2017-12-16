package reviewCode.leetcode;

import reviewCode.leetcode.N105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_M.TreeNode;

/**
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */

//YES
public class N108_Convert_Sorted_Array_to_Binary_Search_Tree_E {
	
	public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;
		return help(nums, 0, nums.length-1);
    }
	private TreeNode help(int[] nums, int s, int e) {
		if(s > e) return null;
		int mid = s + (e - s) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = help(nums, s, mid-1);
		root.right = help(nums, mid+1, e);
		return root;
	}

	//iterative ??








	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}
