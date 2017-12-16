package JzOffer;


// 迭代基本over
public class N57_删除链表中重复的节点 {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
	// recursive   better than mine  over
    public ListNode deleteDuplication(ListNode pHead)
    {
		if(pHead == null || pHead.next == null) return pHead;
        ListNode cur = pHead;
        if(cur.val != cur.next.val){
            cur.next = deleteDuplication(cur.next);
            return cur;
        } 
        else{
			int val = cur.val;
            while(cur != null && cur.val == val) cur = cur.next;
            return deleteDuplication(cur);
        }
    }
	// my recursive  over
    public ListNode deleteDuplication3(ListNode pHead)
    {
    	if(pHead == null) return null;
    	if(pHead.next == null) return pHead;
    	if(pHead.val != pHead.next.val){
    		pHead.next = deleteDuplication(pHead.next);
    		return pHead;
    	}
    	return deleteDuplication(pHead.next.next, pHead.val);
    }
	private ListNode deleteDuplication(ListNode pHead, int val) {
		if(pHead == null) return null;
		if(pHead.val == val) return deleteDuplication(pHead.next, val);
		return deleteDuplication(pHead);
	}
    
    
    
    // iterative
	public ListNode deleteDuplication2(ListNode pHead) {
		ListNode trick = new ListNode(-1);
		trick.next = pHead;  // ★    in case of {1}
		ListNode pre = trick;
		ListNode now = trick.next;
		while(now != null && now.next != null){ // ★
			if(now.val == now.next.val){
				int val = now.val;
				while(now != null && now.val == val) now = now.next; //while条件
				pre.next = now; // ★
			}
			else{
				//pre.next = now; 不需要
				pre = now;
				now = now.next;
			}
		}
		return trick.next;
	}
	
}
