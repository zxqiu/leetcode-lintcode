/*

Remove Duplicates from Sorted List

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

解：
每次对比下一个节点的值和当前节点的值是否相等。
若相等，移除下一个节点。

*/


/**
 * Definition for ListNode
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
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     */
    public static ListNode deleteDuplicates(ListNode head) { 
        if (head == null) {
            return head;
        }
        
        ListNode iter = head;
        while (iter.next != null) {
            if (iter.next.val == iter.val) {
                iter.next = iter.next.next;
            } else {
                iter = iter.next;
            }
        }
        
        return head;
    }  
}