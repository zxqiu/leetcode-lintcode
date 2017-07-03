/*

Interval Sum

Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.

 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Example
For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]

Challenge 
O(logN) time for each query


解：
Segment Tree
先跟据A生成区间树，其中包含区间的和。
生成区间树参照Segment Tree Build。

然后参照Segment Tree Query搜索区间树。
对于每个节点，如果start和end横跨该节点的mid，将其左右两个子节点的和加和并返回。

*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
public class Solution {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Long> intervalSum(int[] A, 
                                       ArrayList<Interval> queries) {
        STreeNode root = buildSTree(A, 0, A.length - 1);
        ArrayList<Long> ret = new ArrayList<Long>();
        
        for (Interval interval : queries) {
            ret.add(searchSTree(root, interval.start, interval.end));
        }
        
        return ret;
    }
    
    private long searchSTree(STreeNode root, int start, int end) {
        int mid = (root.start + root.end) / 2;
        
        if (root.start == start && root.end == end) {
            return root.sum;
        }
        
        if (start > mid) {
            return searchSTree(root.right, start, end);
        } else if (end <= mid) {
            return searchSTree(root.left, start, end);
        } else {
            return searchSTree(root.left, start, mid)
                    + searchSTree(root.right, mid + 1, end);
        }
    }
    
    private STreeNode buildSTree(int[] A, int start, int end) {
        int mid = (start + end) / 2;
        STreeNode root = new STreeNode(start, end, 0);
        
        if (start == end) {
            root.sum = A[start];
            return root;
        }
        
        root.left = buildSTree(A, start, mid);
        root.right = buildSTree(A, mid + 1, end);
        root.sum = root.left.sum + root.right.sum;
        
        return root;
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
