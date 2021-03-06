package leetcode.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1

Trivia:
This problem was inspired by this original tweet by Max Howell:
	Google: 90% of our engineers use the software you wrote (Homebrew), 
	but you can��t invert a binary tree on a whiteboard so fuck off.
 */

//YES
public class N226_Invert_Binary_Tree_E {
	
	// my code
	public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
		return root;
    }
	
	// use stack data structure.
	// it is bound to the application stack, which means that it's no so much scalable
	// - (you can find the problem size that will overflow the stack and crash your application), 
	// so more robust solution would be to use stack data structure.
	public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if(node.left != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);
        }
        return root;
    }
	
	// BFS - so called level order traversal.
	public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return root;
    }
}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
