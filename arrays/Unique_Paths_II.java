/*

Unique Paths II


Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Notice
m and n will be at most 100.


 Example

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]

The total number of unique paths is 2.



解：
该题解法与Unique Path一样，只是多了一个对障碍物的判断。
对于任何一个坐标来说，只要该坐标上有障碍物，那么该坐标的计算结果一定为0。
其他分析与Unique Path一致。

*/



public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] oGrid) {
        if (oGrid == null || oGrid.length == 0) {
            return 0;
        }
        
        int m = oGrid.length;
        int n = oGrid[0].length;
        int[][] dp = new int[m][n];

        // initialize
        dp[0][0] = oGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = oGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }
        
        for (int i = 1; i < n; i++) {
            dp[0][i] = oGrid[0][i] == 1 ? 0 : dp[0][i - 1];
        }
        
        // calculate
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = oGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}
