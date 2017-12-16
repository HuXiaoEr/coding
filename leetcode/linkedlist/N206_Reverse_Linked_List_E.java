package leetcode.linkedlist;
/**
Reverse a singly linked list.
 */

//YES HALF TWO
public class N206_Reverse_Linked_List_E {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseList(ListNode head) {
		if (head == null)
			return null;
		ListNode now = head;
		ListNode next = head.next;
		while (next != null) {
			if (now == head)
				now.next = null; // 不要忘记这个 head.next = null，不然[1, 2] TLE
			ListNode temp = next.next;
			next.next = now;
			now = next;
			next = temp;
		}
		return now;
	}

	// Not Mine
	// best
	/* iterative solution */
	public ListNode reverseList2(ListNode head) {
		ListNode newHead = null; // ★
		while (head != null) {
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		return newHead;
	}

	/* recursive solution */
	public ListNode reverseList3(ListNode head) {
		return reverseListInt(head, null);
	}
	private ListNode reverseListInt(ListNode head, ListNode newHead) {
		if (head == null)
			return newHead;
		ListNode next = head.next;
		head.next = newHead;
		return reverseListInt(next, head);
	}
	
	//not so straight
	public ListNode reverseList5(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode nextNode=head.next;
        ListNode newHead=reverseList(nextNode);
        nextNode.next=head;
        head.next=null;
        return newHead;
    }
}

