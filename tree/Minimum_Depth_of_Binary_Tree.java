/*

Minimum Depth of Binary Tree

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.


Example
Given a binary tree as follow:

  1
 / \ 
2   3
   / \
  4   5
The minimum depth is 2.


解：
Divide and conquer

若遇到空节点，直接返回上一级的深度。
若非空，分别查找左右子树的最小深度，然后根据得到的值确定返回值。
如果左右都返回当前的深度，说明左右子树都为空，返回当前节点深度。
如果左右返回值都不是当前节点深度，说明左右子树都不为空，返回其中小的那个。
如果左右有一个为当前节点深度，则返回不为当前节点深度的那个。

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
     * @return: An integer.
     */
     
    public int minDepth(TreeNode root) {
        return helper(root, 1);
    }
    
    private int helper(TreeNode root, int lvl) {
        if (root == null) {
            return lvl - 1;
        }
        
        int left = helper(root.left, lvl + 1);
        int right = helper(root.right, lvl + 1);
        
        if (left == lvl && right == lvl) {
            return lvl;
        } else if (left != lvl && right != lvl) {
            return Math.min(left, right);
        } else {
            return left == lvl ? right : left;
        }
    }
}