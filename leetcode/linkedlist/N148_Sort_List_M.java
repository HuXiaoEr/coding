package leetcode.linkedlist;
/**
Sort a linked list in O(n log n) time using constant space complexity.

 */

//NO
public class N148_Sort_List_M {
	
	// merge sort
	// the space complexity is O(logn), because the sortList function is called recursively. 
	// Also, a new node is created for each time a merge function is called.
	// Since both merge and sortList are called logn times, the space is O(logn).
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		// step 1. cut the list to two halves
		ListNode prev = null, slow = head, fast = head;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		prev.next = null;  // important 

		// step 2. sort each half
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);

		// step 3. merge l1 and l2
		return merge(l1, l2);
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode l = new ListNode(0), p = l;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
			p = p.next;
		}

		if (l1 != null)
			p.next = l1;

		if (l2 != null)
			p.next = l2;

		return l.next;
	}
// =================================== another idea =============================================
	 //  non-recurisve merge sort ???
	 // o(1) space solution ??

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
