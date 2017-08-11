/*

Ugly Number II

Ugly number is a number that only have factors 2, 3 and 5.

Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

 Notice

Note that 1 is typically treated as an ugly number.

Example
If n=9, return 10.

Challenge 
O(n log n) or O(n) time.


解：
基本思路是一个一个计算出所有nth前的Ugly Number。

解法一：
O(n log n)
使用一个TreeSet，利用TreeSet的自排序特性。
每次取出set顶元素，即最小元素，分别乘以2，3，5，把不重复的结果放回set中。
这样当取到第n个元素时，就是想要的结果。

*/



class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    
    public int nthUglyNumber(int n) {
        long factors[] = {2L, 3L, 5L};
        long ret = 1L;
        
        TreeSet<Long> set = new TreeSet<Long>();
        set.add(1L);
        
        for (int i = 0; i < n; i++) {
            ret = set.pollFirst();
            for (long factor : factors) {
                set.add(factor * ret);
            }
        }
        
        return (int)ret;
    }
};


/*

方法二：
利用Merge k Sorted List的思想，把2，3，5每个数字与从1开始的质数相乘的结果想象成一个sorted list。
比如2可以有：
2*1，2*2，2*3...
3可以有：
3*1，3*2，3*3...

这里的1，2，3...都必须是质数。
如果可以获得这样的三个队列，每次取三个队列头中最小的一个数字放进结果队列。
注意需要保证不能有重复数字放进结果队列。
当结果队列长度为n时，返回最后一个值就是第n个质数。

如何获得1，2，3...这个质数序列，以便与2，3，4相乘？
结果队列本身就射这个质数序列。
所以用三个计数器分别表示2，3，5所需要乘的数字在结果队列中的位置即可。


*/


class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    
    public int nthUglyNumber(int n) {
        int[] primes = {2, 3, 5};
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
};
