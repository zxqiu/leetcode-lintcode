/*

Reverse Linked List II

Reverse a linked list from position m to n.

 Notice

Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.

Example
Given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.

Challenge 
Reverse it in-place and in one-pass


解：
用一个指针指向第m个结点之前的结点p0，然后把第n个结点以及其之前的结点挪动到p0之后。
用一个dummy结点指向head，然后令遍历指针p指向dummy。
用idx表示p的下一个结点的序号，则idx初始值为1，因为p初始指向dummy。当idx等于m时，令p0等与p。
挪动idx小于等于n的所有结点。

*/

/**
 * Definition for ListNode
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    /**
     * @param ListNode head is the head of the linked list 
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */ 
    public ListNode reverseBetween(ListNode head, int m , int n) {
        ListNode p0, p, dummy;
        int idx = 1; // idx is the index of the p->next
        
        dummy = new ListNode(0);
        dummy.next = head;
        p = p0 = dummy;
        
        while (p.next != null) {
            if (idx == m) {
                p0 = p;
                p = p.next;
            } else if (idx > m && idx <= n) {
                ListNode node = p.next;
                p.next = node.next;
                node.next = p0.next;
                p0.next = node;
            } else {
                p = p.next;
            }
            idx++;
        }
        
        return dummy.next;
    }
}
