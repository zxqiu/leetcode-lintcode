/*

Permutations II

Given a list of numbers with duplicate number in it. Find all unique permutations.


Example
For numbers [1,2,2] the unique permutations are:

[
  [1,2,2],
  [2,1,2],
  [2,2,1]
]

Challenge 
Using recursion to do it is acceptable. If you can do it without recursion, that would be great!


解：
DFS
跟Permutation方法类似，不同的是需要对数组排序，然后在每一层的循环中检查是否有连续两个相同的数被加入队列中。

*/

public class Solution {
 /*
 * @param :  A list of integers
 * @return: A list of unique permutations
 */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        
        Arrays.sort(nums);
        helper(nums, new int[nums.length], new ArrayList<Integer>(), ret);
        
        return ret;
    }
    
    private void helper(int[] nums, int[] used, List<Integer> cur, List<List<Integer>> ret) {
        if (cur.size() == nums.length) {
            ret.add(new ArrayList<Integer>(cur));
            return;
        }
        
        int lastIdx = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1 || (lastIdx >= 0 && nums[i] == nums[lastIdx])) {
                continue;
            }
            
            lastIdx = i;
            used[i] = 1;
            cur.add(nums[i]);
            helper(nums, used, cur, ret);
            
            used[i] = 0;
            cur.remove(cur.size() - 1);
        }
    }
};
