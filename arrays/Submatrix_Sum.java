/*

Submatrix Sum


Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.

Example
Given matrix

[
  [1 ,5 ,7],
  [3 ,7 ,-8],
  [4 ,-8 ,9],
]
return [(1,1), (2,2)]

Challenge 
O(n3) time.


解：
Dynanmic Programming
HashMap

这道题分为两部分：
1.求解从(0,0)到任意位置的矩阵的和。
  1).sum[i][j]表示(0,0)到(i,j)的和。
  2).递推公式：
    当遇到点(i,j)，(0,0)到(i-1,j)的和与(0,0)到(i,j-1)的和，以及matrix[i-1][j-1](当前点的值)相加，多加了一个(0,0)到(i-1,j-1)的和。故：
    sum[i][j]=sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1]+matrix[i-1][j-1]
  3).初始条件：
    最上边和最左边的点全为0。
2.求解任意矩阵的和。
  1).用sum中第l1行的值依次减去第l0行的值，可以得到从l0行最左列开始到任意列的矩阵的和。
  2).如果l0到l1之间的上述矩阵和有任意两个相等，则说明这两个矩阵相减的矩阵和为0，返回对应的左上和右下坐标即可。

*/

public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[2][2];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ret = new int[2][2];
        int[][] sum = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        
        for (int l0 = 0; l0 < m; l0++) {
            for (int l1 = l0 + 1; l1 <= m; l1++) {
                Map<Integer, Integer> diffMap = new HashMap<Integer, Integer>();
                for (int j = 0; j <= n; j++) {
                    int diff = sum[l1][j] - sum[l0][j];
                    
                    if (diffMap.containsKey(diff)) {
                        ret[0][0] = l0;
                        ret[0][1] = diffMap.get(diff);
                        ret[1][0] = l1 - 1;
                        ret[1][1] = j - 1;
                        return ret;
                    } else {
                        diffMap.put(diff, j);
                    }
                }
            }
        }
        
        return ret;
    }
}
