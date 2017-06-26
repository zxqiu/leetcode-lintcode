/*

Unique Binary Search Trees II

Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

Example
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
   
解：
DFS递归
辅助函数生成从start到end的BST。
那么当处理start到end之间的数字i时，应当从start到i-1生成左子树，从i+1到end生成右子树。
组合所有的左子树和右子树的可能性，并存入结果列表。
注意：
当start大于end时，说明应当返回含有空树的列表，而非返回空列表。

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
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> ret = new ArrayList<TreeNode>();
        
        if (start > end) {
            ret.add(null);
            return ret;
        }
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);
            
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ret.add(root);
                }
            }
        }
        
        return ret;
    }
}
