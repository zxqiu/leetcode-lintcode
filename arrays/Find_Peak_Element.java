/*

Find Peak Element


There is an integer array which has the following features:

    The numbers in adjacent positions are different.
    A[0] < A[1] && A[A.length - 2] > A[A.length - 1].

We define a position P is a peek if:

A[P] > A[P-1] && A[P] > A[P+1]

Find a peak element in this array. Return the index of the peak.
Notice

The array may contains multiple peeks, find any of them.

Example

Given [1, 2, 1, 3, 4, 5, 7, 6]

Return index 1 (which is number 2) or 6 (which is number 7)
Challenge

Time complexity O(logN)



解：
二分搜索。
这道题的关键在于使left始终保持A[left]<A[left+1]，right始终保持A[right]<A[right-1]。如此left和right之间就一定存在一个peak。

对于从2到A.length-2的所有点来说，其与左右两点的关系存在以下四种可能：
1.递增。此时需要把left移动到mid。
2.递减。此时需要把right移动到mid。
3.先递增后递减（peak）
4.先递减后递增。此时把left或者right移动到mid都可以。

注意由于需要形成peak，所以应当满足left<right-2。这样在退出循环时会获得3个点，中间的一定时peak。

*/

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        int left, right;
        
        if (A == null || A.length < 3) {
            return -1;
        }
        
        left = 0;
        right = A.length - 1;
        
        while (left < right - 2) {
            int mid = (left + right) / 2;
            
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
                left = mid;
            } else if (A[mid] < A[mid - 1] && A[mid] > A[mid + 1]) {
                right = mid;
            } else {
                right = mid;
            }
        }
        
        return left + 1;
    }
}


/*

以上判断逻辑可以简化为如下：

*/

class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        int left, right;
        
        if (A == null || A.length < 3) {
            return -1;
        }
        
        left = 0;
        right = A.length - 1;
        
        while (left < right - 2) {
            int mid = (left + right) / 2;
            
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] < A[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left + 1;
    }
}
