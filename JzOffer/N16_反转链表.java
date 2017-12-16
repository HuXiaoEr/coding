package JzOffer;

// OVER
public class N16_反转链表 {
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
		
		while(now != null) // 注意where条件， 不是now.next != null
		{
			ListNode next = now.next;
			now.next = pre;
			pre = now;
			now = next;
		}
		return pre;
    }
}
