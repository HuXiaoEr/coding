package JzOffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// over
public class N23_从上到下打印二叉树 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }
	}
	
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int size = 0;
        while(!queue.isEmpty())
        {
        	size = queue.size();
        	for(int i = 0; i < size; i++)
        	{
        		TreeNode temp = queue.poll();
        		res.add(temp.val);
        		if(temp.left != null) queue.add(temp.left);
        		if(temp.right != null) queue.add(temp.right);
        	}
        }
        return res;
    }
}
