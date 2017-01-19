/*
Word Break

Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.


解：
动态规划
时间O(n^2)
空间O(n)

dp[i]表示包含s中第0~i个字符可否满足条件。
当dp[j](j为小于i的任意index)为真，并且s中第j~i-1个字符可以在字典中找到，则dp[i]为true。
若j遍历小于i的index之后没有满足条件的j，则dp[i]为false。
*/



public class Solution {
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<String>(dict);
        int maxDictLen = Integer.MIN_VALUE;
        
        for (String word : set) {
            maxDictLen = Math.max(maxDictLen, word.length());
        }
        
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0 && i - j <= maxDictLen; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
}