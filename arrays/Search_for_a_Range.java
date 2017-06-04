/*

Search for a Range

Given a sorted array of n integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

Example
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Challenge 
O(log n) time.


解：
先用二分法找到任意一个target，然后分别往前后搜索第一个和最后一个target的index。

*/

public class Solution {
    /** 
     * @param A : an integer sorted array
     * @param target :  an integer to be inserted
     * return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        int[] ret = {-1, -1};
        int left, right, mid;
        
        left = mid = 0;
        right = A.length - 1;
        
        while (left <= right) {
            mid = (left + right) / 2;
            
            if (A[mid] == target) {
                break;
            } else if (A[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        if (left <= right) {
            for (left = mid; left >= 0 && A[left] == target; left--);
            for (right = mid; right < A.length && A[right] == target; right++);
            ret[0] = ++left;
            ret[1] = --right;
        }
        
        return ret;
    }
}

