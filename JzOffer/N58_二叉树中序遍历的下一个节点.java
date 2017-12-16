package JzOffer;


// over
public class N58_二叉树中序遍历的下一个节点 {
	public class TreeLinkNode {
	    int val;
	    TreeLinkNode left = null;
	    TreeLinkNode right = null;
	    TreeLinkNode next = null;

	    TreeLinkNode(int val) {
	        this.val = val;
	    }
	}
	public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
		if(pNode == null) return null;
        if(pNode.right == null)
        {
        	TreeLinkNode father = pNode.next;
        	if(father == null) return null;
        	while(father != null && father.right == pNode) //attention
        	{
        		pNode = father;
        		father = father.next;
        	}
        	return father;
        }
        else
        {
            pNode = pNode.right;
        	while(pNode.left != null) pNode = pNode.left;
        	return pNode;
        }
    }
}
