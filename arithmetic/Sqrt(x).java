/*

Sqrt(x)


Implement int sqrt(int x).

Compute and return the square root of x.



 Example

sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3

 Challenge

O(log(x))


解：
二分法
注意int乘int的溢出问题。

循化在left和right相邻时终止，这时所求的结果一定在范围[left, right]内。
在这个范围内，先判断right，若right平方不等于x，那精确结果一定是一个大于等于left且小于right的值，这种情况应当返回left。
其实若x不为1，那么right的平方就不可能等于x，故返回语句可以改成：

return (x == 1) ? 1 : (int)left;

*/


class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        long left, right;
        
        left = 0;
        right = ((long)x + 1) / 2;
        
        while (left < right - 1) {
            long mid = (left + right) / 2;
            long product = mid * mid;
            
            if (product == x) {
                return (int)mid;
            } else if (product < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return (right * right == x) ? (int)right : (int)left;
    }
}
