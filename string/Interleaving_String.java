/*

Interleaving String

Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.


Example
For s1 = "aabcc", s2 = "dbbca"

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Challenge 
O(n2) time or better


解：
dynanmic programming

dp[i][j]表示s1中的前i个字符和s2中的前j个字符能否组成s3的前i+j字串。

递推公式：
如果s3的前i+j-1字串可以由s1的前i-1个字符，以及s2的前j个字符组成，那么只要s1的第i个字符和s3的第i+j个字符一样，就可以证明s3的前i+j字串可以由s2的前j个字符以及s1的前i个字符组成。
同理可知当s3的前i+j-1字串可以由s1的前i个字符以及s2的前j-1个字符组成，只要s2的第j个字符和s3的第i+j个字符一样，就可以证明s3的前i+j字串可以由s1的前i个字符以及s2的前j个字符组成。

故：
dp[i][j] = (dp[i-1][j] && (s1[i-1] == s3[i+j-1])) || (dp[i][j-1] && (s2[j-1] == s3[i+j-1]))
注意s1[i-1]表示s1中的第i个字符，s3[i+j-1]表示s3中的第i+j个字符。

*/


public class Solution {
    /*
     * @param : A string
     * @param : A string
     * @param : A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null
            || s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        // use char array to make life easier
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        
        boolean[][] dp = new boolean[c1.length + 1][c2.length + 1];
        
        // Initiate
        // When lenght is 0, s1 and s2 can always form s3
        dp[0][0] = true;
        // When 0 character is selected from s2.
        for (int i = 1; i <= c1.length; i++) {
            dp[i][0] = dp[i - 1][0] && (c1[i - 1] == c3[i - 1]);
        }
        // When 0 character is selected from s1.
        for (int i = 1; i <= c2.length; i++) {
            dp[0][i] = dp[0][i - 1] && (c2[i - 1] == c3[i - 1]);
        }
        
        // Calculate
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if ((dp[i - 1][j] && c1[i - 1] == c3[i + j - 1])
                    || (dp[i][j - 1] && c2[j - 1] == c3[i + j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        
        return dp[c1.length][c2.length];
    }
};
