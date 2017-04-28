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
使用一个heap，利用heap的自排序特性。
每次取出堆顶元素，即最小元素，分别乘以2，3，5，把不重复的结果放回堆中。
这样当取到第n个元素时，就是想要的结果。

由于堆不能查重，所以为了快速查重可以牺牲一部分空间，使用一个HashSet保存已有元素。


*/



class Solution {

 /**
 *
 * @param n an integer
 *
 * @return the nth prime number as description.
 *
 **/

    private Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            return a.intValue() - b.intValue();
        }
    };

    public int nthUglyNumber(int n) {
        int factors[] = {2, 3, 5};
        int ret = 1;

        Queue<Integer> heap = new PriorityQueue<Integer>(n, comparator);
        Set<Integer> set = new HashSet<Integer>();

        heap.offer(1);
        set.add(1);

        for (int i = 0; i < n; i++) {
            ret = heap.poll();
            for (int factor : factors) {
                long cur = (long)factor * (long)ret;
                if (cur < Integer.MAX_VALUE && !set.contains((int)cur)) {
                    heap.offer((int)cur);
                    set.add((int)cur);
                }
            }
        }

        return ret;
    }
};

