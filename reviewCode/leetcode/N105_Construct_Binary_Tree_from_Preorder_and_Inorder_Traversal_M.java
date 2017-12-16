package reviewCode.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
 */

//YES
public class N105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_M {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) return null;
		return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
	private TreeNode buildTree(int[] preorder, int pb, int pe, int[] inorder, int ib, int ie) {
		if(pb > pe || ib > ie) return null;  //donnot forget this  ∑Ò‘Ú p18 ArrayIndexOutOfBoundary
		TreeNode root = new TreeNode(preorder[pb]);
		int rootIdx = 0;
		for(int i = ib; i <= ie; i++){
			if(inorder[i] == preorder[pb]){
				rootIdx = i;
				break;
			}
		}
		root.left = buildTree(preorder, pb+1, pb+rootIdx-ib, inorder, ib, rootIdx-1);
		root.right = buildTree(preorder, pb+rootIdx-ib+1, pe, inorder, rootIdx+1, ie);
		return root;
	}


	// Not mine
	// use HashMap to cache the inorder[] position
	public TreeNode buildTree2(int[] preorder, int[] inorder) {
	    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
	    for(int i = 0; i < inorder.length; i++) {
	        inMap.put(inorder[i], i);
	    }
	    TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
	    return root;
	}

	public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
	    if(preStart > preEnd || inStart > inEnd) return null;
	    TreeNode root = new TreeNode(preorder[preStart]);
	    int inRoot = inMap.get(root.val);
	    int numsLeft = inRoot - inStart;
	    root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
	    root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
	    return root;
	}

	//iterative ??






	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
}
