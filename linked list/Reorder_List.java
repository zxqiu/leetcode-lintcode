/*

Reorder List


Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln

reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

Example

Given 1->2->3->4->null, reorder it to 1->4->2->3->null.

Challenge

Can you do this in-place without altering the nodes' values?


解：
方法一：
先把后半段list倒过来，然后从后向前遍历后半段，从前向后遍历前半段，从而合并。
可以利用一快一慢两个指针来找出list的中间节点。

*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: void
     */
    public void reorderList(ListNode head) {  
        ListNode left, right, slow, fast;
        
        if (head == null || head.next == null) {
            return;
        }
        
        left = slow = fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        right = slow.next;
        slow.next = null;
        
        /* reverse right */
        slow = right;
        fast = right.next;
        while (fast != null) {
            ListNode tmp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = tmp;
        }
        right.next = null;
        right = slow;
        
        /* combine */
        while (right != null) {
            ListNode tmp = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = tmp;
        }
    }
}


/*

方法二：
用一个stack先把list的前半段放入，然后依次出栈，相当于反向遍历前半段。
同时正向遍历后半段，并进行合并。

不能满足challenge中in place的要求。

*/

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: void
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode iter = head;
        int mid;
        
        while (iter != null) {
            stack.push(iter);
            iter = iter.next;
        }
        
        mid = stack.size() / 2;
        iter = head;
        for (int i = 0; i < mid; i++) {
            ListNode node = stack.pop();
            stack.peek().next = null;
            node.next = iter.next;
            iter.next = node;
            iter = iter.next.next;
        }
    }
}
