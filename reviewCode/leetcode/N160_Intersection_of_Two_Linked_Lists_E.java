package reviewCode.leetcode;
/**
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 ¡ú a2
                   ¨K
                     c1 ¡ú c2 ¡ú c3
                   ¨J            
B:     b1 ¡ú b2 ¡ú b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
 */

//NO TWO
public class N160_Intersection_of_Two_Linked_Lists_E {
	
	//wrong
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
		while(nodeA != nodeB){
			nodeA = (nodeA == null) ? headB.next : nodeA.next;
			nodeB = (nodeB == null) ? headA.next : nodeB.next;
		}
		return nodeA;
    }

	// not mine
	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
	    //boundary check
	    if(headA == null || headB == null) return null;  // important
	    
	    ListNode a = headA;
	    ListNode b = headB;
	    
	    //if a & b have different len, then we will stop the loop after second iteration
	    while( a != b){
	    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
	        a = a == null? headB : a.next;
	        b = b == null? headA : b.next;    
	    }
	    
	    return a;
	}
	
	public static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
	}
}
