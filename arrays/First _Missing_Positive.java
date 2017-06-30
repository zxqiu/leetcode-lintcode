/*

 First Missing Positive
 
 

Given an unsorted integer array, find the first missing positive integer.


Example

Given [1,2,0] return 3,
and [3,4,-1,1] return 2.
Challenge

Your algorithm should run in O(n) time and uses constant space.


解：
把所有正数a，放入A数组的a-1位置。如果a小于等于0，或者大于A的长度，则忽略。
然后遍历A数组，找到第一个满足A[i] != i+1的i，返回i+1就是缺少的正数。
如果遍历A也没有找到满足条件的i，说明从1到A.length的正数都存在，则返回A.length+1。

*/


public class Solution {
    /**    
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) {
            return 1;
        }
        
        for (int i = 0; i < A.length; i++) {
            int tmp = A[i];
            A[i] = 0;
            while (tmp > 0 && tmp <= A.length && A[tmp - 1] != tmp) {
                int tmp0 = A[tmp - 1];
                A[tmp - 1] = tmp;
                tmp = tmp0;
            }
        }
        
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        
        return A.length + 1;
    }
}
