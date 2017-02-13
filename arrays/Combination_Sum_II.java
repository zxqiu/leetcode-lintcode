/*

Combination Sum II


Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

 Notice

All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.


 Example

Given candidate set [10,1,6,7,2,1,5] and target 8,

A solution set is:

[
  [1,7],
  [1,2,5],
  [2,6],
  [1,1,6]
]


解：
DFS

这道题与Combination Sum的区别是去重方式不同。
这道题对于每一个数来说，递归其后面的数组时，同样的数字只能访问一次。这样便可以避免重复的结果。

同样可以采取从上到下或者从下到上的方法来求解。
下面是从上到下求解。

*/


public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        Arrays.sort(num);
        helper(num, 0, target, ret, new ArrayList<Integer>());
        
        return ret;
    }
    
    private void helper(int[] num, int start, int target, List<List<Integer>> ret, List<Integer> path) {
        if (target == 0) {
            ret.add(new ArrayList<Integer>(path));
            return;
        } else if (target < 0) {
            return;
        }
        
        for (int i = start; i < num.length; i++) {
            if (i > start && num[i] == num[i - 1]) {
                continue;
            }
            path.add(num[i]);
            helper(num, i + 1, target - num[i], ret, path);
            path.remove(path.size() - 1);
        }
    }
}