/*

Delete Digits


Given string A representative a positive integer which has N digits, remove any k digits of the number, the remaining digits are arranged according to the original order to become a new positive integer.

Find the smallest integer after remove k digits.

N <= 240 and k <= N,

Example
Given an integer A = "178542", k = 4

return a string "12"


解：
要保证结果最小，那就需要尽可能让高位变小。
从最高位开始，如果某一个数大于其下一位的数，这个数应当删除。
如果某一个数大于其前一位的数，而小于其下一位的数，则不需要管，因为删除其后面更大的数会使高位变得更小。

比如输入1243，k=1，最小结果为123，应当删除4。
输入1324，k=1，最小结果为124，应当删除3。

处理完之后应当删除所有最高位的0。

*/



public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        int idx = 0;
        StringBuilder a = new StringBuilder(A);
        
        while (idx < a.length() && k > 0) {
            if (idx == a.length() - 1 || (idx >= 0 && a.charAt(idx) > a.charAt(idx + 1))) {
                a.deleteCharAt(idx--);
                k--;
            } else {
                idx++;
            }
        }
        
        while (a.length() > 0 && a.charAt(0) == '0') {
            a.deleteCharAt(0);
        }
        
        return a.toString();
    }
}
