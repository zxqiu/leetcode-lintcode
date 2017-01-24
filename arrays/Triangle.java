/*

Triangle


Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Notice
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.


 Example

Given the following triangle:

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).


从下向上计算，每次选择当前数的左下和右下中较小的那个与当前数相加，保存在row中。
比如当前行第i个数应当选择与上一行计算结果也就是row中的第i个数和i+1个数中较小的那个相加，存如row[i]。
由于当前行第i+1个数并不需要使用row[i]，所以不需要使用临时的数组来保存当前行的计算结果。

*/


public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0) {
            return 0;
        }
        
        int[] row = new int[triangle.length + 1];
        
        for (int i = triangle.length - 1; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                row[j] = Math.min(row[j], row[j + 1]) + triangle[i][j];
            }
        }
        
        return row[0];
    }
}
