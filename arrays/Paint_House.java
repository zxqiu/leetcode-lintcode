/*

Paint House

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Notice

All costs are positive integers.

Example
Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10


解：
Dynanmic Programming

递推公式：
dp[i][j]表示第i栋房子涂成j颜色时，包括前面所有房子在内的最小花费。
i涂成j颜色时，只需要直到到i-1为止，除了j以外的两个颜色的最小花费。
取其中小的那个与i涂成j的花费相加，就得到了dp[i][j]。

故:
dp[i][j] = min(dp[i][p], dp[i][q]) + costs[i-1][j], j=0~2,p=0~2,q=0~2, j!=p,j!=q,p!=q


初始条件：
涂0栋房子时，任何颜色都不需要花钱。
故dp[0][0~2]=0

*/

public class Solution {
    /**
     * @param costs n x 3 cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int m = costs.length;
        int[][] dp = new int[m + 1][3];
        
        for (int i = 1; i <= m; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i - 1][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i - 1][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i - 1][2];
        }
        
        return Math.min(dp[m][0], Math.min(dp[m][1], dp[m][2]));
    }
}
