/*

Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].


解：
从(i，i)开始，上下左右转一圈，全部加进结果数组即可。
注意处理当只剩一行或者一列的情况。

*/

public class Solution {
    /**
     * @param matrix a matrix of m x n elements
     * @return an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<Integer>();
        }
        
        List<Integer> ret = new ArrayList<Integer>();
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i = 0; i <= (Math.min(m, n) - 1) / 2; i++) {
            int width = n - 2 * i - 1;
            int height = m - 2 * i - 1;
            int x = i;
            int y = i;
            
            // go right
            for (int j = 0; j < width; j++) {
                ret.add(matrix[x][y]);
                y += 1;
            }
            
            if (height == 0) {
                ret.add(matrix[x][y]);
                break;
            }
            
            // go down
            for (int j = 0; j < height; j++) {
                ret.add(matrix[x][y]);
                x += 1;
            }
            
            if (width == 0) {
                ret.add(matrix[x][y]);
                break;
            }
            
            // go left
            for (int j = 0; j < width; j++) {
                ret.add(matrix[x][y]);
                y -= 1;
            }
            
            // go up
            for (int j = 0; j < height; j++) {
                ret.add(matrix[x][y]);
                x -= 1;
            }
        }
        
        return ret;
    }
}
