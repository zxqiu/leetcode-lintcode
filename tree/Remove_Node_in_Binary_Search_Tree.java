/*

Remove Node in Binary Search Tree

Given a root of Binary Search Tree with unique value for each node. Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.

Example
Given binary search tree:

    5
   / \
  3   6
 / \
2   4
Remove 3, you can either return:

    5
   / \
  2   6
   \
    4
or

    5
   / \
  4   6
 /
2



解：
这道题首要是解决删除某一个节点的逻辑，此外只需要进行一次先序遍历即可。

对于任意一个节点，删除时需要考虑其子节点的情况：
1.只有左孩子。此时用左孩子代替该节点。
2.只有右孩子。此时用右孩子代替该结点。
3.没有孩子。用null代替该结点。
4.两个孩子都在。此时可以用左节点也可以用右节点。这里讨论左节点代替的情况。
  用左孩子代替该结点之后，由于原先的右孩子大于左子树的任意一个节点，故应当移动到左子树的最右端，也就是变成左子树的最右边的孩子的右子树。


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
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        
        if (root.val == value) {
            return remove(root);
        }
        
        root.left = removeNode(root.left, value);
        root.right = removeNode(root.right, value);
        
        return root;
    }
    
    private TreeNode remove(TreeNode root) {
        if (root.right == null) {
            return root.left;
        } else if (root.left == null) {
            return root.right;
        } else {
            TreeNode tmp = root.left;
            
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            
            tmp.right = root.right;
            return root.left;
        }
    }
}
