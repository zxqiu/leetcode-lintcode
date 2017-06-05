/*

Median of two Sorted Arrays

There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Example
Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.

Given A=[1,2,3] and B=[4,5], the median is 3.

Challenge 
The overall run time complexity should be O(log (m+n)).


解：
若两个数组的总长度为奇数，找到中间那个数即可。
若两个数组的总长度为偶数，需要找到中间的两个数，然后求平均值。

这样实际是要求在两个数组中找出任意第几个数。
所以首先实现找到第k个数的方法。

首先把k分成k/2和k-k/2两部分，分别在两个数组中找出对应的值。
在A中找第k/2个数a，B中找到第k-k/2个数b，比较两个数的大小。

如果a大于b，则说明b以及B数组中b之前的所有数一定都在两个数组的第k个数之前，故下次递归时B数组可以从b后面的一个数继续查找。
同时下次递归时应当从k中减去k-k/2这部分。

如果b大于a，则说明a以及A数组中a之前的所有数一定都在两个数组的第k个数之前，故下次递归时A数组可以从a后面的一个数继续查找。
同时下次递归时应当从k中减去k/2这部分。

*/


class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (((double)findKth(A, 0, B, 0, len / 2) + (double)findKth(A, 0, B, 0, len / 2 + 1)) / 2.0);
        } else {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }
    
    private int findKth(int[] A, int aStart, int[] B, int bStart, int k) {
        if (k == 1) {
            if (aStart < A.length && bStart < B.length) {
                return Math.min(A[aStart], B[bStart]);
            } else if (aStart < A.length) {
                return A[aStart];
            } else {
                return B[bStart];
            }
        }
        
        int a = (aStart + k / 2 - 1 < A.length) ? A[aStart + k / 2 - 1] : Integer.MAX_VALUE;
        int b = (bStart + k - k / 2 - 1 < B.length) ? B[bStart + k - k / 2 - 1] : Integer.MAX_VALUE;
        
        if (a > b) {
            return findKth(A, aStart, B, bStart + k - k / 2, k / 2);
        } else {
            return findKth(A, aStart + k / 2, B, bStart, k - k / 2);
        }
    }
}

