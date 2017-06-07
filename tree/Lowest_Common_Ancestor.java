/*

Lowest Common Ancestor

Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

 Notice

Assume two nodes are exist in tree.

Example
For the following binary tree:

  4
 / \
3   7
   / \
  5   6
LCA(3, 5) = 4

LCA(5, 6) = 7

LCA(6, 7) = 7



解：
first order traverse

利用返回值的三种情况来直接完成信息传递：
1.null。当前节点为空，或者当前节点不是两个目标节点之一，并且左右子树内也不包含目标节点。
2.目标节点。当前节点为目标节点或者目标节点存在于当前节点的子树中。
3.LCA。当前节点的左子树和右子树中分别包含两个目标节点。

由于题目假设两个目标节点一定存在，所以不需要helper函数直接完成先序遍历即可。


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
 */
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return null;
        }
        
        if (root == A || root == B) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        
        if ((left == A && right == B) || (left == B && right == A)) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        
        return null;
    }
}
