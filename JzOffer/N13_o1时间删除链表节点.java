package JzOffer;

import reviewCode.leetcode.N206_Reverse_Linked_List_E.ListNode;

/*
 * ǰ��������Ҫɾ���Ľڵ�һ���������У���Ϊ��Ҫo(n)ʱ������ж��������Ƿ����ĳһ�ڵ�
 * 
 * 1. Ҫɾ���Ľڵ㲻��β�ڵ�
 * 2. ����ֻ��һ���ڵ�
 * 3. Ҫɾ���Ľڵ���β�ڵ�
 */
public class N13_o1ʱ��ɾ������ڵ� {

	// ƽ��O(1)
	// [(n-1)*o(1)+o(n)] / n = o(1)
	public void deleteNode(ListNode head, ListNode node){
		if(node == null || head == null) return;
		if(node.next != null){
			ListNode next = node.next;
			node.val = next.val;
			node.next = next.next;
			next.next = null; // ��
			next = null; // ��
		}
		else if(head == node){
			head = null; // ��
			node = null; // ��
		}
		else{
			ListNode p = head;
			while(p.next != node) p = p.next;
			p.next = null;
			node = null; // ��
		}
	}
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
