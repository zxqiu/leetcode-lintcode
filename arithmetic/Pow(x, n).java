/*

Pow(x, n)


Implement pow(x, n).

 Notice

You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.

Example
Pow(2.1, 3) = 9.261
Pow(0, 1) = 0
Pow(1, 0) = 1
Challenge 
O(logn) time


解：
binary search
每次把n分成左右两部分分别计算，再把左右两边的计算值相乘。

*/

public class Solution {
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        if (n == 1) {
            return x;
        } else if (n == 0) {
            return 1;
        }
        
        boolean flag = (n < 0) ? true : false;
        int left = Math.abs(n) / 2;
        int right = Math.abs(n) - left;
        double mul = myPow(x, left) * myPow(x, right);
        
        return flag ? 1 / mul : mul;
    }
}
