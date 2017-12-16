package reviewCode.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
Given a Binary Search Tree (BST), 
convert it to a Greater Tree 
such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */

// YES TWO
public class N538_Convert_BST_to_Greater_Tree_E {

	// iterative
	public TreeNode convertBST(TreeNode root) {
		if(root == null) return null;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode node = root;
		//stack.push(root);
		int sum = 0;
		while(!stack.isEmpty() || node != null){
			while(node != null){
				stack.push(node);
				node = node.right;
			}
			node = stack.pop();
			node.val += sum;
			sum = node.val;
			node = node.left;
		}
		return root;
	}
	
	
	// recursive
	// not using global variable
	public TreeNode convertBST2(TreeNode root) {
	    dfs(root, 0);
	    return root;
	}
	public int dfs(TreeNode root, int val) {
	    if(root == null) return val;
	    int right = dfs(root.right, val);
	    int left = dfs(root.left, root.val + right);
	    root.val = root.val + right;
	    return left;
	}
	
	// recursive
	// use global variable
	int sum = 0;
	public TreeNode convertBST3(TreeNode root) {
		convert(root);
		return root;
	}
	public void convert(TreeNode cur) {
		if (cur == null)
			return;
		convert(cur.right);
		cur.val += sum;
		sum = cur.val;
		convert(cur.left);
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
