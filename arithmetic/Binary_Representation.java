/*

Binary Representation




Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Example

For n = "3.72", return "ERROR".

For n = "3.5", return "11.1".


解：
这道题重点考察数制转换。
首先将输入字符串分成整数和小数两部分，然后分别转换成二进制，再组合在一起返回。

整数转换二进制是连续除以二，根据每次得到的余数决定每一位的值，直到除法结果为0。
小数转换二进制是连续乘以二，根据每次的到的整数部分决定每一位的值，然后用乘积的小数部分继续下一次运算，直到小数部分为0。

如果小数部分结尾数字不是0或者5，那么这个小数转换成的二进制数将会无限长，因为不可能使乘2的结果小数部分为0。

最后根据两部分返回字符串的长度来判断是否返回ERROR即可。

*/


public class Solution {
    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        int dotIdx = -1;
        String left, right;
        
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '.') {
                dotIdx = i;
                break;
            }
        }
        
        if (dotIdx == -1) {
            left = leftRepresentation(n);
            right = "";
        } else {
            left = leftRepresentation(n.substring(0, dotIdx));
            right = rightRepresentation(n.substring(dotIdx, n.length()));
        }
        
        if (left.length() > 32 || right.length() > 33) {
            return "ERROR";
        }
        return left + right;
    }
    
    private String leftRepresentation(String n) {
        String ret = "";
        int in = Integer.valueOf(n);
        
        while (in > 0) {
            int tmp = in % 2;
            in /= 2;
            
            ret = String.valueOf(tmp) + ret;
        }
        
        return (ret.length() == 0) ? "0" : ret;
    }
    
    private String rightRepresentation(String n) {
        String ret = ".";
        double in = Double.valueOf("0" + n);
        
        if (n.charAt(n.length() - 1) != '0' && n.charAt(n.length() - 1) != '5') {
            return ".0000000000000000000000000000000000";
        }
        
        while (in > 0.0) {
            int tmp = (int)(in * 2.0);
            in = (in * 2.0) - tmp;
            
            ret += String.valueOf(tmp);
            if (ret.length() > 33) {
                break;
            }
        }
        
        return (ret.length() == 1) ? "" : ret;
    }
}
