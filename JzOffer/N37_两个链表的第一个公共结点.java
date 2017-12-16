package JzOffer;

/**
 *法一：蛮力法时间复杂度O(mn)
 *法二：用两个栈，时间复杂度O(m+n)，空间复杂度O(m+n)
 *法三：先得到两个链表的长度，计算长度差，长的先走，时间复杂度O(m+n)
 *法四：不用事先得到两个链表的长度，时间复杂度O(m+n)，best
 *
 *鲁棒性：两个链表没有公共节点
 */

// 均over
public class N37_两个链表的第一个公共结点 {
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
        //if(pHead1 == null || pHead2 == null) return null;  //不用
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
        while(p1 != null){  //鲁棒性：两个链表没有公共节点
            if(p1 == p2) return p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        return null; //鲁棒性：两个链表没有公共节点
    }
	
}
