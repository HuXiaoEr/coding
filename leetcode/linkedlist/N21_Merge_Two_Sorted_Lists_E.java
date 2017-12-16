package leetcode.linkedlist;
/**

Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.

 */

//YES
public class N21_Merge_Two_Sorted_Lists_E {
	
	//iterative solution
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode node = root;
        while(l1 != null || l2 != null){
        	if(l1 == null){
        		node.next = l2;
        		break;
        	}
        	else if(l2 == null){
        		node.next = l1;
        		break;
        	}
        	else{
        		if(l1.val < l2.val){
        			node.next = l1;
        			l1 = l1.next;
        		}
        		else{
        			node.next = l2;
        			l2 = l2.next;
        		}
        		node = node.next;
        	}
        }
		return root.next;
    }
	
	//recursive solution
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
	    if(l2 == null) return l1;
	    if(l1.val < l2.val) {
	        l1.next = mergeTwoLists(l1.next, l2);
	        return l1;
	    } else {
	        l2.next = mergeTwoLists(l2.next, l1);
	        return l2;
	    }
	}
	class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
}
