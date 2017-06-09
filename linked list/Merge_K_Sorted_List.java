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
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        return divide(lists, 0, lists.size() - 1);
    }
    
    public ListNode divide(List<ListNode> lists, int start, int end) {
        int mid = (start + end) / 2;
        ListNode left, right;
        
        if (start == end) {
            return lists.get(start);
        }
        
        left = divide(lists, start, mid);
        right = divide(lists, mid + 1, end);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode a, ListNode b) {
        ListNode store, dummy = new ListNode(0);
        store = dummy;
        
        while (a != null && b != null) {
            if (a.val > b.val) {
                store.next = b;
                b = b.next;
            } else {
                store.next = a;
                a = a.next;
            }
            store = store.next;
        }
        
        store.next = (a != null) ? a : b;
        
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
