package JzOffer;

// over
import java.util.ArrayList;
public class N5_从尾到头打印链表 {
	/*
	 * 方式一：递归
	 */
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        print(listNode, res);
		return res;
    }
	private void print(ListNode listNode, ArrayList<Integer> res) {
		if(listNode == null) return;
		print(listNode.next, res);
		res.add(listNode.val);
	}
	
	/*
	 * 方式二：直接只用栈数据结构
	 */
	
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
}
