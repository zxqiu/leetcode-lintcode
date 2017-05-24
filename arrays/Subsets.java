/*

Subsets

Given a set of distinct integers, return all possible subsets.

 Notice

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.

Example
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

Challenge 
Can you do it in both recursively and iteratively?

解法一：
DFS
先对数组排序，保证输入是递增序列。
然后依次添加每一个数之后的所有数。每添加一个数，就向结果队列中加入当前队列。
第归直到每一个数开头的所有subset都被加入结果队列。

*/

class Solution {
 /**
 * @param S: A set of numbers.
 * @return: A list of lists. All valid subsets.
 */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>(), ret);
        
        return ret;
    }
    
    private void helper(int[] nums, int start, ArrayList<Integer> cur, ArrayList<ArrayList<Integer>> ret) {
        ret.add(new ArrayList<Integer>(cur));
        
        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            helper(nums, i + 1, cur, ret);
            cur.remove(cur.size() - 1);
        }
    }
}

