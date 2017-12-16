package JzOffer;

// over
public class N15_链表中倒数第k个节点 {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
    public ListNode FindKthToTail(ListNode head,int k) {
    	if(head == null || k < 1) return null;
    	ListNode node = head;
    	while(node != null && k > 0){
    		node = node.next;
    		k--;
    	}
    	if(k != 0) return null;  // ★★
    	ListNode res = head;
    	while(node != null){
    		node = node.next;
    		res = res.next;
    	}
    	return res;
    }
	
	
	// 健壮性判断
	// k <= 0 时， return null;
	// k > head.length()时，return null;  (K > 0)
	
	// my code
	// best
    public ListNode FindKthToTail2(ListNode head,int k) {
    	if(head == null || k <= 0) return null;  // 别忘了健壮性判断
    	ListNode fast = head;
    	ListNode slow = head;
    	int i = 0;
    	for(; i < k && fast != null; i++) fast = fast.next;
    	if(i < k) return null; // 注意  不是if(fast == null) return null;  防止用例：5 {1，2，3，4，5}
    	while(fast != null){
    		fast = fast.next;
    		slow = slow.next;
    	}
    	return slow;
    }
	
	
	
	
	// 注意 k
	// 注意 fast != null  不是  fast.next != null
	public ListNode FindKthToTail3(ListNode head,int k) {
    	if(head == null || k <= 0) return null;  // 别忘了健壮性判断
		ListNode fast = head;
		ListNode slow = head;
		// 注意while条件
		while(k >= 1 && fast != null)
		{
			fast = fast.next;
			k--;
		}
		// 注意while条件
		while(fast != null)
		{
			fast = fast.next;
			slow = slow.next;
		}
		// 注意健壮性判断
		if(k > 0) return null;
		return slow;
    }
}
