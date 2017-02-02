/*

Longest Substring Without Reapeating Character

Given a string, find the length of the longest substring without repeating characters. 

 Example

For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.
Challenge

O(n) time


解：
dynanmic programming + HashMap

递推公式：
dp[i]表示包含s[i-1]作为字串最后一个字符时的最长子串长度。
如果s[j] == s[i-1]，那么该子串最长长度只能是i-1-j，即从j+1到i-1。
但是这里还需要考虑从j+1到i-1中可能存在其他重复字符的情况。比如j+1和i-1都是'a'，而j+2和i-2都是'b'，那么最长字串只能是从j+3到i-1。
所以，dp[i]还需要考虑dp[i-1]，即考虑s[i-2]构成的子串。若s[i-2]构成的子串被j+1到i-1包含在内，则dp[i]只能是dp[i-1]+1，即从上一个子串的开始到s[i-1]。

使用HashMap来记录某个字符在s中最后一次出现的位置。
得到如下公式：
dp[i] = dp[i-1] + 1，map[s[i-1]] >= (i - 1) - dp[i-1]，即s[i-1]上次出现的位置不在上一个子串第一个字符之前；
dp[i] = (i - 1) - map[s[i-1]]，map[s[i-1]] < (i - 1) - dp[i-1], 即s[i-1]上一次出现的位置在上一个子串第一个字符之前

初始条件：
dp[0] = 0
map为空，理解为任意一个字符的初始位置都为无穷小，一定在上一个子串第一个字符出现之前。
当s中第一个字符被处理时，子串长度为1，该字符还没有出现过，所以dp[1] = dp[0] + 1，只有dp[0] = 0 才可以得到正确结果1。


空间优化：
由于处理dp[i]只需要用到dp[i-1]，所以可以用一个int来记录dp[i-1]。
但是由于用了HashMap，所以空间复杂度O(n)不变。

这里还是用dp数组以便于理解。

*/


public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int[] dp = new int[s.length() + 1];
        char[] source = s.toCharArray();
        int max = 0;
        
        for (int i = 1; i <= s.length(); i++) {
            if (map.containsKey(source[i - 1]) && map.get(source[i - 1]) >= i - 1 - dp[i - 1]) {
                dp[i] = i - 1 - map.get(source[i - 1]);
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            
            max = Math.max(max, dp[i]);
            map.put(source[i - 1], i - 1);
        }
        
        return max;
    }
}
