/*

Backpack VI


Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
Notice

A number in the array can be used multiple times in the combination.
Different orders are counted as different combinations.
Have you met this question in a real interview?
Example

Given nums = [1, 2, 4], target = 4

The possible combination ways are:
[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]

return 6


解：
Dynanmic Programming

递推公式：
dp[i]表示和为i时有几种组合。

若一个数字num+j=i，那么dp[i]就应当为dp[j]。
如果有组num和j满足该条件，dp[i]应当为所有dp[j]的和。

故：
dp[i] = sum(dp[j]), j + num == i, num <= i


初始条件：
由于可能用到j小于i的所有dp[j]，故计算dp[1]时初始化dp[0]即可。
dp[0]有一种数字组合可能，即空集。
故dp[0]=1。


*/

public class Solution {
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackVI(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        
        return dp[target];
    }
}
