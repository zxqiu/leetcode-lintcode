/*

Sort List

Sort a linked list in O(n log n) time using constant space complexity.

Example
Given 1->3->2->null, sort it to 1->2->3->null.

Challenge 
Solve it by merge sort & quick sort separately.



解：

方法一：
merge sort
将每个子链表从中间切割成两部分，分别送入下一次递归，直到只剩一个或者没有节点。
左右两部分的返回值merge后再返回给上一层。


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
     * @return: You should return the head of the sorted linked list,
     *          using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode iter = head;
        
        while (iter != null) {
            len++;
            iter = iter.next;
        }
        
        return helper(head, len);
    }
    
    private ListNode helper(ListNode head, int len) {
        int mid = len / 2;
        ListNode left, leftTail, right;
        left = leftTail = right = head;
        
        if (head == null || head.next == null) {
            return head;
        }
        
        for (int i = 0; i < mid - 1; i++) {
            leftTail = leftTail.next;
        }
        right = leftTail.next;
        leftTail.next = null;
        
        left = helper(left, mid);
        right = helper(right, len - mid);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode left, ListNode right) {
        ListNode iter, remain, dummy = new ListNode(0);
        
        iter = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                iter.next = left;
                left = left.next;
            } else {
                iter.next = right;
                right = right.next;
            }
            iter = iter.next;
        }
        
        remain = (left != null) ? left : right;
        
        while (remain != null) {
            iter.next = remain;
            remain = remain.next;
            iter = iter.next;
        }
        
        return dummy.next;
    }
}


/*

解法二：
Quick Sort

除了基本的快速排序之外，这里需要优化才能AC：
把所有跟pivot相同的节点全部挪动到pivot的后面，让其集中在一起。
分段计算时跳过所有跟pivot值相同的节点。

在计算left时把一个pivot带入，计算完成后这个pivot继续保持在左边的最后方，这样在连接right时比较方便。


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
     * @return: You should return the head of the sorted linked list,
     *          using constant space complexity.
     */
    public ListNode sortList(ListNode head) {  
        return qSort(head);
    }
    
    private ListNode qSort(ListNode head) {
        ListNode pivot, pivotEnd, dummy, iter, left, right;
        
        if (head == null || head.next == null) {
            return head;
        }
        
        dummy = new ListNode(0);
        dummy.next = head;
        pivot = pivotEnd = iter = head;
        
        while (iter.next != null) {
            if (iter.next.val == pivot.val) {
                moveToHead(pivotEnd, iter);
                pivotEnd = pivotEnd.next;
                if (iter.next == pivotEnd) {
                    iter = iter.next;
                }
            } else if (iter.next.val < pivot.val) {
                moveToHead(dummy, iter);
            } else {
                iter = iter.next;
            }
        }
        
        right = qSort(pivotEnd.next);
        iter = pivot.next;
        pivot.next = null;
        left = qSort(dummy.next);
        if (pivot != pivotEnd) {
            pivot.next = iter;
        }
        pivotEnd.next = right;
        
        return left;
    }
    
    private void moveToHead(ListNode dummy, ListNode node) {
        ListNode tmp = node.next;
        node.next = tmp.next;
        tmp.next = dummy.next;
        dummy.next = tmp;
    }
}

