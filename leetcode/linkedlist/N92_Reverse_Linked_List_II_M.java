package leetcode.linkedlist;

import reviewCode.leetcode.N206_Reverse_Linked_List_E.ListNode;

/**
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ? m ? n ? length of list.
 */

//NO
public class N92_Reverse_Linked_List_II_M {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	/* 
	 * ListNode pre
	 * 
	 * Set cur to be the immediate node after pre 
	 * and at each time move the [immediate node after cur (named move)] to be the immediate node [after pre]. 
	 * Repeat it for n - m times.
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++)
            pre = pre.next;
        ListNode cur = pre.next;
        for (int i = 0; i < n - m; i++) {
            ListNode move = cur.next; 
            cur.next = move.next;
            move.next = pre.next;  //important  not move.next = cur;
            pre.next = move;
        }
        return dummy.next;
	}
}
