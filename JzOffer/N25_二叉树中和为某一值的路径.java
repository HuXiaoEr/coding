package JzOffer;

import java.util.ArrayList;

public class N25_二叉树中和为某一值的路径 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	
	// best
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        ArrayList<Integer> list = new ArrayList<Integer>();
        dfs(root, list, target, res);
    	return res;
    }
	private void dfs(TreeNode root, ArrayList<Integer> list, int target, ArrayList<ArrayList<Integer>> res) {
		int tar = target - root.val;
		list.add(root.val);
		if(root.left == null && root.right == null){
			if(tar == 0){
				res.add(new ArrayList<Integer>(list));
			}
		}
		if(root.left != null)
			dfs(root.left, list, target-root.val, res);
		if(root.right != null)
			dfs(root.right, list, target-root.val, res);
		list.remove(list.size()-1);
	}
	
	// better
    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root,int target) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root == null) return res;
        ArrayList<Integer> list = new ArrayList<Integer>();
        dfs(root, list, target, res);
    	return res;
    }
	private void dfs2(TreeNode root, ArrayList<Integer> list, int target, ArrayList<ArrayList<Integer>> res) {
		if(root.left == null && root.right == null){
			if(target-root.val == 0){
                list.add(root.val);
				res.add(new ArrayList<Integer>(list));
                list.remove(list.size()-1);
			}
			return;
		}
		list.add(root.val);
		if(root.left != null)
			dfs2(root.left, list, target-root.val, res);
		if(root.right != null)
			dfs2(root.right, list, target-root.val, res);
		list.remove(list.size()-1);
	}
	
}
