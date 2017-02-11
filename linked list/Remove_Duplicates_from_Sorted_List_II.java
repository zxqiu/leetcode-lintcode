/*

Remove Duplicates from Sorted List II

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. 

 Example

Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.



解：
如何判断重复节点？
使用一个临时指针指向当前节点，然后从该结点开始向后遍历，若下一个节点的值与当前节点的值相同，则继续向后移动。
直到下一个节点的值与当前不同。然后判断临时指针是否移动过。
若移动过，表示从当前节点到临时指针指向的节点为止的所有节点重复，应当丢弃。
若没有移动过，表示从前节点应当加入新的linked list。


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
     * @return: ListNode head of the linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode store = dummy;
        
        while (head != null) {
            ListNode iter = head;
            while (iter.next != null && iter.next.val == iter.val) {
                iter = iter.next;
            }
            if (iter == head) {
                store.next = head;
                store = store.next;
            }
            
            head = iter.next;
        }
        
        store.next = null;
        return dummy.next;
    }
}
