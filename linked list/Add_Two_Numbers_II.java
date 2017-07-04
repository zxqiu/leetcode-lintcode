/*

Add Two Numbers II

You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in forward order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 6->1->7 + 2->9->5. That is, 617 + 295.

Return 9->1->2. That is, 912.


解：
先把输入的两个list翻过来，然后求和并存入新的list。

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
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int carry = 0;
        
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        while (l1 != null || l2 != null || carry != 0) {
            ListNode node = new ListNode(0);
            int tmp = carry;
            
            if (l1 != null) {
                tmp += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                tmp += l2.val;
                l2 = l2.next;
            }
            
            if (tmp >= 10) {
                tmp -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            
            node.val = tmp;
            node.next = dummy.next;
            dummy.next = node;
        }
        
        return dummy.next;
    }
    
    private ListNode reverse(ListNode l) {
        ListNode dummy = new ListNode(0);
        
        while (l != null) {
            ListNode tmp = l.next;
            l.next = dummy.next;
            dummy.next = l;
            l = tmp;
        }
        
        return dummy.next;
    }
}
