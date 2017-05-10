/*

Binary Tree Serialization



Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.
Notice

There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.




 Example

An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:

  3
 / \
9  20
  /  \
 15   7

Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.

You can use other method to do serializaiton and deserialization.





解：

记录每个父节点的两个子节点的值。如果某一个子节点为空，则记为“#”。
所有值用“,”隔开以便于分割。

serialization:
对整个树一层一层进行记录。每次循环记录某一层节点的所有子节点。
将每层记录后的非空节点放进一个队列，作为下一层的父节点。
成功记录的关键在于对于每一个父节点，必须同时记录它的两个子节点，不论是否为空。

example中的树serialize之后为：
"3,9,20,#,#,15,7"

deserialization:
先将字符串按照“,”拆分成一个values数组。
对一个父节点进行还原。每次循环还原某一个父节点的两个子节点。
将每个父节点的非空子节点放入队列，一直遍历整个values数组。
由于serialize时保证了每个父节点都有两个子节点，所以每次只需要找出values数组中接下来的两个值，就是该父节点的两个子节点值。


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
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        
        Queue<TreeNode> roots = new LinkedList<TreeNode>();
        StringBuilder sb = new StringBuilder();
        
        roots.offer(root);
        sb.append(root.val);
        
        while (!roots.isEmpty()) {
            TreeNode tmpRoot = roots.poll();
            
            if (tmpRoot.left == null) {
                sb.append(",#");
            } else {
                sb.append("," + tmpRoot.left.val);
                roots.offer(tmpRoot.left);
            }
            
            if (tmpRoot.right == null) {
                sb.append(",#");
            } else {
                sb.append("," + tmpRoot.right.val);
                roots.offer(tmpRoot.right);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> roots = new LinkedList<TreeNode>();
        
        roots.offer(root);
        
        for (int i = 1; i < values.length; i += 2) {
            TreeNode tmpRoot = roots.poll();
            if (!values[i].equals("#")) {
                tmpRoot.left = new TreeNode(Integer.valueOf(values[i]));
                roots.offer(tmpRoot.left);
            }
            
            if (!values[i + 1].equals("#")) {
                tmpRoot.right = new TreeNode(Integer.valueOf(values[i + 1]));
                roots.offer(tmpRoot.right);
            }
        }
        
        return root;
    }
}
