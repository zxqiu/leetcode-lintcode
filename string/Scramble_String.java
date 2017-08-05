/*

Scramble String

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example
Challenge 
O(n3) time


解：
Divide and Conquer
这道题递归的基本思路是分治。
分别计算左边一半和右边一半的比较结果，如果两边都为真，则返回true。
如果两边有一边不为真，则继续拆分计算。

需要考虑两种拆分可能
1.s2的左右没有交换。s1的0到mid-1，与s2的0到mid-1比较；同时s1的mid到len-1，与s2的mid到len-1比较。
2.s2的左右进行了交换。s1的0到mid-1，与s2的len-mid到len-1比较；同时s1的mid到len-1，与s2的0到len-mid-1比较。


仅如此比较可以通过lintcode，但是不能通过leetcode。
需要进行枝剪。
比较每次递归的两个字符串中的字符数量，如果不相同则直接返回false。

*/

public class Solution {
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        int[] charset = new int[26];
        int len = s1.length();
        
        if (s1.equals(s2)) {
            return true;
        }
        
        for (int i = 0; i < len; i++) {
            charset[s1.charAt(i) - 'a']++;
            charset[s2.charAt(i) - 'a']--;
        }
        
        for (int i : charset) {
            if (i != 0) {
                return false;
            }
        }
        
        for (int mid = 1; mid < len; mid++) {
            if (isScramble(s1.substring(0, mid), s2.substring(0, mid)) &&
                isScramble(s1.substring(mid), s2.substring(mid))) {
                return true;
            } else if (isScramble(s1.substring(0, mid), s2.substring(len - mid)) &&
                isScramble(s1.substring(mid), s2.substring(0, len - mid))) {
                return true;
            }
        }
        
        return false;
    }
}
