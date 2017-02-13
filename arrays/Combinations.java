/*

Combinations


Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.


 Example
 
For example,
If n = 4 and k = 2, a solution is:
[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]


解：
DFS

方法一：

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


/*

方法二：

DFS思路基本一致，换个写法。
自底向上生成结果数组。
需要直到当前处于第几层递归，当递归层数与k一致时，对每一个数生成list。每一层拿到下一层的返回值时，将当前值加进每一个list中。直到返回到顶层方法。

*/


public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
		return combineHelper(n, 1, k, 1);
    }
    
    private List<List<Integer>> combineHelper(int n, int start, int k, int lvl) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        for (int i = start; i <= n; i++) {
            if (lvl == k) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                ret.add(list);
            } else {
                List<List<Integer>> list = combineHelper(n, i + 1, k, lvl + 1);
                for (List<Integer> l : list) {
                    l.add(0, i);
                    ret.add(l);
                }
            }
        }
        
        return ret;
    }
}