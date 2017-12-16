package JzOffer;

public class N56_链表中环的入口 {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
	public ListNode EntryNodeOfLoop(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
        	slow = slow.next;
        	fast = fast.next.next;
        	if(slow == fast) break;  //位置
        }
        if(fast.next == null || fast.next.next == null) return null;
        slow = head;
        while(fast != slow){
        	slow = slow.next;
        	fast = fast.next;
        }
		return slow;
    
    }
}
