package JzOffer;

// over
public class N15_�����е�����k���ڵ� {
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
    	if(k != 0) return null;  // ���
    	ListNode res = head;
    	while(node != null){
    		node = node.next;
    		res = res.next;
    	}
    	return res;
    }
	
	
	// ��׳���ж�
	// k <= 0 ʱ�� return null;
	// k > head.length()ʱ��return null;  (K > 0)
	
	// my code
	// best
    public ListNode FindKthToTail2(ListNode head,int k) {
    	if(head == null || k <= 0) return null;  // �����˽�׳���ж�
    	ListNode fast = head;
    	ListNode slow = head;
    	int i = 0;
    	for(; i < k && fast != null; i++) fast = fast.next;
    	if(i < k) return null; // ע��  ����if(fast == null) return null;  ��ֹ������5 {1��2��3��4��5}
    	while(fast != null){
    		fast = fast.next;
    		slow = slow.next;
    	}
    	return slow;
    }
	
	
	
	
	// ע�� k
	// ע�� fast != null  ����  fast.next != null
	public ListNode FindKthToTail3(ListNode head,int k) {
    	if(head == null || k <= 0) return null;  // �����˽�׳���ж�
		ListNode fast = head;
		ListNode slow = head;
		// ע��while����
		while(k >= 1 && fast != null)
		{
			fast = fast.next;
			k--;
		}
		// ע��while����
		while(fast != null)
		{
			fast = fast.next;
			slow = slow.next;
		}
		// ע�⽡׳���ж�
		if(k > 0) return null;
		return slow;
    }
}
