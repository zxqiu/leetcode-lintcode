/*

Maximal Square

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

Example
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.


解：
Dynanmic Programming

递推公式：
dp[i][j]表示(i-1,j-1)作为右下角的正方形的边长。

对于值为0的点来说，构成的正方形面积为0。
对于值为1的(i-1,j-1)来说，能构成多大的正方形取决于其上，左，左上三个点分别构成的正方形的大小。
1.如果这三个点构成的正方形大小都一样，边长为x，那么加入(i-1,j-1)之后构成的正方形边长为x+1。
2.如果这三个点构成的正方形大小不一样，取其中的最小边长x，并加1。
综合1，2，只需要取左，上，左上三个点构成正方形的最小边长并加1就是(i-1,j-1)构成正方形的边长。

故：
dp[i][j] = min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1]) + 1

初始条件：
左边和上边两条边的值全为0。

参照：
https://discuss.leetcode.com/topic/20801/extremely-simple-java-solution/2

方法一：
时间O(n^2)
空间O(n^2)

*/

public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        
        return max * max;
    }
}


/*

方法二：
由于只需要用到左，上，左上三个点的值，所以可以优化空间复杂度。
保存左上的点，将空间复杂度降为O(n)

*/

public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n + 1];
        
        
        for (int i = 1; i <= m; i++) {
            int last = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if (matrix[i - 1][j - 1] == 1) {
                    dp[j] = Math.min(last, Math.min(dp[j - 1], dp[j])) + 1;
                    max = Math.max(max, dp[j]);
                } else {
                    dp[j] = 0;
                }
                last = tmp;
            }
        }
        
        return max * max;
    }
}
