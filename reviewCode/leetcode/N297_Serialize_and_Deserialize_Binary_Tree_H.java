package reviewCode.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, 
so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

// NO TWO
public class N297_Serialize_and_Deserialize_Binary_Tree_H {

	/** Your Codec object will be instantiated and called as such: */
	/** Codec codec = new Codec(); */
	/** codec.deserialize(codec.serialize(root)); */
	
	// preorder 
	// recursive
	// DFS
    private static final String spliter = ",";
    private static final String NN = "X";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();  // remove ·½·¨
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
    
    // iterative
    // BFS 
    public String serialize2(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append(NN+spliter);
                continue;
            }
            res.append(node.val + spliter);
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize2(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(spliter);
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals(NN)) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals(NN)) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
    
    
	// preorder 
	// iterative
	// DFS
    
    // Encodes a tree to a single string.
    public String serialize3(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        TreeNode x=root;
        Deque<TreeNode> stack=new LinkedList<>();
        while (x!=null || !stack.isEmpty()) {
            if (x!=null) {
                sb.append(String.valueOf(x.val));
                sb.append(' ');
                stack.push(x);
                x=x.left;
            }
            else {
                sb.append("null ");
                x=stack.pop();
                x=x.right;
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize3(String data) {
        if (data.length()==0) return null;
        String[] node=data.split(" ");
        int n=node.length;
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0]));
        TreeNode x=root;
        stack.push(x);
        
        int i=1;
        while (i<n) {
            while (i<n && !node[i].equals("null")) {
                x.left=new TreeNode(Integer.valueOf(node[i++]));
                x=x.left;
                stack.push(x);
            }
            while (i<n && node[i].equals("null")) {
                x=stack.pop();
                i++;
            }
            if (i<n) {
                x.right=new TreeNode(Integer.valueOf(node[i++]));
                x=x.right;
                stack.push(x);
            }
        }
        return root;
    }
    
    
     public class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    	  }
}
