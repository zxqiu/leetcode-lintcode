/*

Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
A single node tree is a BST

Example
An example:

  2
 / \
1   4
   / \
  3   5
The above binary tree is serialized as {2,1,4,#,#,3,5} (in level order).


解：
先序遍历整颗树。

设定每个节点的最大值max和最小值min，每个节点的值应当小于max，大于min。否则该次递归返回false。
max和min初始为Long的最大可能值和最小可能值。
当搜索任意节点的左子树时，由于左子树的任何值都应当小于该结点的值，故max变为该节点的值。
当搜索任意节点的右子树时，由于右子树的任何值都应当大于该结点的值，故min变为该节点的值。

当左右子树任意一个返回false时，该次递归返回false。

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
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    
    private boolean helper(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        } else if (root.val >= max || root.val <= min) {
            return false;
        }
        
        if (helper(root.left, root.val, min)) {
            return helper(root.right, max, root.val);
        }
        
        return false;
    }
}
