/*

Combinations


Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.


 Example
 
For example,
If n = 4 and k = 2, a solution is:
[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]


解：
DFS

用path记录DFS的路径。每遇到一个数字就将这个数字放进path，然后从下一个数字开始下一次递归。
当path中已经有k个数字，这时将path存入结果list。

*/



public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        helper(n, 1, k, ret, new ArrayList<Integer>());
        
        return ret;
    }
    
    private void helper(int n, int start, int k, List<List<Integer>> ret, List<Integer> path) {
        if (path.size() == k) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            path.add(i);
            helper(n, i + 1, k, ret, path);
            path.remove(path.size() - 1);
        }
    }
}