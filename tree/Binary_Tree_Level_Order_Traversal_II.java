/*

Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its bottom-up level order traversal as:

[
  [15,7],
  [9,20],
  [3]
]


解：
虽说要求自底向上，但实际上真正实现自底向上并不划算。
还是自上向下level order遍历，但是加入结果数组时每次加在第一个即可。

这里虽然使用了两个循环，但是由于每个结点只遍历一次，所以时间复杂度为O(n)

*/

/**
 * Definition of TreeNode:
 *     public class TreeNode {
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
     * @return: buttom-up level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        if (root == null) {
            return ret;
        }
        
        q.offer(root);
        
        while (!q.isEmpty()) {
            Queue<TreeNode> tmpQ = new LinkedList<TreeNode>();
            ArrayList<Integer> list = new ArrayList<Integer>();
            
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) {
                    tmpQ.offer(node.left);
                }
                if (node.right != null) {
                    tmpQ.offer(node.right);
                }
            }
            q = tmpQ;
            ret.add(0, list);
        }
        
        return ret;
    }
}
