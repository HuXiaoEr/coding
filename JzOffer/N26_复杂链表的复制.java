package JzOffer;

public class N26_复杂链表的复制 {
	public class RandomListNode {
	    int label;
	    RandomListNode next = null;
	    RandomListNode random = null;

	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
	
	public RandomListNode Clone(RandomListNode pHead)
    {
        cloneNext(pHead);
        cloneRandom(pHead);
        RandomListNode copyRoot = reconnectNodes(pHead);
		return copyRoot;
    }
	private void cloneNext(RandomListNode pHead) {
		while(pHead != null)
		{
			RandomListNode next = pHead.next;
			pHead.next = new RandomListNode(pHead.label);
			pHead.next.next = next;
			pHead = next;
		}
	}
	private void cloneRandom(RandomListNode pHead) {
		while(pHead != null)
		{
			if(pHead.random != null) pHead.next.random = pHead.random.next;  // 注意if条件判断，健壮性判断
			pHead = pHead.next.next;
		}
	}
	private RandomListNode reconnectNodes(RandomListNode pHead) {
		if(pHead == null) return null;
		
		RandomListNode copyRoot = pHead.next;
		while(pHead.next != null)   // 注意where条件，健壮性判断
		{
			RandomListNode next = pHead.next;
			pHead.next = pHead.next.next;
			pHead = next;
		}
		return copyRoot;
		
		/*
		 * my code：
        	RandomListNode res = pHead.next;
        	node = pHead;
        	while(node != null && node.next != null){    // node != null 不需要
        		RandomListNode next = node.next;
        		node.next = node.next.next;
        		node = next;
        	}
    		return res;
    	*/
	}
}
