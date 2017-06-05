/*

Construct Binary Tree from Iorder and Postorder Traversal


Given inorder and postorder traversal of a tree, construct the binary tree.
Notice

You may assume that duplicates do not exist in the tree.

Example

Given inorder [1,2,3] and postorder [1,3,2], return a tree:

  2
 / \
1   3



解：
这道题的基本思路是反向遍历后序遍历结果，同时利用中序遍历结果来确定每个节点的位置。

中序遍历用来分辨某个节点的左子树和右子树。
后序遍历用来遍历整个树。

后序遍历的特性：
1.对于任意一个节点，它的所有子节点都在其左边。
2.一个节点的左子树在其右子树的左边。
3.若一个节点存在右子树，那么其在后序遍历结果左边的第一个节点一定是其右子节点。

中序遍历的特性：
1.对于任意一个节点，其左子树在其中序遍历结果的左边，右子树在右边。
2.如果某一个节点左边没有节点，说明它没有左子树。对于右子树也一样。

结合两者，可得：
对于后序遍历的任意一个节点，若其有左子树，则该节点在中序遍历结果中的左边一定有更多节点。对于右子树也一样。

由于后序遍历的第二个特性，所以对与后序遍历的结果中任意节点，先在中序遍历中查找其右子树，然后查其左子树。


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
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> locator = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < inorder.length; i++) {
            locator.put(inorder[i], i);
        }
        
        idx = postorder.length - 1;
        return helper(locator, 0, inorder.length - 1, postorder);
    }
    
    int idx;
    private TreeNode helper(HashMap<Integer, Integer> locator, int start, int end, int[] postorder) {
        if (start > end || idx < 0) {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[idx--]);
        
        root.right = helper(locator, locator.get(root.val) + 1, end, postorder);
        root.left = helper(locator, start, locator.get(root.val) - 1, postorder);
        
        return root;
    }
}
