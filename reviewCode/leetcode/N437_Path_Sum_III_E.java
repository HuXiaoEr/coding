package reviewCode.leetcode;

import java.util.HashMap;

/**
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */

// YES TWO
public class N437_Path_Sum_III_E {
	int res;
	// my code
	// Prefix sum method 
	// time complexity O(n)
	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        hash.put(0, 1);  // 别忘记这个，代表路径中包含根节点  HALF ★
		dfs(root, sum, hash, 0);
		return res;
	}
	// values代表路径累积和 ★
	private void dfs(TreeNode node, int sum, HashMap<Integer, Integer> hash, int values) {
		if(node == null) return;
		int tem = values + node.val;
		if(hash.containsKey(tem-sum)) res += hash.get(tem-sum);
		if(!hash.containsKey(tem)) hash.put(tem, 0);
		hash.put(tem, hash.get(tem)+1);
		dfs(node.left, sum, hash, tem);
		dfs(node.right, sum, hash, tem);
		hash.put(tem, hash.get(tem)-1);
	}
	
	
	// another idea
	// Time Complexity : O(N^2) for the worst case and O(NlogN) for balanced binary Tree.
	public int pathSum2(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
	// find the path that start from current node
    public int findPath(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
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
