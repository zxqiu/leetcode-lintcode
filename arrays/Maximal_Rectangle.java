/*

Maximal Rectangle

Given a 2D boolean matrix filled with False and True, find the largest rectangle containing all True and return its area.


 Example

Given a matrix:

[
  [1, 1, 0, 0, 1],
  [0, 1, 0, 0, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1]
]

return 6.


解：
stack

这道题可以数方格，遍历到某个坐标(x,y)上，先右数，记录下来宽度w，并计算此时的最大矩阵(1 * w)；然后向下移动一格，再向右数，如果比w小就记录下来，计算最大矩阵(2 * w)；如此直到向下移动时遇到false。
这种方法需要重用前面的计算结果，否则时间复杂度过高。当横向遍历时，如果之前在(x,y)上数过一次，那么x到x+w之间的就不用再数了，因为找到的矩阵一定比之前的得到的小。

不推荐这种方法。


这道题实际应当利用Largest Rectangle in Histogram的解法。当遍历某一行时，将这一行以及上方连续为true的格子看作是Histogram，然后求解Largest Rectangle in Histogram。
为了计算Histogram的高度，用height数组来记录每一行的结果。如果再某一行遇到0，不管上面有多少个true，在此行看来，这一列的高度为0。否则高度加一。

下面的实现中内层两个循环可以合并。放在这里为了将算法表达得更清楚。


*/



public class Solution {
    /**
     * @param matrix a boolean 2D matrix
     * @return an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] h = new int[n];
        
        for (int i = 0; i < m; i++) {
            Stack<Integer> st = new Stack<Integer>();
            
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    h[j] = matrix[i][j] ? h[j] + 1 : 0;
                }
                
                while (!st.isEmpty() && (j == n || h[st.peek()] > h[j])) {
                    int idx = st.pop();
                    int w = st.isEmpty() ? j : j - st.peek() - 1;
                    max = Math.max(h[idx] * w, max);
                }
                
                st.push(j);
            }
        }
        
        return max;
    }
}
