/*

Valid Number


Validate if a given string is numeric.

Example
"0" => true

" 0.1 " => true

"abc" => false

"1 a" => false

"2e10" => true


解：
这道题难点在于确定哪些是合法数字。

合法数字列举如下：
123
00
001
.1
1.
0.0
0e0
01e01
.1e1

非法数字列举如下：
.
e
e1
1e
1e0.1
1e1e1
0.0.0

总结一下：
1.任意的数字组合都合法。
2.不能出现两个小数点或者两个e。
3.e不能作为开头或者结尾。小数点可以作为开头或者结尾。
4.e后面不能出现小数点。小数点后面可以出现e。
5.不能只有e和小数点，必须有至少一个数字。

*/

public class Solution {
    /**
     * @param s the string that represents a number
     * @return whether the string is a valid number
     */
    public boolean isNumber(String s) {
        char[] c = s.trim().toCharArray();
        boolean hasE = false;
        boolean hasDot = false;
        boolean hasNum = false;
        
        for (int i = 0; i < c.length; i++) {
            char cur = c[i];
            
            if (cur >= '0' && cur <= '9') {
                hasNum = true;
                continue;
            } else if (cur == 'e') {
                if (i == 0 || hasE) {
                    return false;
                }
                hasE = true;
                hasNum = false;
            } else if (cur == '.') {
                if (hasDot || hasE) {
                    return false;
                }
                hasDot = true;
            }
        }
        
        return c.length > 0 && hasNum;
    }
}
