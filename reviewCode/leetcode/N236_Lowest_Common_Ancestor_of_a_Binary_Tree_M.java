package reviewCode.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: 
¡°The lowest common ancestor is defined between two nodes v and w 
as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).¡±

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 */

// NO TWO
public class N236_Lowest_Common_Ancestor_of_a_Binary_Tree_M {
	
	//recursive
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        return left != null ? left : right;
    }
    
	// iterative
	public class Solution {
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	        Map<TreeNode, TreeNode> parent = new HashMap<>();
	        Deque<TreeNode> stack = new ArrayDeque<>();
	        parent.put(root, null);
	        stack.push(root);

	        while (!parent.containsKey(p) || !parent.containsKey(q)) {
	            TreeNode node = stack.pop();
	            if (node.left != null) {
	                parent.put(node.left, node);
	                stack.push(node.left);
	            }
	            if (node.right != null) {
	                parent.put(node.right, node);
	                stack.push(node.right);
	            }
	        }
	        Set<TreeNode> ancestors = new HashSet<>();
	        while (p != null) {
	            ancestors.add(p);
	            p = parent.get(p);
	        }
	        while (!ancestors.contains(q))
	            q = parent.get(q);
	        return q;
	    }
	}
	
     public class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    	  }
}
