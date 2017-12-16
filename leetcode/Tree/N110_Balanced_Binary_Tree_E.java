package leetcode.Tree;

import reviewCode.leetcode.N108_Convert_Sorted_Array_to_Binary_Search_Tree_E.TreeNode;

/**
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

 */

//YES
public class N110_Balanced_Binary_Tree_E {
	public boolean isBalanced(TreeNode root) {
		return help(root) != -1;
	}
	private int help(TreeNode root){
		if(root == null) return 0;
		int left = help(root.left);
		int right = help(root.right);
		if(left == -1 || right == -1 || left - right > 1 || right - left > 1) return -1;
		return Math.max(left, right)+1;
	}
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}
