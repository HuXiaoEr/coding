package leetcode.DFS;

import java.util.HashMap;
import java.util.Map;

public class N337_House_Robber_III_M {
	
	// my code
	// TLE -> 123 / 124 test cases passed.
	public int rob(TreeNode root) {
        if(root == null) return 0;
    	return Math.max(rob(root, true), rob(root, false));
    }
    private int rob(TreeNode node, boolean flag) {
    	if(node == null) return 0;
    	int res = 0;
    	int lf = rob(node.left, false);
    	int lt = rob(node.left, true);
    	int rf = rob(node.right, false);
    	int rt = rob(node.right, true);
    	if(flag) return lf+rf+node.val;
    	else res = Math.max(Math.max(lf+rf, lt+rt), Math.max(lf+rt, lt+rf));
    	return res;
    }
    
    // https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem
    ///
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        
        int val = 0;
        
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        
        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }
    
    ///
    public int rob3(TreeNode root) {
        return robSub(root, new HashMap<>());
    }

    private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        
        int val = 0;
        
        if (root.left != null) {
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }
        
        if (root.right != null) {
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }
        
        val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);
        
        return val;
    }
    
    /// excellent
    public int rob4(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];
        
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        
        return res;
    }

	public class TreeNode {
    	      int val;
    	      TreeNode left;
    	      TreeNode right;
    	      TreeNode(int x) { val = x; }
    	  }
}
