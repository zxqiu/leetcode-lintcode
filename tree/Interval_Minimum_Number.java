/*

Interval Minimum Number

Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Example
For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]

Challenge 
O(logN) time for each query


解：
Segment Tree
先根据A构建一个Segment Tree，每个节点包含一个min值，保存区间内最小值。
具体参照Segment Tree Build。

搜索区间树，参照Segment Tree Query。

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
    public ArrayList<Integer> intervalMinNumber(int[] A, 
                                                ArrayList<Interval> queries) {
        STreeNode root = buildSTree(A, 0, A.length - 1);
        ArrayList<Integer> ret = new ArrayList<Integer>();
        
        for (Interval interval : queries) {
            ret.add(searchSTree(root, interval.start, interval.end));
        }
        
        return ret;
    }
    
    private int searchSTree(STreeNode root, int start, int end) {
        int mid = (root.start + root.end) / 2;
        
        if (root.start == start && root.end == end) {
            return root.min;
        }
        
        if (start > mid) {
            return searchSTree(root.right, start, end);
        } else if (end <= mid) {
            return searchSTree(root.left, start, end);
        } else {
            return Math.min(searchSTree(root.left, start, mid),
                            searchSTree(root.right, mid + 1, end));
        }
    }
    
    private STreeNode buildSTree(int[] A, int start, int end) {
        int mid = (start + end) / 2;
        STreeNode root = new STreeNode(start, end, 0);
        
        if (start == end) {
            root.min = A[start];
            return root;
        }
        
        root.left = buildSTree(A, start, mid);
        root.right = buildSTree(A, mid + 1, end);
        root.min = Math.min(root.left.min, root.right.min);
        
        return root;
    }
    
    private class STreeNode {
        int start, end, min;
        STreeNode left, right;
        
        STreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            left = right = null;
            
            
        }
    }
}
