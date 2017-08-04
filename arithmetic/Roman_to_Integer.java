/*

Roman to Integer

Given a roman numeral, convert it to an integer.

The answer is guaranteed to be within the range from 1 to 3999.


解：
1.如果一个数字小于它后面的数字，则应该在结果中减去该数字。
2.否则，结果中加入该数字。

*/

public class Solution {
    /**
     * @param s Roman representation
     * @return an integer
     */
    public int romanToInt(String s) {
        int ret = 0;
        char[] sa = s.toCharArray();
        
        for (int i = 0; i < sa.length; i++) {
            int num = charToInt(sa[i]);
            int next = (i == sa.length - 1) ? 0 : charToInt(sa[i + 1]);
            
            if (num < next) {
                ret -= num;
            } else {
                ret += num;
            }
        }
        
        return ret;
    }
    
    private int charToInt(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
