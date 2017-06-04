/*

String to Integer II

Implement function atoi to convert a string to an integer.

If no valid conversion could be performed, a zero value is returned.

If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

Example
"10" => 10
"-1" => -1
"123123123123123" => 2147483647
"1.0" => 1


解：
试吧。

*/

public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        if (str == null) {
            return 0;
        }
        char[] in = str.trim().toCharArray();
        
        if (in.length == 0) {
            return 0;
        }
        
        int flag = (in[0] == '-') ? -1 :
                    (in[0] == '+') ? 1 : 0;
        int isNeg = (flag == 0) ? 1 : flag;
        long ret = 0;
            
        for (int i = Math.abs(flag); i < in.length; i++) {
            char c = in[i];
            if (c > '9' || c < '0') {
                break;
            }
            
            ret *= 10;
            ret += c - '0';
            
            if (ret * isNeg >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (ret * isNeg <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        
        return (int)ret * isNeg;
    }
}
