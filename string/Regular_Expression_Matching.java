/*

Regular Expression Matching


Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)
Have you met this question in a real interview? Yes
Example
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true


解：
这道题重点考虑两点：
1.两个字符串剩余长度不一致时：
  1.如果s遍历完时，p还有剩余，那么必须是‘*’或者任意字符加“*”。
  2.如果p遍历完时，s还有剩余，返回false。
2.当p当前字符的下一个字符为“*”时：
  1.“*”可以表示一个字符都不匹配，此时只需要判断s从当前字符开始是否与p从“*”之后开始匹配。
  2.“*”可以表示匹配一个或者多个字符，此时需要保证s当前字符以及之后存在一个或者多个字符与p当前字符匹配，并且剩余字符可以与p中“*”之后的字符匹配。

*/

public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        char[] S = s.toCharArray();
        char[] P = p.toCharArray();
        
        return helper(S, P, 0, 0);
    }
    
    private boolean helper(char[] s, char[] p, int idxS, int idxP) {
        if (idxS == s.length) {
            if (idxP == p.length) {
                return true;
            } else if (idxP == p.length - 1) {
                return (p[idxP] == '*');
            } else if (idxP == p.length - 2) {
                return (p[idxP + 1] == '*');
            } else {
                return false;
            }
        } else if (idxP == p.length || idxS < 0) {
            return false;
        }
        
        if (idxP + 1 < p.length && p[idxP + 1] == '*') {
            /* match none */
            if (helper(s, p, idxS, idxP + 2)) {
                return true;
            }
            
            /* match at least one */
            for (int i = idxS; i < s.length; i++) {
                if (!isCharMatch(s[i], p[idxP])) {
                    return false;
                }
                
                if (helper(s, p, i + 1, idxP + 2)) {
                    return true;
                }
            }
        } else if (isCharMatch(s[idxS], p[idxP])) {
            return helper(s, p, idxS + 1, idxP + 1);
        }
        
        return false;
    }
    
    private boolean isCharMatch(char a, char b) {
        return (a == b || a == '.' || b == '.');
    }
}
