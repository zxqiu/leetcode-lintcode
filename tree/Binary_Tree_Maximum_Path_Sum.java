/*

Binary Tree Maximum Path Sum


Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example
Given the below binary tree:

  1
 / \
2   3
return 6.



解：
divide and conquer

后序遍历整个树。
对于每个子树来说，其最大path sum存在四种可能：
1.左子树返回值加上自身的值；
2.右子树返回值加上自身的值；
3.自身的值什么也不加；
4.左子树返回值加上右子树返回值加上自身的值。

前三中情况可以与该子树的父节点组成新的path sum，故每次递归返回值应当为前三种中最大的。
最后一种不能于父节点组合，故只存用于计算全局最大path sum。

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
    
    int maxPath;
    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        /* avoid null root. Keep in mind maxPath must be put after helper */
        return Math.max(helper(root), maxPath);
    }
    
    private int helper(TreeNode root) {
        int left, right, max;
        
        if (root == null) {
            return 0;
        }
        
        left = helper(root.left);
        right = helper(root.right);
        
        max = Math.max(root.val, Math.max(left + root.val, right + root.val));
        maxPath = Math.max(maxPath, Math.max(max, left + root.val + right));
        
        return max;
    }
}
