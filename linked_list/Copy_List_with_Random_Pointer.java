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
        Map<Integer, RandomListNode> nodeMap = new HashMap<Integer, RandomListNode>();
        
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode iter = dummy;
        
        while (head != null) {
            RandomListNode node;
            if (nodeMap.containsKey(head.label)) {
                node = nodeMap.get(head.label);
            } else {
                node = new RandomListNode(head.label);
                nodeMap.put(node.label, node);
            }
            
            // add random link
            RandomListNode random = null;
            if (head.random != null) {
                if (nodeMap.containsKey(head.random.label)) {
                    random = nodeMap.get(head.random.label);
                } else {
                    random = new RandomListNode(head.random.label);
                    nodeMap.put(random.label, random);
                }
            }
            
            node.random = random;
            
            iter.next = node;
            iter = iter.next;
            head = head.next;
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
        // first, copy all nodes and insert into the original list
        RandomListNode iter = head;
        while (iter != null) {
            RandomListNode node = new RandomListNode(iter.label);
            node.next = iter.next;
            node.random = null;
            iter.next = node;
            
            iter = iter.next.next;
        }
        
        // connect the random pointer
        iter = head;
        while (iter != null) {
            RandomListNode node = iter.next;
            if (iter.random != null) {
                node.random = iter.random.next;
            }
            
            iter = iter.next.next;
        }
        
        // split the two list
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode node = dummy;
        iter = head;
        while (iter != null) {
            node.next = iter.next;
            iter.next = iter.next.next;
            node = node.next;
            iter = iter.next;
        }
        
        return dummy.next;
    }
}
