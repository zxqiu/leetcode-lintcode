/*

Fast Power

Calculate the an % b where a, b and n are all 32bit integers.


 Example

For 231 % 3 = 2

For 1001000 % 1000 = 0


 Challenge

O(logn)


解：
二分法

a^n % b
= ((bm + x)*(bm + x)*(b + y)) % b	// 对a^n进行二分，若n为偶数，则分为(bm + x)*(bm + x)，若为奇数，则分为(bm + x)*(bm + x)*(b + y)。
= (x*x*y) % b				// 由于b乘任何数得到的值都可以被b整除，所以只考虑x和y相乘的情况
= (((x*x) % b) * y) % b			// 为了避免x*x*y溢出long，所以分次计算


*/



class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        if (n == 1) {
            return a % b;
        }
        if (n == 0) {
            return 1 % b;
        }
        
        long x = fastPower(a, b, n / 2);
        long y = (n % 2 == 0) ? 1 : a % b;
        
        return (int)((((x * x) % b) * y) % b);
    }
}
