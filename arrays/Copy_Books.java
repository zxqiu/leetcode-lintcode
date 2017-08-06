/*

Copy Books

Given n books and the ith book has A[i] pages. You are given k people to copy the n books.

n books list in a row and each person can claim a continous range of the n books. For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )


解：
Dynanmic Programming

递推公式：
dp[i][j]表示i个人读j本书的最快时间。
可想而知，页数平均分配的时候需要的时间最少。

i个人读完j本书的时间，取决于i-1个人读完前m-1本书的时间，与最后一个人从m读到j的时间中的最大值。
m从第1本到第j本，取读完j本所需时间的最小值。

故：
dp[i][j] = min(dp[i][j], max(dp[i-1][m-1], pages[m]+pages[m+1]+...+pages[j])), m=1~j

初始条件：
0个人读完0本书需要时间为0。
0个人读完大于0本书需要时间为无穷大。
故需要初始化dp[0][0]=0,其他值都初始化为Integer.MAX_VALUE即可。

*/


public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        
        int len = pages.length;
        int total = 0;
        int[][] dp = new int[k + 1][len + 1];
        
        for (int[] i : dp) {
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        
        dp[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            total = 0;
            for (int j = 1; j <= len; j++) {
                total += pages[j - 1];
                int left = total;
                
                for (int m = 1; m <= j; m++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(left, dp[i - 1][m - 1]));
                    left -= pages[m - 1];
                }
            }
        }
        
        return (k > len) ? dp[len][len] : dp[k][len];
    }
}
