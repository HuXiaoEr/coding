package JzOffer;

// OVER
public class N16_��ת���� {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	public ListNode ReverseList(ListNode head) {
		if(head == null) return null;
		ListNode pre = null;
		ListNode now = head;
		
		while(now != null) // ע��where������ ����now.next != null
		{
			ListNode next = now.next;
			now.next = pre;
			pre = now;
			now = next;
		}
		return pre;
    }
}
