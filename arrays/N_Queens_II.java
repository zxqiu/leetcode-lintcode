/*

N Queens II

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Example
For n=4, there are 2 distinct solutions.


解：
跟N Queens思路完全一致。
不同的是只需要统计可行解数量就行。


*/

class Solution {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        int[] path = new int[n];
        Arrays.fill(path, -1);
        return helper(path, 0);
    }
    
    private int helper(int[] path, int depth) {
        if (depth == path.length) {
            return 1;
        }
        
        int ret = 0;
        boolean[] avai = available(path, depth);
        for (int i = 0; i < path.length; i++) {
            if (!avai[i]) {
                continue;
            }
            
            path[depth] = i;
            ret += helper(path, depth + 1);
            path[depth] = -1;
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
};


