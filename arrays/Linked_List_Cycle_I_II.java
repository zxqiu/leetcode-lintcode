
/*
Linked List Cycle

Given a linked list, determine if it has a cycle in it.


解：
设两个指针slow和fast，同时指向队头。
slow每次向后移动一步，fast每次向后移动两步。
若存在环，则slow和fast最终必然相遇。
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
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {  
        if (head == null) {
            return false;
        }
        
        ListNode slow, fast;
        slow = head;
        fast = head.next;
        
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return false;
    }
}



/*
Linked List Cycle II

Given a linked list, return the node where the cycle begins.

If there is no cycle, return null.


解：
设走过的总时间为t，环的长度为h，环之前的长度为L，则：
fast走过的路程为：2t + 1 = L + n*h + x
slow走过的路程为：t = L + m*h + y

由于fast和slow最后在环中会相遇，所以：
x = y
fast方程减去slow方程乘以2的积：
1 = -L + (n - 2*m)*h - x
==> L = (n - 2*m)*h - 1 - x

若fast从环内x位置开始，n-2*m为一个正整数，设为a，则
L = x + a*h - 1 - x
==> L = a*h - 1

故，让fast从环内x位置开始，slow从list头开始，两者都每次前进一步，
则当fast的下一个为slow时，slow所在位置为环的起点
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
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. 
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {  
        if (head == null) {
            return null;
        }
        
        ListNode fast, slow;
        fast = head.next;
        slow = head;
        
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (fast != slow) {
            return null;
        }
        
        slow = head;
        
        while (fast.next != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}