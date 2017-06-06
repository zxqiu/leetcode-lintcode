/*

Longest Common Substring


Given two strings, find the longest common substring.

Return the length of it.

 Notice

The characters in substring should occur continuously in original string. This is different with subsequence.

Example
Given A = "ABCD", B = "CBCE", return 2.

Challenge 
O(n x m) time and memory.



解：
dynanmic programming

dp[i][j]表示包含A[i-1]和B[j-1]在内的最长子字符串。

递推公式：
当A[i-1]和B[j-1]相等，应当对之前的子字符串长度加1。
若不等，则之前的子字符串被截断，当前子字符串长度为0。
故：
dp[i][j] = dp[i-1][j-1] + 1, A[i-1] == B[j-1]
dp[i][j] = 0, A[i-1] != B[j-1]


初始条件：
当只取A或者只取B时，共同子字符串不存在，故长度为0。
即dp的第一行和第一列都为0。


*/

public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        int max = 0;
        
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max;
    }
}
