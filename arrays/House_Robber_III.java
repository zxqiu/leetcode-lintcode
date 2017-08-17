/*

House Robber III


The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example
  3
 / \
2   3
 \   \ 
  3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

    3
   / \
  4   5
 / \   \ 
1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.


解：
Divide and Conquer

这种题基本上就是把大问题化小。

每个节点返回包含和不包含自身时的两个最大值。
1.包含自身的最大值，为自身的值加上左右两个孩子不包含自身的值。
2.不包含自身的最大值，为以下4种情况取最大值：
  1).两个孩子不包含自身值的和。
  2).左孩子包含自身与右孩子不包含自身的和。
  3).左孩子不包含自身和右孩子包含自身的和。
  4).左右孩子都不包含自身的和。

*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int x) { val = x; }
 * }
 */


public class Solution {
    
    /*
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
    
    private class Value {
        int with, without;
        Value() {
            with = 0;
            without = 0;
        }
    }
    
    public int houseRobber3(TreeNode root) {
        Value val = helper(root);
        
        return Math.max(val.with, val.without);
    }
    
    private Value helper(TreeNode root) {
        Value val = new Value();
        
        if (root == null) {
            return val;
        }
        
        Value left = helper(root.left);
        Value right = helper(root.right);
        
        val.with = root.val + left.without + right.without;
        val.without = Math.max(left.with + right.with,
                        Math.max(left.with + right.without,
                        Math.max(left.without + right.with,
                        left.without + right.without)));
        
        return val;
    }
};
