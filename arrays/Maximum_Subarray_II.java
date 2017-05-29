/*

Maximum Subarray II

Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

 Notice

The subarray should contain at least one number

Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.

Challenge 
Can you do it in time complexity O(n) ?


解：
这道题理解成将原数组按照某个位置分成左右两半，分别计算左右两边的最大子数组和，使得两个子数组和的和最大。
取这个位置的最优解。

既然要分别找出左右两边的最大和子数组，那么先从左往右计算左边到任意位置i为止的最大子数组和，记为left[i]。
同理计算从右往左的最大子数组和，记为right[i]。
然后找出left[i]+right[i+1]的最大值，0<=i<nums.size()-1

*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        int[] left = new int[nums.size()];
        int[] right = new int[nums.size()];
        int sum, max;
        
        max = Integer.MIN_VALUE;
        sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
            left[i] = max;
        }
        
        max = Integer.MIN_VALUE;
        sum = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            sum += nums.get(i);
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
            right[i] = max;
        }
        
        max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            max = Math.max(max, left[i] + right[i + 1]);
        }
        
        return max;
    }
}


