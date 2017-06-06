/*

Data Stream Median


Numbers keep coming, return the median of numbers at every time a new number added.

Clarification
What's the definition of Median?
- Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge 
Total run time in O(nlogn).



解：
实际上时要实现插入新数字后依然保持数组递增或者递减的顺序。
类似与TreeMap或者堆。

由于必须要遍历整个输入数组，已经消耗了O(n)时间，故插入并保持顺序的操作需要在O(logn)完成。
这也与TreeMap和堆的插入时间复杂度一致。

O(logn)可以通过二分搜索实现。
只要找到插入数字左右两边的数字即可正确插入。

同时需要考虑边界情况：
1.当插入数组为空时，直接插入新的数字。
2.当新数字大于最后得到的right所指向的值，或者小于最后的到的left所指向的值时，right和left一定是数组的边界。这是需要插入在边界两端。

*/

public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        ArrayList<Integer> buf = new ArrayList<Integer>();
        int[] ret = new int[nums.length];
        
        for (int i : nums) {
            push(buf, i);
            ret[buf.size() - 1] = buf.get((buf.size() - 1) / 2);
        }
        
        return ret;
    }
    
    private void push(ArrayList<Integer> buf, int num) {
        int left, right;
        
        if (buf.size() == 0) {
            buf.add(num);
            return;
        }
        
        left = 0;
        right = buf.size() - 1;
        
        while (left < right - 1) {
            int mid = (left + right) / 2;
            
            if (buf.get(mid) == num) {
                buf.add(mid, num);
                return;
            } else if (buf.get(mid) > num) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (buf.get(left) > num) {
            buf.add(left, num);
        } else if (buf.get(right) < num) {
            buf.add(right + 1, num);
        } else {
            buf.add(right, num);
        }
    }
}
