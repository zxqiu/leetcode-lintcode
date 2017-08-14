/*

House Robber II


After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
Notice

This is an extension of House Robber.


Example

nums = [3,6,4], return 6



解：
Dynanmic Programming
由于头尾相连，有两种独立可能：
1.抢头不抢尾
2.抢尾不抢头

只需要分别DP计算上面两种可能性，取其中大的就行。

递推公式：
不论哪种情况，递推公式都与House Robber一致。
dp[i] = max(dp[i - 2], dp[i - 3]) + nums[i]

只不过在抢头不抢尾的情况下，由于len-1一定不抢，故最后取的并不是dp[len - 1]和dp[len - 2]中较大的，而是dp[len - 2]和dp[len - 3]中较大的。


初始条件：
对于抢头不抢尾，只需要在House Robber的基础上把dp[1]赋0，这是由于第一个一定抢，第二个一定不抢。
对于抢尾不抢头，只需要把dp[0]赋0，因为第一个一定不抢。


参照House Robber:
https://github.com/zxqiu/leetcode-lintcode/blob/master/arrays/House_Robber.java

*/

public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int max = 0;
        int len = nums.length;
        int[] dp0 = new int[len];
        int[] dp1 = new int[len];
        
        if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        // rob idx 0
        dp0[0] = nums[0];
        dp0[1] = 0;
        dp0[2] = nums[0] + nums[2];
        // not rob idx 0
        dp1[0] = 0;
        dp1[1] = nums[1];
        dp1[2] = nums[2];
        
        for (int i = 3; i < len; i++) {
            dp0[i] = Math.max(dp0[i - 2], dp0[i - 3]) + nums[i];
            dp1[i] = Math.max(dp1[i - 2], dp1[i - 3]) + nums[i];
        }
        
        return Math.max(Math.max(dp0[len - 2], dp0[len - 3]), Math.max(dp1[len - 1], dp1[len - 2]));
    }
}
