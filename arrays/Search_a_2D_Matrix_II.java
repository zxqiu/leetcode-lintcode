/*

Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

This matrix has the following properties:

Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.

Example
Consider the following matrix:

[
  [1, 3, 5, 7],
  [2, 4, 7, 8],
  [3, 5, 9, 10]
]
Given target = 3, return 2.

Challenge 
O(m+n) time and O(1) extra space

解：
使用一个walker从由上角开始搜索：
1. 若当前值大于target，由于左边的值一定小于当前值，下方的值一定大于当前值，故向左移动一步；
2. 若当前值等于target，首先计数器加一。由于下方的值一定大于当前值，左边的值一定小于当前值也一定小于target，故向下移动一步；
3. 若当前值小于target，由于左边的值一定小于当前值也一定小于target，下方的值一定大于当前值，下方一行左边可能包含target，故向下移动一步。由于右边的值一定大于target，而右下方的值也一定大于target，所以不要向右下方移动。

也可以从左下角开始搜索，但是不能从左上角或者右下角开始。
因为如果从左上角或者右下角开始搜索，每一次判断时选择都不唯一。
比如从左上角开始向右搜索时，若当前值小于target，右边的值和下边的值都大于当前值，也可能都小于target，此时不能兼顾两种选择，容易出现遗漏。

*/

public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int x, y, cnt;
        
        x = 0;
        y = matrix[0].length - 1;
        cnt = 0;
        
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] == target) {
                cnt++;
                x++;
            } else if (matrix[x][y] < target) {
                x++;
            }
        }
        
        return cnt;
    }
}

