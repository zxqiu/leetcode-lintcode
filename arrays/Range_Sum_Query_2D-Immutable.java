/*

Range Sum Query 2D - Immutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Notice

You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.


Example
Given matrix =

[
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]
sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12


解：
Dynanmic Programming

这道题强调了会有很多sumRegion的调用，就是在暗示不要每一个矩阵的加一次。

先利用dp求出从(0,0)到每一个点的和，然后做矩阵加减法就可以得到任意想要的矩阵和。

递推公式：
sum[i][j]表示从左上角到(i,j)的矩阵和。
对于每一个点matrix[i][j]，其正上方的矩阵加上其正左方的矩阵，减去其左上方的矩阵，再加上这个点的值就是需要的矩阵和。

比如：
1 2
3 4
计算从坐上4这个点的矩阵和时，用矩阵1+2加上矩阵1+3，减去矩阵1，再加上4这个值就可以得到1+2+3+4。

故：
sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + matrix[i-1][j-1]

初始条件：
需要用到上方和左边的值，故初始化第一行和第一列。
由于第一行和第一列都表示没有任何值，故全为0。


计算完sum矩阵之后，每当遇到一个要求的矩阵(r1,c1)到(r2,c2)：
用sum[r2+1][c2+1]减去其上方矩阵和以及其左边矩阵和，再加上其左上方矩阵和，即：
ret = sum[r2+1][c2+1]-sum[r1][c2+1]-sum[r2+1][c1]+sum[r1][c1]

*/

public class NumMatrix {
    int[][] sum;
    
    public NumMatrix(int[][] matrix) {
        int h = matrix.length;
        int w = matrix[0].length;
        
        sum = new int[h + 1][w + 1];
        
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1]
                - sum[row1][col2 + 1]
                - sum[row2 + 1][col1]
                + sum[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
