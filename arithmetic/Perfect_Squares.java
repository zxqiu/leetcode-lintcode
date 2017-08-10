/*

Perfect Squares


Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example
Given n = 12, return 3 because 12 = 4 + 4 + 4
Given n = 13, return 2 because 13 = 4 + 9


解：
Dynanmic Programming
这道题有一个数学解法，任何数都可以由四个或四个以下数的平方和组成。
由于没有通用性，这里不做讨论。

方法一：

递推公式：
dp[k]表示k最少可以由几个数的平方和构成。
假设：
k = i + j * j
那么：
dp[k] = dp[i] + 1
由于k可以由多组不同的i和j组成，应当取其中的最小值。

故：
dp[i + j * j] = min(dp[i + j * j], dp[i] + 1)


初始条件：
假设i=0，那么dp[j * j] = dp[0] + 1 = 1。
故dp[0]=0。
由于要取最小值，故其他dp[i]全为最大整数。

*/

public class Solution {
    /**
     * @param n a positive integer
     * @return an integer
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; i + j * j <= n; j++) {
                dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
            }
        }
        
        return dp[n];
    }
}

/*

方法二：
递推公式：
dp[k]表示k可以由最少几个数的平方和组成，与方法一一样。
如果已知dp[i - j * j]，那么：
dp[i] = dp[i - j * j] + 1

对于每一个i，应当计算所有可能的j，取其中最小的。
故:
dp[i] = min(dp[i], dp[i - j * j] + 1)，j * j <= i


初始条件：
如果i-j*j=0，那么i=j*j，此时dp[i]=dp[0]+1=1。
故dp[0]=0。
由于要求最小值，故其他dp[i]初始为最大整数。

*/


public class Solution {
    /**
     * @param n a positive integer
     * @return an integer
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }
}
