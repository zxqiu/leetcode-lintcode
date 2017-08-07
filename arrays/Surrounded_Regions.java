/*

Surrounded Regions

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O''s into 'X''s in that surrounded region.

Example
X X X X
X O O X
X X O X
X O X X
After capture all regions surrounded by 'X', the board should be:

X X X X
X X X X
X X X X
X O X X


解：
DFS
不能变成X的位置一定跟四条边相通。
所以扫描四条边，用DFS把所有与四条边相通的O全部变成#。
然后再扫描所有点，把#变成O，其他所有变成X。

*/


public class Solution {
    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        // top
        for (int i = 0; i < n; i++) {
            spread(board, 0, i);
        }
        
        // bottom
        for (int i = 0; i < n; i++) {
            spread(board, m - 1, i);
        }
        
        // left
        for (int i = 0; i < m; i++) {
            spread(board, i, 0);
        }
        
        // right
        for (int i = 0; i < m; i++) {
            spread(board, i, n - 1);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void spread(char[][] board, int x, int y) {
        int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = board.length;
        int n = board[0].length;
        
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') {
            return;
        }
        
        board[x][y] = '#';
        
        for (int[] d : direct) {
            spread(board, x + d[0], y + d[1]);
        }
    }
}
