/*

Construct Binary Tree from Preorder and Inorder Traversal


Given preorder and inorder traversal of a tree, construct the binary tree.
Notice

You may assume that duplicates do not exist in the tree.

Example

Given in-order [1,2,3] and pre-order [2,1,3], return a tree:

  2
 / \
1   3



解：
参照Construct Binary Tree from Preorder and Inorder Traversal。
把从后向前遍历postorder traversal变成从前向后遍历preorder traversal，同时注意preorder traversal任意一个节点的左子树在其右子树的左边，故先生成左子树。

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
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> locator = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < inorder.length; i++) {
            locator.put(inorder[i], i);
        }
        
        idx = 0;
        return helper(locator, 0, inorder.length - 1, preorder);
    }
    
    int idx;
    private TreeNode helper(HashMap<Integer, Integer> locator, int start, int end, int[] preorder) {
        if (start > end || idx < 0) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[idx++]);
        
        root.left = helper(locator, start, locator.get(root.val) - 1, preorder);
        root.right = helper(locator, locator.get(root.val) + 1, end, preorder);
        
        return root;
    }
}
