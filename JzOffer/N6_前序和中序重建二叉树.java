package JzOffer;

import java.util.Stack;

// 递归OVER
public class N6_前序和中序重建二叉树 {
	
	 public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	 
	// recursive
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		if(pre == null || in == null || pre.length != in.length) return null;
		return helpReconstructBinaryTree(pre, in, 0, pre.length-1, 0, in.length-1);
    }
	private TreeNode helpReconstructBinaryTree(int[] pre, int[] in, int pres, int pree, int ins, int ine) {
		if(pres > pree || ins > ine) return null;
		TreeNode node = new TreeNode(pre[pres]);
		int mid = 0;
		for(int i = ins; i <= ine; i++)
		{
			if(pre[pres] == in[i]) mid = i;
		}
		node.left = helpReconstructBinaryTree(pre, in, pres+1, mid+pres-ins, ins, mid-1);
		node.right = helpReconstructBinaryTree(pre, in, mid+pres-ins+1, pree, mid+1, ine);
		return node;
	}
	
	// iterative
	// https://discuss.leetcode.com/topic/795/the-iterative-solution-is-easier-than-you-think/16?page=1
	public TreeNode reConstructBinaryTree2(int [] pre,int [] in) {
		if(pre == null || in == null || pre.length != in.length || pre.length == 0) return null;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode root = new TreeNode(pre[0]);
		TreeNode cur = root;
		for(int i = 1, j = 0; i < pre.length; i++){
			if(cur.val != in[j]){
				cur.left = new TreeNode(pre[i]);
				stack.push(cur);
				cur = cur.left;
			}
			else{
				j++;
				while(!stack.isEmpty() && stack.peek().val == in[j]){
					j++;
					cur = stack.pop();
				}
				cur.right = new TreeNode(pre[i]); // ★★
				cur = cur.right;
			}
		}
		return root;
    }
	
	
	// 中序、后序重建树
	// iterative
    public TreeNode buildTree(int[] in, int[] post) {
        if(in == null || post == null || in.length != post.length || in.length == 0) return null;
        TreeNode root = new TreeNode(post[post.length-1]);
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        for(int i = post.length-2, j = in.length-1; i >= 0; i--){
        	if(cur.val != in[j]){
        		cur.right = new TreeNode(post[i]);
        		stack.push(cur);
        		cur = cur.right;
        	}
        	else{
        		j--;
        		while(!stack.isEmpty() && stack.peek().val == in[j]){
        			j--;
        			cur = stack.pop();
        		}
        		cur.left = new TreeNode(post[i]);
        		cur = cur.left;
        	}
        }
        return root;
    }
}
