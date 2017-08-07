/*

Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.
Only constant memory is allowed.

Example
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5


解：
三个指针start,end,mover。
start指向每一段长度为k list的头，end指向尾，mover指向start前的一个。

每一次把mover的下一个节点移动到end后面，end和start不动。
当mover的下一个是end时停止，并且把mover和end都移动到start，然后start移动到下一个。

例如：
假设k=3。

开始：
1   ->    2   ->    3   ->    4   ->    5
mover   start                end

1   ->    3   ->    4   ->    2   ->    5
mover              end      start

1   ->    4   ->    3   ->    2   ->    5
mover    end                start

结束后移动所有指针：
1   ->    4   ->    3   ->    2   ->    5
                            mover     start
                             end



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
     * @param k an integer
     * @return a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start, end, mover, dummy;
        int cnt = k;
        
        dummy = new ListNode(0);
        dummy.next = head;
        mover = dummy;
        start = head;
        end = dummy;
        
        while (end != null) {
            if (cnt != 0) {
                cnt--;
                end = end.next;
            } else {
                while (mover.next != end) {
                    ListNode node = mover.next;
                    mover.next = node.next;
                    
                    node.next = end.next;
                    end.next = node;
                }
                
                end = start;
                mover = start;
                start = start.next;
                cnt = k;
            }
        }
        
        return dummy.next;
    }
}
