/*

Segment Tree Modify


解；
For a Maximum Segment Tree, which each node has an extra value max to store the maximum value in this node's interval.

Implement a modify function with three parameter root, index and value to change the node's value with [start, end] = [index, index] to the new given value. Make sure after this change, every node in segment tree still has the max attribute with the correct value.

 Notice

We suggest you finish problem Segment Tree Build and Segment Tree Query first.

Example
For segment tree:

                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
if call modify(root, 2, 4), we can get:

                      [1, 4, max=4]
                    /                \
        [1, 2, max=4]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]
or call modify(root, 4, 0), we can get:

                      [1, 4, max=2]
                    /                \
        [1, 2, max=2]                [3, 4, max=0]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]

Challenge 
Do it in O(h) time, h is the height of the segment tree.


解：
DFS
当index在某个节点的start和end之外时，返回该节点的max。
当index与该节点start和end都一样时，修改该节点的max并返回。
其他情况，遍历该节点的左右两个孩子，并且根据左右两个孩子的max修改自己的max。


*/

/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     *@param root, index, value: The root of segment tree and 
     *@ change the node's value with [index, index] to the new given value
     *@return: void
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        helper(root, index, value);
    }
    
    private int helper(SegmentTreeNode root, int index, int value) {
        int left, mid, right;
        
        if (index < root.start || index > root.end) {
            return root.max;
        } else if (index == root.start && index == root.end) {
            root.max = value;
            return value;
        }
        
        mid = (root.start + root.end) / 2;
        
        left = helper(root.left, index, value);
        right = helper(root.right, index, value);
        
        root.max = Math.max(left, right);
        return root.max;
    }
}
