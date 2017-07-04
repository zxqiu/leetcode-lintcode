/*

Palindrome Linked List


Implement a function to check if a linked list is a palindrome.

Example
Given 1->2->1, return true

Challenge 
Could you do it in O(n) time and O(1) space?

解：
把list的后半段翻过来，然后跟前半段比较判断是否时回文。
可以用slow和fast指针的方法找到list的中点。
不需要判断list长度奇偶数，只需要判断前后两半长度相同的部分，奇数情况下多出来的一个一定时中间那个节点，可以忽略。

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
     * @return a boolean
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast, slow, tail;
        
        if (head == null || head.next == null) {
            return true;
        }
        
        fast = head;
        slow = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        tail = slow.next;
        slow.next = null;
        tail = reverse(tail);
        
        while (head != null && tail != null) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }
        
        return true;
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
