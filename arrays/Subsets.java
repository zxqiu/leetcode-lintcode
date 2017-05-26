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


/*

解法二：
<<<<<<< HEAD
非第归DFS
基本思路是把每一个数分别加入每一个已经存在于结果队列中的子队列的末尾，构成新的子队列。
=======
BFS
最外层循环表示当前计算的队列长度。从长度为0的队列开始计算，一直到最大的subset长度，也就是输入数组长度。
第二层循环遍历当前的结果队列，对其中长度与当前计算的队列长度一致的子队列，依次加入其最后一个数字后面的每一个数字，构成新的队列放入结果队列中。
>>>>>>> f5be7e2091e3526a7a6af69bc6bd2647dc9dd48c

比如输入1，2，3，
首先加入空集：[]
第一次循环对空集加入1：[],[1]
第二次循环对每个子集加入2：[],[1],[2],[1,2]
第三次循化加入3：[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]

*/
class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ret.add(new ArrayList<Integer>());
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            int size = ret.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> tmp = new ArrayList<Integer>(ret.get(j));
                tmp.add(nums[i]);
                ret.add(tmp);
            }
        }
        
        return ret;
    }
}

