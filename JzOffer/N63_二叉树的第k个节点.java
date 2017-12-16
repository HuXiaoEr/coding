package JzOffer;

import java.util.Deque;
import java.util.LinkedList;

// over
public class N63_二叉树的第k个节点 {

	//iterative inorder
	TreeNode KthNode(TreeNode pRoot, int k) {
		if(pRoot == null || k < 1) return null;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode nodes = pRoot;
        while(!stack.isEmpty() || nodes != null) {
        	if(nodes != null) {
        		stack.push(nodes);
        		nodes = nodes.left;
        	}
        	else {
        		nodes = stack.pop();
        		if(k == 1) return nodes;
        		k--;
        		nodes = nodes.right;
        	}
        }
		return null;
    }
	
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }

	}
}
