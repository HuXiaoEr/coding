package JzOffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import leetcode.Tree.N101_Symmetric_Tree_E.TreeNode;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */

// N19_二叉树的镜像
// 均over
public class N59_对称的二叉树 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	boolean isSymmetrical(TreeNode pRoot) {
		return isSymmetrical(pRoot, pRoot);
    }
	private boolean isSymmetrical(TreeNode pRoot, TreeNode pRoot2) {
		if(pRoot == null && pRoot2 == null) return true;
		if(pRoot == null || pRoot2 == null) return false;
		if(pRoot.val != pRoot2.val) return false;
		return isSymmetrical(pRoot.left, pRoot2.right) && isSymmetrical(pRoot.right, pRoot2.left);
	}

	//iterative
	public boolean isSymmetrical2(TreeNode root) {
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
	public boolean isSymmetrical3(TreeNode root) {
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
	
}
