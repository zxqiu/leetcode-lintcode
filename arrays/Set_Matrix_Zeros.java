/*

Set Matrix Zeros


Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Example
Given a matrix

[
  [1,2],
  [0,3]
],
return
[
[0,2],
[0,0]
]

Challenge 
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?


解：
先把所有0点的坐标保存起来，然后再一次置零整行和整列。
坐标可以保存再矩阵的上边沿和左边沿。
若(i,j)为0，就把matrix[i][0]和matrix[0][j]置零。
需要注意的是，如果(i,j)中有任意一个为0，就意味着matrix[0][0]会被置零，此时如果还原整行和整列则上边沿和左边沿都会被置零。
故使用zeroX0和zeroY0专门用来保存是否需要将上边沿和左边沿置零。并且置零这两个边沿的操作在其他行列都处理完成之后。


*/


public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    public void setZeroes(int[][] matrix) {
        boolean zeroX0, zeroY0;
        
        if (matrix == null || matrix.length == 0) {
            return;
        }
        
        zeroX0 = false;
        zeroY0 = false;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        zeroY0 = true;
                    }
                    if (j == 0) {
                        zeroX0 = true;
                    }
                    
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] != 0) {
                continue;
            }
            
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
        
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] != 0) {
                continue;
            }
            
            for (int i = 1; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }
        }
        
        if (zeroY0) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        
        if (zeroX0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
