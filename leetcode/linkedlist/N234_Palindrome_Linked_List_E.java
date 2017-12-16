package leetcode.linkedlist;
/**
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
 */

//NO
public class N234_Palindrome_Linked_List_E {

	public boolean isPalindrome(ListNode head) {
	    ListNode fast = head, slow = head;
	    while (fast != null && fast.next != null) {
	        fast = fast.next.next;
	        slow = slow.next;
	    }
	    if (fast != null) slow = slow.next; // odd nodes: let right half smaller
	    slow = reverse(slow);
	    fast = head;
	    
	    while (slow != null) {
	        if (fast.val != slow.val) return false;
	        fast = fast.next;
	        slow = slow.next;
	    }
	    return true;
	}
	public ListNode reverse(ListNode head) {
	    ListNode prev = null;
	    while (head != null) {
	        ListNode next = head.next;
	        head.next = prev;
	        prev = head;
	        head = next;
	    }
	    return prev;
	}
	
	public class Solution {
	    ListNode h;
	    public boolean isPalindrome(ListNode head) {
	        if (head == null) return true;
	    
	        if (h == null) h = head;

	        boolean tmp = true;        
	        if (head.next != null) tmp &= isPalindrome(head.next);
	    
	        tmp &= (head.val == h.val);
	        h = h.next;
	        return tmp;
	    }
	}
    
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}


