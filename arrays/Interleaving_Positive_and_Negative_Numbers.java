/*

Interleaving Positive and Negative Numbers


Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

 Notice

You are not necessary to keep the original order of positive integers or negative integers.

Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

Challenge 
Do it in-place and without extra memory.

解：
扫描一边数组，把负数放在左边，正数放在右边。同时统计有多少个负数。
如果负数多，负数应当从第一个数开始，如果正数多，正数应当从第一个开始。一样多则谁先都行。
left指向第一个正数开始的位置，right指向最后一个负数的位置，交换left和right的值直到两个指针相遇。

*/


class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        int cnt, left, right;
        
        cnt = 0;
        right = 0;
        while (right < A.length) {
            if (A[right] < 0) {
                swap(A, cnt, right);
                cnt++;
            }
            right++;
        }
        
        left = (A.length % 2 == 0) ? 1 :
                (cnt > A.length / 2) ? 1 : 0;
        right = (A.length % 2 == 0) ? A.length - 2 :
                (cnt > A.length / 2) ? A.length - 1 : A.length - 2;
                
        while (left < right) {
            swap(A, left, right);
            left += 2;
            right -= 2;
        }
   }
   
   void swap(int[] A, int a, int b) {
       int tmp = A[a];
       A[a] = A[b];
       A[b] = tmp;
   }
}


