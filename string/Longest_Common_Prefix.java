/*

Longest Common Prefix


Given k strings, find the longest common prefix (LCP).

Example
For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"

For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"


解：
这道题只能一个一个字符串的排查。
因为从信息的角度来讲，必须查找过所有字符串之后，才有可能找出common prefix。

可以做一个小优化，在每个字符串长度相差较大时会有不错的效果。
先遍历字符串数组，找出最短的一个作为初始prefix，这样可以避免初始prefix过长带来的不必要计算。



*/

public class Solution {
    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        
        for (String s : strs) {
            if (prefix.length() > s.length()) {
                prefix = s;
            }
        }
        
        for (String s : strs) {
            int i;
            for (i = 0; i < prefix.length(); i++) {
                if (s.charAt(i) != prefix.charAt(i)) {
                    break;
                }
            }
            
            prefix = prefix.substring(0, i);
        }
        
        return prefix;
    }
}
