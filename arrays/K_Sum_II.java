/*

K Sum II


Given n unique integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.

Example
Given [1,2,3,4], k = 2, target = 5. Return:

[
  [1,4],
  [2,3]
]


解：
DFS
深度优先搜索遍历所有数字，找出所有解即可。

k等于0时无论target如何都应当返回。
可以进一步优化，先对A进行递增排序，这样当target-A[i]小于0时就可以打断循环，因为后面的值将会更小。

*/

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return a list of lists of integer 
     */ 
    public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        helper(A, 0, k, target, ret, new ArrayList<Integer>());
        
        return ret;
    }
    
    private void helper(int[] A, int start, int k, int target, ArrayList<ArrayList<Integer>> ret, ArrayList<Integer> path) {
        if (k == 0) {
            if (target == 0) {
                ret.add(new ArrayList<Integer>(path));
            }
            return;
        }
        
        for (int i = start; i < A.length; i++) {
            path.add(A[i]);
            helper(A, i + 1, k - 1, target - A[i], ret, path);
            path.remove(path.size() - 1);
        }
    }
}
