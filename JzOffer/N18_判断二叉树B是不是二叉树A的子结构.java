package JzOffer;

// over
public class N18_判断二叉树B是不是二叉树A的子结构 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
		if(root1 == null || root2 == null) return false;
        boolean result = false;
        if(root1.val == root2.val) result = help(root1, root2);
        if(!result) result = HasSubtree(root1.left,root2);
        if(!result) result = HasSubtree(root1.right,root2);
		return result;
    }
	private boolean help(TreeNode root1, TreeNode root2) {
		/* wrong
		if(root1 == null && root2 == null) return true;
		if(root1 == null) return false;
		if(root2 == null) return false;
		*/
		if(root2 == null) return true;  // ★
		if(root1 == null) return false; // ★
		if(root1.val != root2.val) return false;
		return help(root1.left, root2.left) && help(root1.right, root2.right);
	}
}
