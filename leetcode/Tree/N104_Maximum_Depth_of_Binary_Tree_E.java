package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */

//YES
public class N104_Maximum_Depth_of_Binary_Tree_E {
	public int maxDepth(TreeNode root) {
        int max = 0;
        dfs(root, 0, max);
		return max;
    }
	private void dfs(TreeNode root, int len, int max) {
		if(root == null){
			max = Math.max(len, max);
			return;
		}
		dfs(root.left, len+1, max);
		dfs(root.right, len+1, max);
	}
	
	//Not mine
	//recursive DFS
	public int maxDepth2(TreeNode root) {
        return root==null? 0 : Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }

	//iterative DFS  бябя
	public int maxDepth4(TreeNode root) {
	    if(root == null) {
	        return 0;
	    }
	    Stack<TreeNode> stack = new Stack<>();
	    Stack<Integer> value = new Stack<>();
	    stack.push(root);
	    value.push(1);
	    int max = 0;
	    while(!stack.isEmpty()) {
	        TreeNode node = stack.pop();
	        int temp = value.pop();
	        max = Math.max(temp, max);
	        if(node.left != null) {
	            stack.push(node.left);
	            value.push(temp+1);
	        }
	        if(node.right != null) {
	            stack.push(node.right);
	            value.push(temp+1);
	        }
	    }
	    return max;
	}
	
	//iterative BFS
	public int maxDepth3(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			count++;
		}
		return count;
	}
	

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}
