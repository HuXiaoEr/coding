package reviewCode.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */

//YES
public class N101_Symmetric_Tree_E {
    public boolean isSymmetric(TreeNode root) {
    	if(root == null) return true;
    	return isSymmetric(root.left, root.right);
    }
	private boolean isSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		return (left.val == right.val) && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	}

	//iterative
	public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if(root == null) return true;
        q.add(root.left);
        q.add(root.right);
        while(q.size() > 1){
            TreeNode left = q.poll(),
                     right = q.poll();
            if(left== null&& right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);            
        }
        return true;
    }
	
	//same idea
	public boolean isSymmetric3(TreeNode root) {
	    if (root == null) return true;
	    Stack<TreeNode> stack = new Stack<>();
	    stack.push(root.left);
	    stack.push(root.right);
	    while (!stack.empty()) {
	        TreeNode n1 = stack.pop(), n2 = stack.pop();
	        if (n1 == null && n2 == null) continue;
	        if (n1 == null || n2 == null || n1.val != n2.val) return false;
	        stack.push(n1.left);
	        stack.push(n2.right);
	        stack.push(n1.right);
	        stack.push(n2.left);
	    }
	    return true;
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
