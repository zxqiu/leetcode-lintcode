/*

Longest Incresing Subsequence

Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Clarification
What's the definition of longest increasing subsequence?

The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Example
For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
For [4, 2, 4, 5, 3, 7], the LIS is [2, 4, 5, 7], return 4

Challenge 
Time complexity O(n^2) or O(nlogn)


解：
dynanmic programing

dp[i]表示包含nums[i]在内的最长递增子序列。

递推公式：
对于nums[i]，如果在其左边有任何一个小于它的数，就可以构成一个长度大于1的递增子序列。
所有需要遍历之前的所有数，若nums[j]小于nums[i]，就在之前的子序列长度dp[j]上加1作为备选的dp[i]。取其中最大的一个作为dp[i]。
故：
dp[i] = dp[j] + 1, j = 0 ~ i-1 && nums[i] > nums[j]

最后取dp数组中的最大值返回。

*/

public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        dp[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}

