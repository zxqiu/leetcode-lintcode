/*

Paint House II



There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
Notice

All costs are positive integers.
Have you met this question in a real interview?
Example

Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
Challenge

Could you solve it in O(nk)?


解：
Dynanmic Programming

本质与Paint House一样，还是DP。参照：
https://github.com/zxqiu/leetcode-lintcode/blob/master/arrays/Paint_House.java

每个房子的计算只需要知道上一个房子的最小值和次小值即可。

对于每栋房子的每种颜色而言，若该颜色与上一栋房子的最小值对应的颜色不一样，那么上一栋房子就可以涂成最小值对应的颜色。
否则，如果该颜色与上一栋房子最小值对应的颜色一样，那必定和次小值对应的颜色不一样，上一栋房子涂成次小值对应的颜色即可。

对每栋房子遍历其所有颜色，找出最小值和次小值，再计算下一栋房子。
最后返回最后一栋房子的最小值。

*/


public class Solution {
    /**
     * @param costs n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int m = costs.length;
        int n = costs[0].length;
        int key0 = -1;
        int val0 = 0;
        int val1 = 0;
        
        for (int i = 0; i < m; i++) {
            int tmpKey0 = -1;
            int tmpVal0 = Integer.MAX_VALUE;
            int tmpVal1 = Integer.MAX_VALUE;
            
            for (int j = 0; j < n; j++) {
                int cost = j == key0 ? val1 : val0;
                cost += costs[i][j];
                
                if (cost < tmpVal0) {
                    tmpVal1 = tmpVal0;
                    
                    tmpKey0 = j;
                    tmpVal0 = cost;
                } else if (cost < tmpVal1) {
                    tmpVal1 = cost;
                }
            }
            
            key0 = tmpKey0;
            val0 = tmpVal0;
            val1 = tmpVal1;
        }
        
        return val0;
    }
}
