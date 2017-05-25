/*

Subsets II

Given a list of numbers that may has duplicate numbers, return all possible subsets

 Notice

Each element in a subset must be in non-descending order.
The ordering between two subsets is free.
The solution set must not contain duplicate subsets.

Example
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

Challenge 
Can you do it in both recursively and iteratively?


解法一：
DFS
跟Subsets类似，只需要把每次的当前队列都加入结果都列即可。

*/

class Solution {
 /**
 * @param nums: A set of numbers.
 * @return: A list of lists. All valid subsets.
 */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>(), ret);
        
        return ret;
    }
    
    private void helper(int[] nums, int start, ArrayList<Integer> cur, ArrayList<ArrayList<Integer>> ret) {
        ret.add(new ArrayList<Integer>(cur));
        
        int lastIdx = -1;
        for (int i = start; i < nums.length; i++) {
            if (lastIdx >= 0 && nums[i] == nums[lastIdx]) {
                continue;
            }
            
            lastIdx = i;
            cur.add(nums[i]);
            helper(nums, i + 1, cur, ret);
            cur.remove(cur.size() - 1);
        }
    }
}

/*

解法二：
BFS非第归

跟Subset循环方法类似，不同的是如果需要添加的数字和前一个数一样，那么这个数只能加入到每一个上一轮生成的队列中。
所以每次需要记录上一轮生成的队列的起始位置。该位置其实是上一轮开始前的结果数组大小。


*/


class Solution {
    /**
    * @param S: A set of numbers.
    * @return: A list of lists. All valid subsets.
    */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        int start, size = 1;
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ret.add(new ArrayList<Integer>());
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            start = (i > 0 && nums[i] == nums[i - 1]) ? size : 0;
            size = ret.size();

            for (int j = start; j < size; j++) {
                ArrayList<Integer> tmp = new ArrayList<Integer>(ret.get(j));
                tmp.add(nums[i]);
                ret.add(tmp);
            }
        }

        return ret;
    }
}
