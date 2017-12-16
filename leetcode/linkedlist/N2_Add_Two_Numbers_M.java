package leetcode.linkedlist;
/**
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8


 */

//YES
public class N2_Add_Two_Numbers_M {
    
	//Big bums
	//careful of overflow
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    	ListNode node1 = l1;
    	ListNode node2 = l2;
    	ListNode root = null;
    	ListNode node = null;
    	int carry = 0;
    	while(node1 != null && node2 != null){
    		int add1 = 0;
    		int add2 = 0;
    		if(node1 != null){
    			add1 = node1.val;
    			node1 = node1.next;
    		}
    		if(node2 != null){
    			add2 = node2.val;
    			node2 = node2.next;
    		}
    		int res = add1 + add2 + carry;
    		carry = (res >= 10 ? 1 : 0);
    		res = (res >= 10 ? (res % 10) : res);
    		ListNode temp = new ListNode(res);
    		if(node == null){
    			root = temp;
    			node = temp;
    		}
    		else{
    			node.next = temp;
    			node = temp;
    		}
    	}
    	if(carry == 1) node.next = new ListNode(1);  //donn't forget бя
    	return root;
    }
}
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
