package JzOffer;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 均未over
// N297_Serialize_and_Deserialize_Binary_Tree_H
public class N62_序列化和反序列化二叉树 {
	private static final String splitString = ",";
	private static final String nullString = "#";
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	String Serialize(TreeNode root) {
		if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        Serialize(root, sb);
		return sb.toString();
	}
	private void Serialize(TreeNode root, StringBuilder sb) {
		if(root == null) sb.append(nullString + splitString);
		else
		{
			sb.append(root.val + splitString);
			Serialize(root.left, sb);
			Serialize(root.right, sb);
		}
	}
	
	TreeNode Deserialize(String str) {
		if(str == null) return null;
		Deque<String> deque = new LinkedList<String>(Arrays.asList(str.split(splitString)));
		return Deserialize(deque);
	}
	private TreeNode Deserialize(Deque<String> deque) {
		if(deque.isEmpty()) return null;
		String str = deque.poll();
		if(str.equals(nullString)) return null;
		TreeNode nodes = new TreeNode(Integer.valueOf(str));
		nodes.left = Deserialize(deque);
		nodes.right = Deserialize(deque);
		return nodes;
	}
	public static void main(String[] args) {
		System.out.println("boo:and:foo".split("o", 6).length);
	}
	
	// iterator
    String Serialize2(TreeNode root) {
        if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                sb.append(node.val+splitString);
                node = node.left;
            }
            else{
                sb.append(nullString+splitString);
                node = stack.pop().right;
            }
        }
        return sb.toString();
  }
}
