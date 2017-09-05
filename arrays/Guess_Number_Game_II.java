/*

Guess Number Game II



We are playing the Guess Game. The game is as follows:
I pick a number from 1 to n. You have to guess which number I picked.
Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.


Example

Given n = 10, I pick 8.
First round: You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round: You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.
You end up paying $5 + $7 + $9 = $21.

Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
So when n = ｀10, return16`



解：
Dynanmic Programming

递推公式：
dp[i][j]表示从i到j中猜中任意数字的最小开销。
假设猜数字k，i<k<j，那么需要在i到k-1和k+1到j中继续猜。
依据minimax原理，对方可选择让我方开销最大的回答方式，故选择i到k-1和k+1到j中开销大的那个。
故猜k时的最小开销是：
k + max(dp[i][k-1], dp[k+1][j])

k可以取i到j，我方应当选择让自己开销最小的一个，故：
dp[i][j] = min(k + max(dp[i][k-1], dp[k+1][j])，i<k<j)


初始条件：
由于每次需要用到i和j之间长度小于当前长度的dp值，所以应当按照长度递增来进行计算。
当i和j相等时，长度为1，猜中的开销为0。
故dp[i][i]=0。

*/

public class Solution {
    /**
     * @param n an integer
     * @return how much money you need to have to guarantee a win
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int min = Integer.MAX_VALUE;
                min = i + dp[i + 1][i + len - 1];
                min = Math.min(min, i + len - 1 + dp[i][i + len - 2]);
                
                for (int j = i + 1; j < i + len - 1; j++) {
                    min = Math.min(min, j + Math.max(dp[i][j - 1], dp[j + 1][i + len - 1]));
                }
                
                dp[i][i + len - 1] = min;
            }
        }
        
        return dp[1][n];
    }
}
