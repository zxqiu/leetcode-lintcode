/*

Rotate Image 


You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Example
Given a matrix

[
    [1,2],
    [3,4]
]
rotate it by 90 degrees (clockwise), return

[
    [3,1],
    [4,2]
]
Challenge 
Do it in-place.


解：
把原图按照左上，右上，左下，右下分成四个矩形。然后顺时针移动矩形中的每个点。
只需要遍历一个矩形，然后分别更新其在其他三个区域的对应点即可。

*/


public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    public void rotate(int[][] matrix) {
        int len;
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        len = matrix.length;
        
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < (len + 1) / 2; j++) {
                int tmp0, tmp1, x, y;
                
                x = j;
                y = len - 1 - i;
                tmp0 = matrix[x][y];
                matrix[x][y] = matrix[i][j];
                
                x = len - 1 - i;
                y = len - 1 - j;
                tmp1 = matrix[x][y];
                matrix[x][y] = tmp0;
                
                x = len - 1 - j;
                y = i;
                tmp0 = matrix[x][y];
                matrix[x][y] = tmp1;
                
                matrix[i][j] = tmp0;
            }
        }
    }
}
