/*

Using O(1) time to check whether an integer n is a power of 2.
Have you met this question in a real interview?
Example

For n=4, return true;

For n=5, return false;
Challenge

O(1) time

解：
2的任何次方的二进制表示都只有一个为1的bit。
所以可以用位运算找出数字中是否有多于一个为1的bit。


*/

class Solution {
    /*
     * @param n: An integer
     * @return: True or false
     */
    public boolean checkPowerOf2(int n) {
        if (n <= 0) {
            return false;
        }
        
        /* n = 10100B
            => (n-1) = 10011B
            => n & (n - 1) = 10000B
            => n - (n & (n - 1)) = 00100B
            => 00100B != n
            => false
        */
        return ((n - (n & (n - 1))) == n);
    }
};
