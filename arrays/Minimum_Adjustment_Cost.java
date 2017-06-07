/*

Minimum Adjustment Cost


Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|
Notice

You can assume each number in the array is a positive integer and not greater than 100.

Example

Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.


解：
dynanmic programming

dp[i][j]表示将A[i-1]调整到j时，所需要的最小成本。这个cost包含之前所有数的调整成本。

递推公式：
计算dp[i][j]需要两部分值：
1.A[i-1]与j的差；
2.A[i-2]调整到j-target到j+target的区间内的最小成本。
两部分求和就可以得到dp[i][j]。
故：
dp[i][j] = abs(A[i-1] - j) + min(dp[i-1][j-target+0], dp[i-1][j-target+1], ..., dp[i-1][j+target]), j-target >= 1 && j+target <=100


初始条件：
dp[0][j]应当为0。
这样可以保证计算第一个数时，也即计算dp[1][j]时，最小成本就是abs(A[0]-j)。

*/

public class Solution {
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        int[][] dp = new int[A.size() + 1][101];
        int min = 0;
        
        for (int i = 1; i <= A.size(); i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j <= 100; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k <= target; k++) {
                    if (j + k <= 100) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + k]);
                    }
                    if (j - k > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k]);
                    }
                }
                
                dp[i][j] += Math.abs(A.get(i - 1) - j);
                min = Math.min(dp[i][j], min);
            }
        }
        
        return min;
    }
}
