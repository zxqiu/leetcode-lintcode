/*

Rotate List



Given a list, rotate the list to the right by k places, where k is non-negative.


Example

Given 1->2->3->4->5 and k = 2, return 4->5->1->2->3.


解：
首先计算链表长度，并找出最后一个节点。
根据长度计算出需要把末尾多少个节点移动到链表头，并切断需要移动的部分，移动到表头。

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode next, tail;
        int len = 1;
        
        if (head == null) {
            return null;
        }
        
        next = head;
        while (next.next != null) {
            len++;
            next = next.next;
        }
        tail = next;
        
        len = len - (k % len) - 1;
        next = head;
        while (len > 0) {
            next = next.next;
            len--;
        }
        
        tail.next = head;
        tail = next;
        head = tail.next;
        tail.next = null;
        
        return head;
    }
}
