/*

Count of Smaller Number


Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.

 Notice

We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

Challenge 
Could you use three ways to do it.

Just loop
Sort and binary search
Build Segment Tree and Search.


解：
Segment Tree

构建一个从0到10000的区间树，然后把A中所有值插进去。
之后进行统计。

对于一个节点，如果目标数小于或者等于其start，说明该区间内没有小于该数的数字。
如果目标数大于其end，说明该区间内所有数字都小于该数。
否则，应当在该节点的孩子节点中继续查找。

*/

public class Solution {
   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        if (A == null || queries == null) {
            return new ArrayList<Integer>();
        }
        
        TreeNode root = buildSegmentTree(0, 10000);
        ArrayList<Integer> ret = new ArrayList<Integer>();
        
        for (int target : A) {
            insert(root, target);
        }
        
        for (int target : queries) {
            ret.add(query(root, target));
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
