package JzOffer;

/**
 *��һ��������ʱ�临�Ӷ�O(mn)
 *������������ջ��ʱ�临�Ӷ�O(m+n)���ռ临�Ӷ�O(m+n)
 *�������ȵõ���������ĳ��ȣ����㳤�Ȳ�������ߣ�ʱ�临�Ӷ�O(m+n)
 *���ģ��������ȵõ���������ĳ��ȣ�ʱ�临�Ӷ�O(m+n)��best
 *
 *³���ԣ���������û�й����ڵ�
 */

// ��over
public class N37_��������ĵ�һ��������� {
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	
	//best
	public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2)
	{
        //if(pHead1 == null || pHead2 == null) return null;  //����
		ListNode p1 = pHead1;
		ListNode p2 = pHead2;
		while(p1 != p2)
		{
			p1 = (p1 == null ? pHead2 : p1.next);
			p2 = (p2 == null ? pHead1 : p2.next);
		}
		return p1;
	}
	
	
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
 		if(pHead1 == null || pHead2 == null) return null;
        int length1 = 0;
        int length2 = 0;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1 != null){
            p1 = p1.next;
            length1++;
        }
        while(p2 != null){
            p2 = p2.next;
            length2++;
        }
        p1 = pHead1;
        p2 = pHead2;
        int diff = length1-length2;
        if(diff < 0)
        {
        	p1 = pHead2;
        	p2 = pHead1;
        	diff = -diff;
        }
        while(diff > 0)
        {
        	diff--;
        	p1 = p1.next;
        }
        while(p1 != null){  //³���ԣ���������û�й����ڵ�
            if(p1 == p2) return p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        return null; //³���ԣ���������û�й����ڵ�
    }
	
}
