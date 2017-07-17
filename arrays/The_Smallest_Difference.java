/*

The Smallest Difference


Given two array of integers(the first array is array A, the second array is array B), now we are going to find a element in array A which is A[i], and another element in array B which is B[j], so that the difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.

Example
For example, given array A = [3,6,7,4], B = [2,8,9,3], return 0

Challenge 
O(n log n) time


解：
1.对A数组排序。
2.遍历B数组，得到数组i，用二分查找在B中找到与i最相近的数，并求差。
3.对每次求得的差求最小值。


*/

public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        int ret = Integer.MAX_VALUE;
        
        Arrays.sort(A);
        
        for (int i : B) {
            ret = Math.min(ret, smallestDiff(i, A));
        }
        
        return ret;
    }
    
    private int smallestDiff(int target, int[] A) {
        int left = 0;
        int right = A.length - 1;
        
        if (target < A[0]) {
            return A[0] - target;
        } else if (target > A[A.length - 1]) {
            return target - A[A.length - 1];
        }
        
        while (left < right - 1) {
            int mid = (left + right) / 2;
            
            if (A[mid] == target) {
                return 0;
            } else if (A[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        return Math.min(target - A[left], A[right] - target);
    }
}
