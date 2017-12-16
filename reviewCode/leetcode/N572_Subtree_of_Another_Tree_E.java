package reviewCode.leetcode;
/**
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
A subtree of s is a tree consists of a node in s and all of this node's descendants. 
The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 */

// YES TWO
// 注意tree中元素重复
public class N572_Subtree_of_Another_Tree_E {

	public static boolean isSubtree(TreeNode s, TreeNode t) {
		if(s == null && t == null) return true;
		if(s == null || t == null) return false;
		boolean res = false;
		if(s.val == t.val) return res = isSub(s, t);
		if(res) return res;
		else return isSubtree(s.left, t) || isSubtree(s.right, t);
	}
	private static boolean isSub(TreeNode s, TreeNode t) {
		if(s == null && t == null) return true;
		if(s == null || t == null) return false;
		if(s.val != t.val) return false;
		return isSub(s.left, t.left) && isSub(s.right, t.right);
	}

	public static void main(String[] args) {
		TreeNode s = new TreeNode(1);
		s.left = new TreeNode(1);
		TreeNode t = new TreeNode(1);
		System.out.println(isSub(s, t));
	}
	 private static  class TreeNode {
			int val;
			TreeNode left;
			TreeNode right;

			TreeNode(int x) {
				val = x;
			}
		}
}

