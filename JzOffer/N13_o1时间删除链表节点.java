package JzOffer;

import reviewCode.leetcode.N206_Reverse_Linked_List_E.ListNode;

/*
 * 前提条件：要删除的节点一定在链表中，因为需要o(n)时间才能判断链表中是否包含某一节点
 * 
 * 1. 要删除的节点不是尾节点
 * 2. 链表只有一个节点
 * 3. 要删除的节点是尾节点
 */
public class N13_o1时间删除链表节点 {

	// 平均O(1)
	// [(n-1)*o(1)+o(n)] / n = o(1)
	public void deleteNode(ListNode head, ListNode node){
		if(node == null || head == null) return;
		if(node.next != null){
			ListNode next = node.next;
			node.val = next.val;
			node.next = next.next;
			next.next = null; // ★
			next = null; // ★
		}
		else if(head == node){
			head = null; // ★
			node = null; // ★
		}
		else{
			ListNode p = head;
			while(p.next != node) p = p.next;
			p.next = null;
			node = null; // ★
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
