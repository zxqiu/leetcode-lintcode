/*

Unique Paths


A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Notice

m and n will be at most 100.


解：
dynamic programming
与climbing stairs类似。

递推公式：
dp[i][j]表示矩阵坐标(i, j)有多少条unique paths可以到达。每个坐标可以从上方或者左边到达。所以每个坐标的unique paths都为上方坐标和左边坐标的unique paths之和。
由于从上方或者左边到达一个坐标时，并不会增加新的可行路径，所以直接求和即可。
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

初始条件：
由于每个点都需要上方和左边方坐标的计算结果，所以需要保证最上方一行和最左边一列的坐标计算值被初始化。
最上方和最左边的任意一个坐标都只可能有一种方式到达，即从左上角的点一路向右，或者一路向下。故全部初始化为1。
这里为了书写方便，将整个矩阵初始化为1。由于除了最上方一行和最左边一列以外的所有值都会被覆盖，所以不影响计算结果。

最后取右下角坐标的计算结果。

*/

public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        for (int[] row : dp) {
            Arrays.fill(row, 1);
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}
