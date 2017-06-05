/*

Search in Rotated Sorted Array II

Follow up for Search in Rotated Sorted Array:

What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.

Example
Given [1, 1, 0, 1, 1, 1] and target = 0, return true.
Given [1, 1, 1, 1, 1, 1] and target = 0, return false.


解：
首先回答，duplicates不会影响复杂度。
因为这道题的本质是二分搜索，而二分搜索并不会受到重复数字的影响。

只需要在Search in Rotated Sorted Array的基础上处理一些相同的情况。
需要注意当left指向值和right指向值相同时，只需要把right不断左移或者把left不断右移，直到不相等为止。

*/

public class Solution {
    /** 
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean 
     */
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return false;
        }
        
        int left, right;
        left = 0;
        right = A.length - 1;
        
        while (left < right - 1) {
            int mid = (left + right) / 2;
            
            if (A[mid] == target) {
                return true;
            } else if (A[left] < A[right]) {
                if (A[mid] > target) {
                    right = mid - 1;
                } else if (A[mid] < target) {
                    left = mid + 1;
                }
            } else if (A[left] > A[right]) {
                if (target >= A[left]) {
                    if (A[mid] > target) {
                        right = mid - 1;
                    } else {
                        if (A[mid] >= A[left]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                } else if (target < A[left]) {
                    if (A[mid] < target) {
                        left = mid + 1;
                    } else {
                        if (A[mid] >= A[left]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                }
            } else if (A[left] == A[right]) {
                right--;
            }
        }
        
        return (A[left] == target) ? true :
                (A[right] == target) ? true : false;
    }
}

