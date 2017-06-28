/*

Update Bits

Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)

 Notice

In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Clarification
You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.

Example
Given N=(10000000000)2, M=(10101)2, i=2, j=6

return N=(10001010100)2

Challenge 
Minimum number of operations?


解：
要求使用最少的位运算，所以一定不能用循环。
先计算出一个mask，用来把n中i到j全部清零，然后将m左移i位，并与mask后的n按位或。

比较麻烦的是计算高位mask。
当j小于31时，先把1左移j+1次之后减1，得到一个0到j位全为1的数，之后按位求反即可得到j+1到32位全为1，其他为0的数。
当j为31时，左移j+1会溢出，故直接把结果0付给高位mask。

*/

class Solution {
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        int high = (j < 31) ? (~((1 << (j + 1)) - 1)) : 0;
        int low = (1 << (i)) - 1;
        int mask = high | low;
        
        return (n & mask | (m << i));
    }
}
