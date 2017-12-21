package leetcode.linkedlist;


/**
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
 */


public class N19_Remove_Nth_Node_From_End_of_List_M {

	// my code
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        if(n <= 0) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for(int i = 0; i < n; i++) fast = fast.next;
        while(fast.next != null){
        	fast = fast.next;
        	slow = slow.next;
        }
        ListNode next = slow.next;
        slow.next = next.next;
        next.next = null;
        return dummy.next;
    }
    
    
    
	private class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
}
