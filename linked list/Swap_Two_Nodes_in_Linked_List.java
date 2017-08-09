/*

Swap Two Nodes in Linked List


Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. It's guaranteed there is no duplicate values in the linked list. If v1 or v2 does not exist in the given linked list, do nothing.
Notice

You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.


Example

Given 1->2->3->4->null and v1 = 2, v2 = 4.

Return 1->4->3->2->null.


解：
遍历列表，找出这两个值的上一个点。
如果这两个点相邻，直接交换。
如果不相邻，先把这两个点出队，然后交换插回。

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        ListNode p1 = null;
        ListNode p2 = null;
        ListNode dummy = new ListNode(0);
        ListNode node1 = dummy;
        ListNode node2;
        
        dummy.next = head;
        
        while (node1.next != null) {
            if (node1.next.val == v1) {
                p1 = node1;
            } else if (node1.next.val == v2) {
                p2 = node1;
            }
            
            node1 = node1.next;
        }
        
        if (p1 == null || p2 == null) {
            return head;
        }
        
        node1 = p1.next;
        node2 = p2.next;
        
        if (node1.next == node2) {
            node1.next = node2.next;
            node2.next = node1;
            p1.next = node2;
        } else if (node2.next == node1) {
            node2.next = node1.next;
            node1.next = node2;
            p2.next = node1;
        } else {
            p1.next = node1.next;
            p2.next = node2.next;
            
            node2.next = p1.next;
            p1.next = node2;
            
            node1.next = p2.next;
            p2.next = node1;
        }
        
        return dummy.next;
    }
}
