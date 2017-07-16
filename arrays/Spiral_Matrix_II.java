/*

Spiral Matrix II


Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

 Notice

Example
Given n = 3,

You should return the following matrix:

[
  [ 1, 2, 3 ],
  [ 8, 9, 4 ],
  [ 7, 6, 5 ]
]


解：
转圈圈写数字。
每次可以同时写四个边。
重点在计算每个边的起点和要写的值。

*/


public class Solution {
    /**
     * @param n an integer
     * @return a square matrix
     */
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int val = 1;
        
        for (int i = 0; i <= (n - 1) / 2; i++) {
            int len = n - 2 * i - 1;
            int[][] idx = {{i, i, val},
                            {i, i + len, val + len},
                            {i + len, i + len, val + 2 * len},
                            {i + len, i, val + 3 * len}};
            int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < 4; k++) {
                    ret[idx[k][0]][idx[k][1]] = idx[k][2]++;
                    idx[k][0] += direct[k][0];
                    idx[k][1] += direct[k][1];
                }
            }
            
            if (len == 0) {
                ret[i][i] = val;
            }
            
            val += 4 * len;
        }
        
        return ret;
    }
}
