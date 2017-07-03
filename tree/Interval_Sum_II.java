/*

Interval Sum II

Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):

For query(start, end), return the sum from index start to index end in the given array.
For modify(index, value), modify the number in the given index to value
 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Example
Given array A = [1,2,7,8,5].

query(0, 2), return 10.
modify(0, 4), change A[0] from 1 to 4.
query(0, 1), return 6.
modify(2, 1), change A[2] from 7 to 1.
query(2, 4), return 14.
Challenge 
O(logN) time for query and modify.


解：
Segment Tree
这道题在Interval Sum的基础上增加了修改。
所以首先完成Interval Sum，之后参照Segment Tree Modify完成修改部分。

修改时注意要后序遍历，每个root节点的sum要根据两个自节点更新后的结果来进行更新。

*/

public class Solution {
    STreeNode root;

    /**
     * @param A: An integer array
     */
    public Solution(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        
        root = buildSTree(A, 0, A.length - 1);
    }
    
    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        if (start > end) {
            return 0;
        }
        
        return searchSTree(root, start, end);
    }
    
    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        modifySTree(root, index, value);
    }
    
    private long modifySTree(STreeNode node, int index, int value) {
        if (node == null) {
            return 0;
        } else if (node.start == index && node.end == index) {
            node.sum = value;
            return value;
        } else if (index < node.start || index > node.end) {
            return node.sum;
        }
        
        node.sum = modifySTree(node.left, index, value)
                + modifySTree(node.right, index, value);
                
        return node.sum;
    }
    
    private long searchSTree(STreeNode node, int start, int end) {
        if (node == null) {
            return 0;
        }
        
        int mid = (node.start + node.end) / 2;
        
        if (node.start == start && node.end == end) {
            return node.sum;
        }
        
        if (start > mid) {
            return searchSTree(node.right, start, end);
        } else if (end <= mid) {
            return searchSTree(node.left, start, end);
        } else {
            return searchSTree(node.left, start, mid)
                    + searchSTree(node.right, mid + 1, end);
        }
    }
    
    private STreeNode buildSTree(int[] A, int start, int end) {
        int mid = (start + end) / 2;
        STreeNode node = new STreeNode(start, end, 0);
        
        if (A == null || A.length == 0 || start > end) {
            return null;
        } else if (start == end) {
            node.sum = A[start];
            return node;
        }
        
        node.left = buildSTree(A, start, mid);
        node.right = buildSTree(A, mid + 1, end);
        node.sum = node.left.sum + node.right.sum;
        
        return node;
    }
    
    private class STreeNode {
        int start, end;
        long sum;
        STreeNode left, right;
        
        STreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            left = right = null;
        }
    }
}
