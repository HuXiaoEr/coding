package reviewCode.leetcode;
/**
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 */


public class N141_Linked_List_Cycle_E {
	
	//bad code
    public boolean hasCycle(ListNode head) {
    	if(head == null || head.next == null) return false;
    	ListNode node1 = head;
    	ListNode node2 = head;
    	while(node1 != null && node2 != null){
    		node1 = node1.next;
    		if(node2.next == null) return false;
    		node2 = node2.next.next;  // java.lang.NullPointerException
    		if(node1 == node2) return true;
    	}
    	return false;
    }
    
    //not mine
    public boolean hasCycle2(ListNode head) {
        if(head==null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next!=null && runner.next.next!=null) { // important 
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
    
    // The program gives every visited node a sign by pointing next to head.
    public boolean hasCycle3(ListNode head) {
    	if(head == null || head.next == null) {
    		return false;
    	}
    	ListNode node = head;
    	while(node != null) {
    		if(node.next == head) {
    			return true;
    		}
    		ListNode temp = node.next;
    		node.next = head;
    		node = temp;
    	}
    	
    	return false;
    }
    
    
    
    
    class ListNode {
    	      int val;
    	      ListNode next;
    	      ListNode(int x) {
    	          val = x;
    	          next = null;
    	      }
    	  }
}
