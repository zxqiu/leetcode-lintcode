/*

Combination Sum 

Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.
Notice

    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
    The solution set must not contain duplicate combinations.


 Example

Given candidate set [2,3,6,7] and target 7, a solution set is:

[7]
[2, 2, 3]


解：
DFS

这道题的基本思路是分割问题，由大化小。
把在数组中查找目标数字的问题，缩小成在子数组中查找某一个小于目标数字的数字的问题。
当从数组中读出一个数时，去从这个数开始到数组结束为止的子数组中查找目标数减去该数的数字。
逐次递归直到目标数变成0，则表示找到了一个可行序列，如果目标数变得小于0，则表示该序列不可行。
为了方便查重，先将数组排序再进行查找。


可以选择自底向上的后序遍历，也可以选择自上向下的先序遍历。

方法一：
自底向上
先找到整个序列，然后从下向上添加其中的每个数字。

*/


public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null) {
            return new ArrayList<List<Integer>>();
        }
        
        Arrays.sort(candidates);
        
        return helper(candidates, 0, target);
    }
    
    private List<List<Integer>> helper(int[] candidates, int start, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (target < 0) {
            return ret;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int cand = candidates[i];
            
            if (cand == target) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(cand);
                ret.add(list);
                break;
            }
            
            List<List<Integer>> lists = helper(candidates, i, target - cand);
            for (List<Integer> list : lists) {
                list.add(0, cand);
                ret.add(list);
            }
        }
        
        return ret;
    }
}



/*

方法二：
从上向下

传递一个ArrayList用来保存当前路径，如果当前路径最后可以获得目标数为0，则表示该路径可行，加入结果列表中。
如果最后获得目标数小于0，则抛弃掉。

*/


public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        if (candidates == null) {
            return ret;
        }
        
        Arrays.sort(candidates);
        
        helper(candidates, 0, target, new ArrayList<Integer>(), ret);
        return ret;
    }
    
    private void helper(int[] candidates, int start, int target, List<Integer> path, List<List<Integer>> ret) {
        if (target == 0) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            path.add(candidates[i]);
            helper(candidates, i, target - candidates[i], path, ret);
            path.remove(path.size() - 1);
        }
    }
}
