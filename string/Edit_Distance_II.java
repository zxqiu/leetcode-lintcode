/*

Edit Distance II

Given two strings S and T, determine if they are both one edit distance apart.

Example
Given s = "aDb", t = "adb"
return true


解:
从头逐字符比较两个字符串，统计修改数量为change。
1.如果s[i]==t[j]，那么i++,j++，继续比较。
2.如果s[i]==t[j]，那么有三种情况：
  1)s和t剩余的字符数量相等，则修改当前字符使当前两个字符相等，并且change++。
  2)s剩余字符比t多，那么s中删除一个字符，即i++而j不变，并且change++。
  3)t剩余字符比s多，那么t中删除一个字符，即j++而i不变，并且change++。
3.如果change大于1，直接返回false。
4.比较结束后，如果任何一个s或t中的字符没有用完，则应当全部删除，并把操作数加入change中。

最后如果总操作数为1则返回true，否则返回false。

*/

public class Solution {
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        
        int change = 0;
        int lens = s.length();
        int lent = t.length();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int i = 0;
        int j = 0;
        
        while (i < lens && j < lent) {
            if (sc[i] != tc[j]) {
                change++;
                if (lens - i == lent - j) {
                    i++;
                    j++;
                } else if (lens - i > lent - j) {
                    i++;
                } else if (lens - i < lent - j) {
                    j++;
                }
            } else {
                i++;
                j++;
            }
            
            if (change > 1) {
                return false;
            }
        }
        
        return change + Math.max(lens - i, lent - j) == 1;
    }
}
