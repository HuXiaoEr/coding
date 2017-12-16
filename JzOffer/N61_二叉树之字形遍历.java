package JzOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 均over
public class N61_二叉树之字形遍历 {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }

	}
	
	public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer> >();
		if(pRoot == null) return res;
		Stack<TreeNode> stack0 = new Stack<TreeNode>();
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		int cur = 0;
		stack0.push(pRoot);
		while(!stack0.isEmpty() || !stack1.isEmpty()){
			TreeNode node = null;
			int size = 0;
			ArrayList<Integer> list = new ArrayList<Integer>();
			if(cur == 0){
				size = stack0.size();
				for(int i = 0; i < size; i++){
					node = stack0.pop();
					list.add(node.val);
					if(node.left != null) stack1.push(node.left);
					if(node.right != null) stack1.push(node.right);
				}
			}
			else{
				size = stack1.size();
				for(int i = 0; i < size; i++){
					node = stack1.pop();
					list.add(node.val);
					if(node.right != null) stack0.push(node.right);
					if(node.left != null) stack0.push(node.left);
				}
			}
			cur = 1 - cur;
			res.add(list);
		}
		return res;
	}
	
	
	public ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
		ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer> >();
		if(pRoot == null) return res;
	    Queue<TreeNode> queue= new LinkedList<TreeNode>();
	    queue.add(pRoot);
	    boolean leftToright = true;
	    while(!queue.isEmpty())
	    {
	    	ArrayList<Integer> al = new ArrayList<Integer>();
	    	int size = queue.size();
	    	for(int i = 0; i < size; i++)
	    	{
	    		TreeNode temp = queue.poll();
	    		if(leftToright) al.add(temp.val);
	    		else al.add(0, temp.val);
	    		if(temp.left != null) queue.add(temp.left);
	    		if(temp.right != null) queue.add(temp.right);
	    	}
	    	res.add(al);
	    	leftToright = !leftToright;
	    }
		return res;
    }
}
