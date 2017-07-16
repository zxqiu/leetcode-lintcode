/*

Convert Binary Search Tree to Doubly Linked List


Convert a binary search tree to doubly linked list with in-order traversal.

Example
Given a binary search tree:

    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5


解：
DFS in order traversal

*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        if (root == null) {
            return null;
        }
        
        DoublyListNode dummy = new DoublyListNode(0);
        
        helper(dummy, root);
        
        dummy.next.prev = null;
        return dummy.next;
    }
    
    private DoublyListNode helper(DoublyListNode node, TreeNode root) {
        if (root == null) {
            return node;
        }
        
        node = helper(node, root.left);
        
        DoublyListNode cur = new DoublyListNode(root.val);
        node.next = cur;
        cur.prev = node;
        
        return helper(cur, root.right);
    }
}
