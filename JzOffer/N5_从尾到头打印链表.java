package JzOffer;

// over
import java.util.ArrayList;
public class N5_��β��ͷ��ӡ���� {
	/*
	 * ��ʽһ���ݹ�
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
	 * ��ʽ����ֱ��ֻ��ջ���ݽṹ
	 */
	
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
}
