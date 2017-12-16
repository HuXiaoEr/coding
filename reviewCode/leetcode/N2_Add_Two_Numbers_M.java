package reviewCode.leetcode;
/**
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8


 */

//YES
public class N2_Add_Two_Numbers_M {
    
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    	ListNode pre = new ListNode(0);
    	ListNode dummy = pre;
    	ListNode n1 = l1;
    	ListNode n2 = l2;
    	int carry = 0;
    	while(n1 != null || n2 != null){
    		int tem = (n1 == null ? n2.val : (n2 == null ? n1.val : n1.val + n2.val)) + carry;
    		if(tem >= 10){
    			carry = 1;
    			tem -= 10;
    		}
    		else carry = 0;
    		pre.next = new ListNode(tem);
    		pre = pre.next;
    		if(n1 != null) n1 = n1.next;
    		if(n2 != null) n2 = n2.next;
    	}
    	if(carry == 1) pre.next = new ListNode(1);
    	return dummy.next;
    }
}
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
