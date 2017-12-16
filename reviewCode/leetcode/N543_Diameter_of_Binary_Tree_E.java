package reviewCode.leetcode;
/**
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */

// YES TWO HALF
public class N543_Diameter_of_Binary_Tree_E {
	
	// similar to N124_Binary_Tree_Maximum_Path_Sum_H
	int max;
    public int diameterOfBinaryTree(TreeNode root) {
    	if(root == null) return 0;
    	max = Integer.MIN_VALUE;
    	maxDown(root);
    	return max;
    }
	private int maxDown(TreeNode node) {
		if(node == null) return 0;
		int left = maxDown(node.left);
		int right = maxDown(node.right);
		max = Math.max(max, left+right);
		return Math.max(left, right)+1;
	}

	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
}
