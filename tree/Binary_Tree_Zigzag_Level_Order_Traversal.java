/*

Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]



解：
相比正常的level order traversal使用Queue来保证每个level都是从左向右遍历，这里需要使用Stack来让每次取出子节点的顺序与上一次相反。
此外，还需要用一个flag来标记加入每个节点的子节点时的顺序。
比如上面的例子，
1. 初始时先让3入栈，flag值为false。
2. 3出栈，由于flag为false，其子节点9和20按从左到右顺序入栈。flag置为true。
3. 20先出栈，9后出栈。同时20出栈时，由于flag为true，其子节点按照7和15的从右到左顺序入栈。flag置为false。
4. 15现出栈，7后出栈。

所有节点出栈的顺序就是最后需要返回的结果。


*/


/**
 *  Definition of TreeNode:
 *   public class TreeNode {
 *       public int val;
 *       public TreeNode left, right;
 *       public TreeNode(int val) {
 *           this.val = val;
 *           this.left = this.right = null;
 *       }
 *   }
 */
 
 
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: A list of lists of integer include 
     *           the zigzag level order traversal of its nodes' values 
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        boolean goLeft = false;
        Stack<TreeNode> st = new Stack<TreeNode>();
        
        if (root == null) {
            return ret;
        }
        
        st.push(root);
        
        while(!st.isEmpty()) {
            Stack<TreeNode> tmpSt = new Stack<TreeNode>();
            ArrayList<Integer> list = new ArrayList<Integer>();
            
            while (!st.isEmpty()) {
                TreeNode node = st.pop();
                list.add(node.val);
                
                if (goLeft) {
                    if (node.right != null) {
                        tmpSt.push(node.right);
                    }
                    if (node.left != null) {
                        tmpSt.push(node.left);
                    }
                } else {
                    if (node.left != null) {
                        tmpSt.push(node.left);
                    }
                    if (node.right != null) {
                        tmpSt.push(node.right);
                    }
                }
            }
            
            st = tmpSt;
            ret.add(list);
            goLeft ^= true;
        }
        
        return ret;
    }
}
