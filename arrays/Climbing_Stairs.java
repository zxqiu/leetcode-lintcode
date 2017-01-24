/*

Climbing Stairs


You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


 Example

Given an example n=3 , 1+1+1=2+1=1+2=3

return 3


解：
dynanmic programing

递推公式：
dp[i]表示上到第i级台阶有几种上法。
由于每次最多上两级，故第i级台阶可以从i-1或者i-2到达。
从i-1到达时，共有dp[i - 1]种上法，因为从i-1到i并不会增加可能的上法数量。同理从i-2到达时有dp[i - 2]种上法。
dp[i] = dp[i - 1] + dp[i - 2]

初始条件：
由于计算i需要i-1和i-2的结果，所以至少需要有dp[0]和dp[1]作为初始条件。
第0级和第1级台阶都有1中上法，故：
dp[0] = dp[1] = 1;

*/

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        
        return dp[n];
    }
}
