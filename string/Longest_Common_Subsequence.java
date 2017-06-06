/*

Longest Common Subsequence


Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

Clarification
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.



解：
dynanmic programing

dp[i][j]表示到A[i-1]和B[j-1]为止的最长共同子序列。

递推公式：
如果A[i-1]和B[j-1]相等，那么当前LCS长度应该为去掉A[i-1]和B[j-1]时的LCS长度加1。
如果不相等，那么应当为去掉A[i-1]或者去掉B[j-1]或者两者都去掉时LCS长度中最大的那个。由于单独去掉A[i-1]或者B[j-1]时的信息多与两者都去掉，所以只要取单独去掉两者中任意一个时的最大LCS。
故：
dp[i][j] = dp[i-1][j-1], A[i-1] == B[j-1]
dp[i][j] = max(dp[i-1][j], dp[i][j-1]), A[i-1] != B[j-1]

初始条件：
当只有A或者只有B时，最大LCS长度为0。故只需让dp数组的第一行和第一列为0。

*/

public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        
        return dp[A.length()][B.length()];
    }
}

