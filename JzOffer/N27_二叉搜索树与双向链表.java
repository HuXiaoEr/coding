package JzOffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 均over
public class N27_二叉搜索树与双向链表 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;
	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	
	//Iterative, mine
    public TreeNode Convert(TreeNode pRootOfTree) {
    	if(pRootOfTree == null) return null;
    	Stack<TreeNode> s = new Stack<>();
    	TreeNode node = pRootOfTree;
    	TreeNode pre = null;
    	while(node != null || !s.isEmpty()){
    		if(node != null){
    			s.push(node);
    			node = node.left;
    		}
    		else{
    			node = s.pop();
    			node.left = pre;
    			if(pre != null) pre.right = node;
    			pre = node;
    			node = node.right;
    		}
    	}
    	TreeNode res = pRootOfTree;
    	while(res .left != null) res = res.left;
    	return res;
    }
	
	//JZOffer better
		TreeNode rightMost = null;
		public TreeNode Convert3(TreeNode pRootOfTree) {
	        helpConvert2(pRootOfTree);
	        while(pRootOfTree != null && pRootOfTree.left != null) pRootOfTree = pRootOfTree.left;
	        return pRootOfTree;
	    }

		private void helpConvert2(TreeNode root) {
			if(root == null) return;
			helpConvert2(root.left);
			if(rightMost != null) rightMost.right = root;
			root.left = rightMost;
			rightMost = root;
			helpConvert2(root.right);
		}
	
		
	//Mine clear 
	public TreeNode Convert2(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        TreeNode root = pRootOfTree;
        while(root.left != null)
        {
        	root = root.left;
        }
        helpConvert(pRootOfTree);
        return root;
    }
	private void helpConvert(TreeNode pRootOfTree) {
		if(pRootOfTree == null) return;
		helpConvert(pRootOfTree.left);
        helpConvert(pRootOfTree.right);
		TreeNode left = pRootOfTree.left;
		if(left != null)
		{
			while(left.right != null)
			{
				left = left.right;
			}
			left.right = pRootOfTree;
			pRootOfTree.left = left;
		}
        TreeNode right = pRootOfTree.right;
        if(right != null)
        {
        	while(right.left != null)
        	{
        		right = right.left;
        	}
        	right.left = pRootOfTree;
        	pRootOfTree.right = right;
        }
	}
	
	
}
