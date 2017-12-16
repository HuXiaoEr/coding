package dataStructure;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Tree {

//	************ preorder**************************
	// recursive
	public List<Integer> preorder(TreeNode root){
		List<Integer> pre = new ArrayList<Integer>();
		preHelper(root, pre);
		return pre;
	}
	private void preHelper(TreeNode root, List<Integer> pre) {
		if(root == null) return;
		pre.add(root.val);
		preHelper(root.left, pre);
		preHelper(root.right, pre);
	}
	// iterative
	public List<Integer> preorder2(TreeNode root){
		List<Integer> pre = new ArrayList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode node = root;
		while(node != null || !stack.isEmpty()){
			if(node != null){
				pre.add(node.val);
				stack.push(node);
				node = node.left;
			}
			else node = stack.pop().right;
		}
		return pre;
	}

//	************ inorder**************************
	// recursive
	public List<Integer> inorder(TreeNode root){
		List<Integer> in = new ArrayList<Integer>();
		inHelper(root, in);
		return in;
	}
	private void inHelper(TreeNode root, List<Integer> in) {
		if(root == null) return;
		inHelper(root.left, in);
		in.add(root.val);
		inHelper(root.right, in);
	}
	// iterative
	public List<Integer> inorder2(TreeNode root){
		List<Integer> in = new ArrayList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode node = root;
		while(node != null || !stack.isEmpty()){
			if(node != null){
				stack.push(node);
				node = node.left;
			}
			else{
				node = stack.pop();
				in.add(node.val);
				node = node.right;
			}
		}
		return in;
	}

//	************ postorder**************************
	// recursive
	public List<Integer> postorder(TreeNode root){
		LinkedList<Integer> post = new LinkedList<Integer>(); // LinkedList
		postHelper(root, post);
		return post;
	}
	private void postHelper(TreeNode root, LinkedList<Integer> post) {
		if(root == null) return;
		post.addFirst(root.val);
		postHelper(root.right, post);
		postHelper(root.left, post);
	}
	// iterative
	public List<Integer> postorder2(TreeNode root){
		LinkedList<Integer> post = new LinkedList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode node = root;
		while(node != null || !stack.isEmpty()){
			if(node != null){
				post.addFirst(node.val);
				stack.push(node);
				node = node.right;
			}
			else node = stack.pop().left;
		}
		return post;
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
