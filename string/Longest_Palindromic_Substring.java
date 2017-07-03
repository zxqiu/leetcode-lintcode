/*

Longest Palindromic Substring

Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

Example
Given the string = "abcdzdcab", return "cdzdc".

Challenge 
O(n2) time is acceptable. Can you do it in O(n) time.


解：
Dynanmic Programming
时间复杂度O(n^2)
求出任意两点之间的字符串是否为为回文，返回其中最长的。

递推公式：
dp[i][j]表示从i到j的字符串是否为回文。
i与j之间的字符串是否为回文，取决于s[i]是否和s[j]相等，以及i+1到j-1是否为回文。
故：
dp[i][j] = s[i] == s[j] && dp[i+1][j-1]

初始条件：
任意一个字符本是是一个回文：
dp[i][i] = true

*/

public class Solution {
    /**
     * @param s input string
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        String longest = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        if (s == null || s.length() < 2) {
            return s;
        }
        
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j == i + 1)) {
                    dp[i][j] = true;
                    
                    if (j - i + 1 > longest.length()) {
                        longest = s.substring(i, j + 1);
                    }
                }
            }
        }
        
        return longest;
    }
}
