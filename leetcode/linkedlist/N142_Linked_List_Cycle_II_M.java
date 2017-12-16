package leetcode.linkedlist;
/**
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
 */

//YES TWO
public class N142_Linked_List_Cycle_II_M {
	
	// my code
	public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
        	slow = slow.next;
        	fast = fast.next.next;
        	if(slow == fast) break;
        }
        if(fast.next == null || fast.next.next == null) return null;
        int clen = 1;
        ListNode cnode = fast.next;
        while(cnode != fast){  		// not necessary
        	clen++;
        	cnode = cnode.next;
        }
        slow = head;
        fast = head;
        while(clen > 0){
        	fast = fast.next;
        	clen--;
        }
        while(fast != slow){
        	slow = slow.next;
        	fast = fast.next;
        }
		return slow;
    }
	
	/*
	 * better
	 * 
	 * my solution is like this: using two pointers, one of them one step at a time. 
	 * another pointer each take two steps. 
	 * Suppose the first meet at step k,the length of the Cycle is r. so..2k-k=nr,k=nr
	 * 
	 * Now, the distance between the start node of list and the start node of cycle is s. 
	 * the distance between the start of list and the first meeting node is k(the pointer which wake one step at a time waked k steps).
	 * the distance between the start node of cycle and the first meeting node is m, so...s=k-m,
	 * 
	 * s=nr-m=(n-1)r+(r-m),here we takes n = 1..so, using one pointer start from the start node of list, 
	 * another pointer start from the first meeting node, 
	 * all of them wake one step at a time, the first time they meeting each other is the start of the cycle.
	 */
	public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
        	slow = slow.next;
        	fast = fast.next.next;
        	if(slow == fast) break;  //Œª÷√
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
