/*

K Sum

Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.


解：
dynanmic programming

这道题需要用到3维dp。

dp[i][j][m]表示前i个数字中选取j个，其和为m的解的数量。

递推公式：
当遇到第i个数时，可以选择该数，也可以不选择：
1.当不选择该数字时，可行解的数量应当为从前i-1个数中选择相同数量的数字，并求相同的和时的解的数量；
2.当选择概数字时，可行解的数量应当为从前i-1个数中少选一个数并求target-A[i]时的解的数量。此时要求target-A[i-1]必须大于等于0。

两种情况总是同时存在，故：
dp[i][j][m] = dp[i-1][j][m]
dp[i][j][m] += dp[i-1][j-1][m-A[i-1]], m-A[i-1] >= 0

初始条件：
递推公式需要用到正上方和左上方的值，故需要初始化第一行和第一列。
当从前i个数中选取0个数求和为0时，可行解为1，求和大于0时可行解为0。
故：
dp[i][0][0] = 1;
dp[i][0][m] = 0, m > 0;

*/

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        int[][][] dp = new int[A.length + 1][k + 1][target + 1];
        
        for (int i = 0; i <= A.length; i++) {
            dp[i][0][0] = 1;
        }
        
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int m = j; m <= target; m++) {
                    dp[i][j][m] = dp[i - 1][j][m];
                    if (m - A[i - 1] >= 0) {
                        dp[i][j][m] += dp[i - 1][j - 1][m - A[i - 1]];
                    }
                }
            }
        }
        
        return dp[A.length][k][target];
    }
}


/*

由于求解dp[i]只需要用到dp[i-1]，故其实只需要存储dp[j][m]即可。


*/

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        int[][] dp = new int[k + 1][target + 1];
        
        dp[0][0] = 1;
        
        for (int i = 1; i <= A.length; i++) {
            for (int j = Math.min(k, i); j >= 1; j--) {
                for (int m = j; m <= target; m++) {
                    if (m - A[i - 1] >= 0) {
                        dp[j][m] += dp[j - 1][m - A[i - 1]];
                    }
                }
            }
        }
        
        return dp[k][target];
    }
}

