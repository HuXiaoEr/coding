package JzOffer;

// over
public class N17_合并两个排序的链表 {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
	//better recursion
	public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode pMergedHead = null;
        if(list1.val < list2.val)
        {
        	pMergedHead = list1;
        	pMergedHead.next = Merge(list1.next, list2);
        }
        else
        {
        	pMergedHead = list2;
        	pMergedHead.next = Merge(list1, list2.next);
        }
        return pMergedHead;
    }
	
	// iterative
	public ListNode Merge1(ListNode list1,ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode now1 = list1;
        ListNode now2 = list2;
        while(now1 != null || now2 != null){
        	if(now1 == null){
        		pre.next = now2;
        		now2 = now2.next;
        	}
        	else if(now2 == null){
        		pre.next = now1;
        		now1 = now1.next;
        	}
        	else if(now1.val < now2.val){
        		pre.next = now1;
        		now1 = now1.next;
        	}
        	else{
        		pre.next = now2;
        		now2 = now2.next;
        	}
        	pre = pre.next;
        }
        return dummy.next;
	}
	
	//mine
	public ListNode Merge3(ListNode list1,ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode root = new ListNode(1);
        ListNode pre = root;
        while(node1 != null || node2 != null)
        {
        	if(node1 == null)
        	{
        		pre.next = new ListNode(node2.val);
        		node2 = node2.next;
        	}
        	else if(node2 == null)
        	{
        		pre.next = new ListNode(node1.val);
        		node1 = node1.next;
        	}
        	else if(node1.val <= node2.val)
        	{
        		pre.next = new ListNode(node1.val);
        		node1 = node1.next;
        	}
        	else
        	{
        		pre.next = new ListNode(node2.val);
        		node2 = node2.next;
        	}
        	pre = pre.next;
        }
		return root.next;
    }
	

}
