/*

Word Search

Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.


 Example
 
Given board =

[
  "ABCE",
  "SFCS",
  "ADEE"
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.


解：
这道题的思路很简单，从矩阵中的每个字符出发，如果该字符和word中第一个字符一致，则继续判断其上下左右四个字符是否与word中下一个字符一致。
从上下左右中一致的字符再出发进行判断，直到匹配完word，或者遍历完整个矩阵。


*/


public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null) {
            return false;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existHelper(i, j, board, word)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean existHelper(int x, int y, char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }
        
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || 
            board[x][y] != word.charAt(0)) {
            return false;
        }
        
        int[][] travel = {{-1, 0}, {0, 1}, {1, 0}, {0, -1 }};
        char save = board[x][y];
        board[x][y] = '#';
        for (int i = 0; i < 4; i++) {
            if (existHelper(x + travel[i][0], y + travel[i][1], board, word.substring(1, word.length()))) {
                return true;
            }
        }
        
        board[x][y] = save;
        return false;
    }
}