/*

Binary Search Tree Iterator



Design an iterator over a binary search tree with the following rules:

    Elements are visited in ascending order (i.e. an in-order traversal)
    next() and hasNext() queries run in O(1) time in average.


Example

For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12

Challenge

Extra memory usage O(h), h is the height of the tree.

Super Star: Extra memory usage O(1)



解：
用一个node来保存每颗子树的root。
每次next时，若node不为空，则把node的所有左子树包括node在内一并加入栈中。
然后取栈顶元素的右子树作为新的node，并返回栈顶元素。

这样做保证了每个子树的左子树会被加入栈中一次，且不会重复加入。

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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
public class BSTIterator {
    Stack<TreeNode> st;
    TreeNode node;
    
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        st = new Stack<TreeNode>();
        node = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return !st.isEmpty() || node != null;
    }
    
    //@return: return next node
    public TreeNode next() {
        if (st.isEmpty() && node == null) {
            return null;
        }
        
        while (node != null) {
            st.push(node);
            node = node.left;
        }
        
        node = st.peek().right;
        
        return st.pop();
    }
}
