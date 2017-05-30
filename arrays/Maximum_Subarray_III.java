/*

Maximum Subarray III

Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

 Notice

The subarray should contain at least one number

Example
Given [-1,4,-2,3,-2,3], k=2, return 8


解：
dynanmic programming
双重DP
local[i][j]表示前j个数字中i个子数组最大和，包含第j个数字。
global[i][j]表示前j个数字中i个子数组最大和，可以不包含第j个数字。

递推公式：
local[i][j]
第j个数字可以自己组成一个子数组，此时之前的最后一个子数组包含j-i与否都可以:
local[i][j] = global[i - 1][j - 1] + nums[j - 1]
也可以加入上一个子数组，此时之前的最后一个子数组必须包含j-1，且前j-1个数已经包含i个子数组:
local[i][j] = local[i][j - 1] + nums[j - 1]
取大的那个：
local[i][j] = max(global[i - 1][j - 1], local[i][j - 1]) + nums[j - 1]

global[i][j]
第j个数字可以不被包含在最后一个子数组中：
global[i][j] = global[i][j - 1];
也可以被包含在最后一个子数组中：
global[i][j] = local[i][j];
取大的那个：
global[i][j] = max(global[i][j - 1], local[i][j])

计算时由于要组成i个非空子数组，至少由i个数字，故j>=i。
故当j==i时，local[i][j - 1]和global[i][j - 1]都不成立。
而且此时一定时每个数字都被包含，故：
local[i][j] = local[i - 1][j - 1] + nums[j - 1], i == j
global[i][j] = local[i][j], i == j


*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        int[][] local = new int[k + 1][nums.length + 1];
        int[][] global = new int[k + 1][nums.length + 1];
        
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= nums.length; j++) {
                if (i == j) {
                    local[i][j] = local[i - 1][j - 1] + nums[j - 1];
                    global[i][j] = local[i][j];
                } else {
                    local[i][j] = Math.max(global[i - 1][j - 1], local[i][j - 1]) + nums[j - 1];
                    global[i][j] = Math.max(global[i][j - 1], local[i][j]);
                }
            }
        }
        
        return global[k][nums.length];
    }
}

