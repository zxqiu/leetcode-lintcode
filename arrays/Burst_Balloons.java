/*

Burst Balloons



Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.
- You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
- 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100


Example

Given [4, 1, 5, 10]
Return 270

nums = [4, 1, 5, 10] burst 1, get coins 4 * 1 * 5 = 20
nums = [4, 5, 10]    burst 5, get coins 4 * 5 * 10 = 200 
nums = [4, 10]       burst 4, get coins 1 * 4 * 10 = 40
nums = [10]          burst 10, get coins 1 * 10 * 1 = 10

Total coins 20 + 200 + 40 + 10 = 270



解：
Dynanmic Programming

left和right规定从第left个数开始，到第right个数为止的区间。
dp[left][right]表示left到right之间的气球的最大得分。

递推公式：
某一个区间的最大得分，可以为该区间内任何一个气球作为最后一个爆炸的气球时的得分。
当第m个气球最后一个爆炸时，m左侧和右侧的所有气球应当先爆炸，然后m爆炸时左右两侧的气球为left-1和right+1。
故：
dp[left][right] = max(dp[left][right], burst(left - 1, right + 1, m) + dp[left][m - 1] + dp[m + 1][right]), left <= m <= right

如果按照一般二维dp的循环方法，直接遍历整个dp数组，那么会发现递推公式中使用了未计算的结果。
所以这道题实际上是三维dp问题。
dp[len][left][right]表示长度为len的从left到right的区间的最大得分，由于len信息可以用left到right的区间长度来保存，故省略len这个维度。
计算时，应当从len为1计算到nums长度为止。这样再计算某一个len时，长度小于len的区间已经全部计算过，可以直接使用。

为什么不可以计算任意气球最先爆炸时某区间的最高得分？
如果m先爆炸，那么dp[left][m-1]的值将不再准确。原因是该区间外右边第一个气球的值变了。
同理dp[m+1][right]也将不准确。

初始条件：
若left>right，dp[left][right]为0。
故，若left==right，dp[left][right]为left爆炸得分。

*/


public class Solution {
    /**
     * @param nums a list of integer
     * @return an integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        
        for (int len = 1; len <= nums.length; len++) {
            for (int left = 1; left <= nums.length + 1 - len; left++) {
                int right = left + len - 1;
                for (int m = left; m <= right; m++) {
                    dp[left][right] = Math.max(dp[left][right], dp[left][m - 1] + dp[m + 1][right] + burst(nums, left - 2, right, m - 1));
                }
            }
        }
        
        return dp[1][nums.length];
    }
    
    private int burst(int[] nums, int left, int right, int idx) {
        int ret = (left < 0) ? 1 : nums[left];
        ret *= nums[idx];
        ret *= (right >= nums.length) ? 1 : nums[right];
        
        return ret;
    }
}
