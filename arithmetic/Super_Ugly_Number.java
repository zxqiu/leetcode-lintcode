/*

Super Ugly Number


Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

 Notice

1 is a super ugly number for any given primes.
The given numbers in primes are in ascending order.
0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000

Example
Given n = 6, primes = [2, 7, 13, 19] return 13


解：
这道题的两种解法都与Ugly Number II解法完全一致。把固定primes 2，3，5换乘输入参数就行了。
参照：
https://github.com/zxqiu/leetcode-lintcode/blob/master/arithmetic/Ugly_Number_II.java

解法直接复制粘贴过来。

方法一：
O(n log n)
使用一个TreeSet，利用TreeSet的自排序特性。
每次取出set顶元素，即最小元素，分别乘以primes中的数，把不重复的结果放回set中。
这样当取到第n个元素时，就是想要的结果。

*/


public class Solution {
    /**
     * @param n a positive integer
     * @param primes the given prime list
     * @return the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        TreeSet<Long> set = new TreeSet<Long>();
        long ret = 1L;
        
        set.add(1L);
        for (int i = 0; i < n; i++) {
            ret = set.pollFirst();
            
            for (int p : primes) {
                set.add(p * ret);
            }
        }
        
        return (int)ret;
    }
}



/*

方法二：
利用Merge k Sorted List的思想，把primes中的每个数字与从1开始的质数相乘的结果想象成一个sorted list。
比如2可以有：
2*1，2*2，2*3...
3可以有：
3*1，3*2，3*3...
这里的1，2，3...都必须是质数。

如果可以获得这样的若干个队列，每次取所有队列头中最小的一个数字放进结果队列。
注意需要保证不能有重复数字放进结果队列。
当结果队列长度为n时，返回最后一个值就是第n个质数。

如何获得1，2，3...这个质数序列，以便与primes相乘？
结果队列本身就射这个质数序列。
所以用若干个计数器分别表示primes中每个数所需要乘的数字在结果队列中的位置即可。


*/


public class Solution {
    /**
     * @param n a positive integer
     * @param primes the given prime list
     * @return the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] cnts = new int[primes.length];
        List<Integer> unums = new ArrayList<Integer>();
        
        unums.add(1);
        
        while (unums.size() < n) {
            int pick = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < cnts.length; i++) {
                int tmp = unums.get(cnts[i]) * primes[i];
                if (tmp < min) {
                    pick = i;
                    min = tmp;
                }
            }
            
            cnts[pick]++;
            if (min != unums.get(unums.size() - 1)) {
                unums.add(min);
            }
        }
        
        return unums.get(n - 1);
    }
}
