/*

Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Notice

You can only move either down or right at any point in time.


解：
dynanmic programming

dp[i][j]表示从左上角到grid[i - 1][j - 1]的最小path sum。
由于只能向右或者向下移动，所以每个坐标的最小path sum一定是上方或者左边的坐标的最小path sum加上当前坐标的值。

则：dp[i][j] = min(dp[i - 1][j], dp[j - 1][i]) + grid[i - 1][j - 1]

注意初始条件，应当计算grid中第一行和第一列的值。

*/

public class Solution {
    /**
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int[][] dp = new int[grid.length + 1][grid[0].length + 1];
        
        // init
        for (int i = 1; i <= grid.length; i++) {
            dp[i][1] = dp[i - 1][1] + grid[i - 1][0];
        }
        
        for (int j = 1; j <= grid[0].length; j++) {
            dp[1][j] = dp[1][j - 1] + grid[0][j - 1];
        }
        
        // calc
        for (int i = 2; i <= grid.length; i++) {
            for (int j = 2; j <= grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        
        return dp[grid.length][grid[0].length];
    }
}
