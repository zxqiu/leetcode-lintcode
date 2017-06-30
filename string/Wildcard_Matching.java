/*

Wildcard Matching



Implement wildcard pattern matching with support for '?' and '*'.

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).


Example

isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false


解：
Dynanmic Programming

递推公式：
dp[i][j]表示p的前i个字符是否和s的前j个字符匹配。
若p[i-1]与s[j-1]匹配，则dp[i][j]取决于dp[i-1][j-1]。
如过不匹配，但是p[i-1]为*，则以下三种情况满足任意一种dp[i][j]就为true：
1，*匹配s中0个字符时的匹配结果，即dp[i-1][j]；
2，*匹配s中1个字符时的匹配结果，即dp[i-1][j-1]；
3，*匹配s中多个字符时的匹配结果，即dp[i][j-1]。

故：
dp[i][j] = dp[i-1][j-1], p[i-1] match s[j-1]
         = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1], p[i-1] == *
         = fales, all other cases

初始条件：
1，用p的前0个字符匹配s的前0个字符，一定为true。
  故dp[0][0] = true
2，用p的前n个字符匹配s的前0个字符，如果p的前n个字符全为*，则为true
  故dp[0~n][0] = true, p[0~n-1] = *
3，其他情况初始为false。

*/


public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        
        dp[0][0] = true;
        for (int i = 1; i <= pc.length; i++) {
            if (pc[i - 1] != '*') {
                break;
            }
            dp[i][0] = true;
        }
        
        for (int i = 1; i <= pc.length; i++) {
            for (int j = 1; j <= sc.length; j++) {
                if (isCharMatch(sc[j - 1], pc[i - 1])) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc[i - 1] == '*') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        
        return dp[pc.length][sc.length];
    }
    
    private boolean isCharMatch(char a, char b) {
        return (a == b || a == '?' || b == '?');
    }
}
