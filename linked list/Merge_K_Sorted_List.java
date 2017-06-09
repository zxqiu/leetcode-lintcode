/*
Merge k sorted linked lists and return it as one sorted list.

Analyze and describe its complexity.


解：

方法一：
Divide & Conquer
将lists拆成左右两部分，分别合并后再将左右合并。

递归的终止条件是，当传入的lists中只剩下一个list时直接返回该list。
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
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    
    // Divide & conquer
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null) {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }
    
    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return lists.get(start);
        }
        
        // divide
        int mid = (start + end) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        
        // conquer
        ListNode dummy = new ListNode(0);
        ListNode iter = dummy;
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
        
        if (left != null) {
            iter.next = left;
        }
        
        if (right != null) {
            iter.next = right;
        }
        
        return dummy.next;
    }
}



/*
方法二：
heap

先把每个list的头放进heap中，然后每次从中取出值最小的队列头放入结果队列中，再把该队列头的下一个元素放入heap中。
由于每个队列都是有序的，且heap有自排序特性，每次从队列中取出的都是当前最小节点。

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
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        Queue<ListNode> heap = new PriorityQueue(lists.size(), nodeComparator);
        ListNode iter, dummy = new ListNode(0);
        
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        
        iter = dummy;
        while (!heap.isEmpty()) {
            iter.next = heap.poll();
            iter = iter.next;
            if (iter.next != null) {
                heap.offer(iter.next);
            }
        }
        
        return dummy.next;
    }
    
    private Comparator<ListNode> nodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val;
        }
    };
}
