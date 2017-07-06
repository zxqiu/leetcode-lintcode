/*

Count of Smaller Number before itself



Give you an integer array (index from 0 to n-1, where n is the size of this array, data value from 0 to 10000) . For each element Ai in the array, count the number of element before this element Ai is smaller than it and return count number array.
Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.

Example

For array [1,2,7,8,5], return [0,1,2,3,2]


解：
这道题思路与Count of Smaller Number几乎一致。
唯一的区别是只在每个数字被查询过之后再插入树中，以保证只查询每个数字之前的数字。

*/


public class Solution {
   /**
     * @param A: An integer array
     * @return: Count the number of element before this element 'ai' is 
     *          smaller than it and return count number array
     */ 
    public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<Integer>();
        }
        
        TreeNode root = buildSegmentTree(0, 10000);
        ArrayList<Integer> ret = new ArrayList<Integer>();
        
        for (int target : A) {
            ret.add(query(root, target));
            insert(root, target);
        }
        
        return ret;
    }
    
    private int query(TreeNode root, int target) {
        if (root == null || target <= root.start) {
            return 0;
        } else if (root.end < target) {
            return root.cnt;
        } else {
            return query(root.left, target) + query(root.right, target);
        }
    }
    
    private void insert(TreeNode root, int target) {
        if (root == null || target < root.start || target > root.end) {
            return;
        }
        
        root.cnt++;
        insert(root.left, target);
        insert(root.right, target);
    }
    
    private TreeNode buildSegmentTree(int start, int end) {
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(start, end);
        
        if (start > end) {
            return null;
        } else if (start == end) {
            return root;
        }
        
        root.left = buildSegmentTree(start, mid);
        root.right = buildSegmentTree(mid + 1, end);
        
        return root;
    }
    
    private class TreeNode {
        int cnt, start, end;
        TreeNode left, right;
        
        public TreeNode(int start, int end) {
            cnt = 0;
            this.start = start;
            this.end = end;
            left = right = null;
        }
    }
}
