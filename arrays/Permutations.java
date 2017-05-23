/*

Permutations

Given a list of numbers, return all possible permutations.

 Notice

You can assume that there is no duplicate numbers in the list.

Have you met this question in a real interview? Yes

Example
For nums = [1,2,3], the permutations are:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Challenge 
Do it without recursion.


解法一：
DFS
使用递归自顶而下扫描。每次将一个没有出现在当前序列中的数字放入当前序列，直到所有数都被放进去，则将当前序列放入结果序列。
也可以自底向上扫描。

*/

public class Solution {
 /*
 * @param : A list of integers.
 * @return: A list of permutations.
 */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        helper(nums, new HashSet<Integer>(), new ArrayList<Integer>(), ret);
        
        return ret;
    }
    
    private void helper(int[] nums, HashSet<Integer> used, List<Integer> cur, List<List<Integer>> ret) {
        if (nums.length == used.size()) {
            ret.add(new ArrayList<Integer>(cur));
        }
        
        for (int i : nums) {
            if (used.contains(i)) {
                continue;
            }
            
            used.add(i);
            cur.add(i);
            helper(nums, used, cur, ret);
            used.remove(i);
            cur.remove(cur.size() - 1);
        }
    }
};

/*

解法二：
使用iteration而非recursion

原理是先把每个数字作为头的list放进结果list中，然后每次循环对每个子list增加一个数字，直到所有数字都被加进去。


*/

public class Solution {
    /*
     * @param : A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums.length == 0) {
            ret.add(new ArrayList<Integer>());
            return ret;
        }
        
        // initiate the result list
        for (int num : nums) {
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.add(num);
            ret.add(tmp);
        }
        
        for (int i = 0; i < nums.length - 1; i++) {
            List<List<Integer>> tmpRet = new ArrayList<List<Integer>>();
            // scan every list in result list for each number
            for (int num : nums) {
                for (List<Integer> list : ret) {
                    if (list.contains(num)) {
                        // only add new number to each list
                        continue;
                    }
                    List<Integer> tmp = new ArrayList<Integer>(list);
                    tmp.add(num);
                    tmpRet.add(tmp);
                }
            }
            ret = tmpRet;
        }
        
        return ret;
    }
};
