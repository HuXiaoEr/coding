package JzOffer;

// 平衡二叉树 思想后序遍历 代码over
// 其它over
public class N39_二叉树的深度_平衡二叉树后序遍历解决 {

	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	
	//法一：
	int depth;
	public int TreeDepth(TreeNode root) {
		if(root == null) return 0;
		depth(root, 0);
		return depth;
    }
	private void depth(TreeNode root, int i) {
		if(root == null)
		{
			if(i > depth) depth = i;
			return;
		}
		depth(root.left, i+1);
		depth(root.right, i+1);
	}
	
	//法二：cleaner
	public int TreeDepth2(TreeNode root) {
		if(root == null) return 0;
		int leftDepth = TreeDepth2(root.left);
		int rightDepth = TreeDepth2(root.right);
		return Math.max(leftDepth, rightDepth) + 1;
    }
	
	//平衡二叉树
	//后序遍历  ★★
	public boolean IsBalanced_Solution(TreeNode root) {
		int temp = depthHelp(root);
		if(temp == -1) return false;
		return true;
	}
	private int depthHelp(TreeNode root) {
		if(root == null) return 0;
		int left = depthHelp(root.left);
		int right = depthHelp(root.right);
		if(left == -1 || right == -1) return -1;
		return Math.abs(left-right) > 1 ? -1 :Math.max(left, right)+1;
	}
	
	public static void main(String[] args) {
		N39_二叉树的深度_平衡二叉树后序遍历解决 n = new N39_二叉树的深度_平衡二叉树后序遍历解决();
		TreeNode root =n.new TreeNode(1);
		root.left = n.new TreeNode(2);
		root.right = n.new TreeNode(3);
		System.out.println(n.TreeDepth(root));
	}
	
}
