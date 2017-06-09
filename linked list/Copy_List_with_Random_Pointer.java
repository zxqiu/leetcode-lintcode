/*
Copy List with Random Pointer


A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.

解：
方法一：
空间O(n)
时间O(n)

用一个HashMap来保存已经生成的节点。
在复制新节点的时候，首先判断该结点是否已经生成，如果已经生成就直接用，如果没生成就生成，并且存入HashMap。
在复制节点的random pointer的时候也是一样，如果已经生成就直接用，如果没生成就生成并且存入HashMap。
*/


/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<Integer, RandomListNode> map = new HashMap<Integer, RandomListNode>();
        RandomListNode iter, store, dummy = new RandomListNode(0);

        if (head == null) {
            return null;
        }
        
        dummy.next = new RandomListNode(head.label);
        store = dummy.next;
        iter = head;
        
        while (iter != null) {
            if (iter.next == null) {
                store.next = null;
            } else {
                if (!map.containsKey(iter.next.label)) {
                    map.put(iter.next.label, new RandomListNode(iter.next.label));
                }
                store.next = map.get(iter.next.label);
            }
            
            if (iter.random == null) {
                store.random = null;
            } else {
                if (!map.containsKey(iter.random.label)) {
                    map.put(iter.random.label, new RandomListNode(iter.random.label));
                }
                store.random = map.get(iter.random.label);
            }
            
            store = store.next;
            iter = iter.next;
        }
        
        return dummy.next;
    }
}




/*
方法二：
空间O(1)
时间O(n)

先把每个复制出的节点串连在对应原生节点的后面，这样得到一个双倍长度的list。
然后，每个原生节点random指针的下一个节点，就是对应该原生节点的复制节点random指针应当指向的节点。
最后，分离两个list便能得到复制的list。

这种方法相当于把第一种方法中用map存储的位置信息在原list中存储了起来。

*/



/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode dummy, iter = head;
        
        /* copy */
        while (iter != null) {
            RandomListNode copy = new RandomListNode(iter.label);
            copy.next = iter.next;
            iter.next = copy;
            iter = copy.next;
        }
        
        /* link random */
        iter = head;
        while (iter != null) {
            iter.next.random = null;
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }
        
        /* decouple */
        dummy = new RandomListNode(0);
        iter = dummy;
        while (head != null) {
            iter.next = head.next;
            head = head.next.next;
            iter = iter.next;
        }
        
        return dummy.next;
    }
}
