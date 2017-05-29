/*

N-Queens

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example
There exist two distinct solutions to the 4-queens puzzle:

[
  // Solution 1
  // [".Q..",
  // "...Q",
  // "Q...",
  // "..Q."
  // ],
  // // Solution 2
  // ["..Q.",
  // "Q...",
  // "...Q",
  // ".Q.."
  // ]
]

Challenge 
Can you do it without recursion?


解：
使用一个一维数组表示棋盘，第一个数表示第一行放置棋子的位置，第二个数表示第二行放置棋子的位置，依次类推。
通过上面的数组可以计算出当前一行哪些位置可以继续放置棋子。
数组被填满时，将其转换成字符串组成的棋盘即可。

方法一：
DFS
递归解决这个问题比较容易，只需要先写好计算当前行哪些位置可以放置棋子，以及将数组转换成棋盘的方法，然后简单的使用DFS就可以解决问题。
每次递归先计算出当前一行所有可以放置棋子的位置，然后依次在每一个可以放子的位置放置棋子后进入下依次递归。
当递归深度达到n时，说明当前已经获得了一个有效解，将该解转换成棋盘然后加入结果队列。

*/


class Solution {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        int[] path = new int[n];
        Arrays.fill(path, -1);
        helper(path, 0, ret);
        return ret;
    }
    
    private void helper(int[] path, int depth, ArrayList<ArrayList<String>> ret) {
        if (depth == path.length) {
            ret.add(pathToArray(path));
            return;
        }
        
        boolean[] avai = available(path, depth);
        for (int i = 0; i < avai.length; i++) {
            if (avai[i] == false) {
                continue;
            }
            path[depth] = i;
            helper(path, depth + 1, ret);
            path[depth] = -1;
        }
    }
    
    private boolean[] available(int[] path, int depth) {
        boolean[] ret = new boolean[path.length];
        Arrays.fill(ret, true);
        for (int i = 0; i < path.length; i++) {
            if (path[i] == -1) {
                continue;
            }
            int diff = depth - i;
            int left = path[i] - diff;
            int right = path[i] + diff;
            
            if (left >= 0) {
                ret[left] = false;
            }
            if (right < path.length) {
                ret[right] = false;
            }
            ret[path[i]] = false;
        }
        return ret;
    }
    
    private ArrayList<String> pathToArray(int[] path) {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i : path) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < path.length; j++) {
                if (j == i) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            ret.add(sb.toString());
        }
        
        return ret;
    }
};


/*

方法二：
BFS
循环解决这个问题用BFS比较容易理解。
一行一行向下计算，直到计算完整个棋盘。
用一个队列保存某一行的所有可行解，然后进入下一行继续计算。

第一层循环表示行数，循环至第n行。
第二层循环遍历当前所有可行解。
第三层循环为每个可行解计算所有可以放子的位置，然后把新的可行解保存起来。

计算完成后把所有可行解变成棋盘即可。

*/


class Solution {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        ArrayList<int[]> list = new ArrayList<int[]>();
        int[] pathTmp = new int[n];
        
        Arrays.fill(pathTmp, -1);
        list.add(pathTmp);
        
        for (int i = 0; i < n; i++) {
            ArrayList<int[]> tmp = new ArrayList<int[]>();
            for (int[] path : list) {
                boolean[] avai = available(path, i);
                for (int j = 0; j < n; j++) {
                    if (avai[j] == false) {
                        continue;
                    }
                    int[] newPath = Arrays.copyOf(path, path.length);
                    newPath[i] = j;
                    tmp.add(newPath);
                }
            }
            list = tmp;
        }
        
        for (int[] path : list) {
            ret.add(new ArrayList<String>(pathToArray(path)));
        }
        
        return ret;
    }
    
    private boolean[] available(int[] path, int depth) {
        boolean[] ret = new boolean[path.length];
        Arrays.fill(ret, true);
        for (int i = 0; i < path.length; i++) {
            if (path[i] == -1) {
                continue;
            }
            int diff = depth - i;
            int left = path[i] - diff;
            int right = path[i] + diff;
            
            if (left >= 0) {
                ret[left] = false;
            }
            if (right < path.length) {
                ret[right] = false;
            }
            ret[path[i]] = false;
        }
        return ret;
    }
    
    private ArrayList<String> pathToArray(int[] path) {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i : path) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < path.length; j++) {
                if (j == i) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            ret.add(sb.toString());
        }
        
        return ret;
    }
};
