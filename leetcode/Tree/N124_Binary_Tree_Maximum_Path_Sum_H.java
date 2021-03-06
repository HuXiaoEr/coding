package leetcode.Tree;

import reviewCode.leetcode.N110_Balanced_Binary_Tree_E.TreeNode;

/**
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes 
from some starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

 */

//NO
//NO TWO
public class N124_Binary_Tree_Maximum_Path_Sum_H {
	int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
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
