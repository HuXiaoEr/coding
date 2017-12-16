package JzOffer;

import java.util.Stack;

// N59_对称的二叉树
// 均over
public class N19_二叉树的镜像 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	
	//recursion
	public void Mirror(TreeNode root) {
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

	//iterative
	//栈或队列都可以
	public void Mirror2(TreeNode root) {
		if(root == null) return; // 别忘了 健壮性判断
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			if(node.left != null || node.right != null){
				TreeNode temp = node.left;
				node.left = node.right;
				node.right = temp;
			}
			if(node.left!=null){
				stack.push(node.left);
			}
			if(node.right!=null){
				stack.push(node.right);
			}
		}
	}
}
