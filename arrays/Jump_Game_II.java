/*

Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.


 Example

Given array A = [2,3,1,1,4]
The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)


解：
dynanmic programming

递推公式：
dp[i]表示跳到当前点最少需要多少次跳跃。
dp[i] = min(dp[j] + 1, j = 0 ~ i - 1， A[j] + j >= i)
即dp[i]应当为从0到i-1中能够跳到i的点中需要最少的跳跃次数。

初始条件：
跳到第一个点需要0次跳跃，即dp[0] = 0。

*/


public class Solution {
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        int[] dp = new int[A.length];
        dp[0] = 0;
        
        for (int i = 1; i < A.length; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (A[j] + j >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[A.length - 1];
    }
}
