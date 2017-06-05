/*

Search in Rotated Sorted Array

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Example
For [4, 5, 1, 2, 3] and target=1, return 2.

For [4, 5, 1, 2, 3] and target=0, return -1.

Challenge 
O(logN) time


解：
看到O(logN)首先想到的是二分。
与有序数组二分查找不同的是，需要分成若干种不同的情况来决定查找指针如何移动。
每次循环的输入可以分成两大类：
1. 有序数组。
  直接进行一般的二分。
2. 旋转过的有序数组。
  1）首先判断target在数组的什么位置。比较target和left指向值的大小，若大于left指向值，说明target在pivot左边，否则在右边。
  2）若target在pivot左边：
    1> 如果mid指向值大于target，说明mid也在pivot左边，直接把right挪动到mid。
    2> 如果mid指向值小于target，则mid可能在pivot的任意一边。
       如果mid在pivot左边，则把left挪动到mid，否则把right挪动到mid。
  3）若target在pivot右边：
    1> 如果mid指向值小于target，说明mid也在pivot右边，直接把left挪动到mid。
    2> 如果mid指向值大于target，则mid可能在pivot任意一边。
       如果mid在pivot左边，则把left挪动到mid，否则把right移动到mid。

以上逻辑有重复的部分，可以整合。
这里提供没有整合的解。

*/

public class Solution {
    /** 
     * @param A : an integer rotated sorted array
     * @param target :  an integer to be searched
     * return : an integer
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int left, right;
        left = 0;
        right = A.length - 1;
        
        while (left < right - 1) {
            int mid = (left + right) / 2;
            
            if (A[mid] == target) {
                return mid;
            } else if (A[left] < A[right]) {
                if (A[mid] > target) {
                    right = mid - 1;
                } else if (A[mid] < target) {
                    left = mid + 1;
                }
            } else if (A[left] > A[right]) {
                if (target > A[left]) {
                    if (A[mid] > target) {
                        right = mid - 1;
                    } else {
                        if (A[mid] > A[left]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                } else {
                    if (A[mid] < target) {
                        left = mid + 1;
                    } else {
                        if (A[mid] > A[left]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                }
            }
        }
        
        return (A[left] == target) ? left :
                (A[right] == target) ? right : -1;
    }
}

