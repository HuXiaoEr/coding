package leetcode.Tree;

import java.util.Stack;

/**
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
 */

//YES
public class N98_Validate_Binary_Search_Tree_M {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//my code
	public boolean isValidBST(TreeNode root) {
		if(root == null) return true;
		boolean res = true;
		
		if(root.left != null){  // important     not root.left.val < root.val
			TreeNode temp = root.left;
			while(temp.right != null) temp = temp.right;
			res = res && (temp.val < root.val); 
		}
		if(root.right != null){ // important     not root.val < root.right.val
			TreeNode temp = root.right;
			while(temp.left != null) temp = temp.left;
			res = res && (root.val < temp.val); 
		}
		res = res && isValidBST(root.left) && isValidBST(root.right); 
		return res;
	}
	
	// not mine
	public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    /*
     * same idea
     * use Object Integer and null pointer to avoid the corner cases 
     * (when node has value Integer.MIN_VALUE or Integer.MAX_VALUE 
     */
    private boolean help(TreeNode p, Integer low, Integer high) {
        if (p == null) return true;
        return (low == null || p.val > low) && (high == null || p.val < high) && help(p.left, low, p.val) && help(p.right, p.val, high);
    }
    public boolean isValidBST3(TreeNode root) {
        return help(root, null, null);
    }
//  ============================== important =======================================
    
    //inorder 
    //recursion
    TreeNode pre = null;
    public boolean isValidBST4(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        return isValidBST(root.right);
    }
    
    //inorder
    //iterative
    public boolean isValidBST6(TreeNode root) {
    	   if (root == null) return true;
    	   Stack<TreeNode> stack = new Stack<>();
    	   TreeNode pre = null;
    	   while (root != null || !stack.isEmpty()) {
    	      while (root != null) {
    	         stack.push(root);
    	         root = root.left;
    	      }
    	      root = stack.pop();
    	      if(pre != null && root.val <= pre.val) return false;
    	      pre = root;
    	      root = root.right;
    	   }
    	   return true;
    	}
}
