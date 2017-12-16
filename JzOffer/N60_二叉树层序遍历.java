package JzOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author 胡小二
 *
 *从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class N60_二叉树层序遍历 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }

	}
	
	ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer> >();
		if(pRoot == null) return res;
	    Queue<TreeNode> queue= new LinkedList<TreeNode>();
	    queue.add(pRoot);
	    while(!queue.isEmpty())
	    {
	    	ArrayList<Integer> al = new ArrayList<Integer>();
	    	int size = queue.size();
	    	for(int i = 0; i < size; i++)
	    	{
	    		TreeNode temp = queue.poll();
	    		al.add(temp.val);
	    		if(temp.left != null) queue.add(temp.left);
	    		if(temp.right != null) queue.add(temp.right);
	    	}
	    	res.add(al);
	    }
		return res;
    }
	
	
	public static void main(String[] args) {
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.add(null);
		queue.add(null);
		System.out.println(queue.size());
	}
}
