/*

Intersection of Two Linked Lists


Write a program to find the node at which the intersection of two singly linked lists begins.

 Notice

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.


Example
The following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Challenge 
Your code should preferably run in O(n) time and use only O(1) memory.


解：
计算两个list的长度，把长的那个的头节点向后移动直到两个list长度一样。
然后同时遍历两个list，直到找到相遇点，或者到达list末尾。

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
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode 
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode node;
        
        node = headA;
        while (node != null) {
            lenA++;
            node = node.next;
        }
        
        node = headB;
        while (node != null) {
            lenB++;
            node = node.next;
        }
        
        node = (lenA > lenB) ? headA : headB;
        for (int i = Math.abs(lenA - lenB); i > 0; i--) {
            node = node.next;
        }
        
        if (lenA > lenB) {
            headA = node;
        } else {
            headB = node;
        }
        
        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        
        return null;
    }  
}
