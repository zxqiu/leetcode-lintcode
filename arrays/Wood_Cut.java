/*

Wood Cut

Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

 Notice

You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge 
O(n log Len), where Len is the longest length of the wood.



 解：
 二分法
 对从1到最长木头的长度进行二分搜索，找出满足条件的最大长度。
 对于已知长度len，用每个木头长度除以len的结果相加即可得到能切多少段。
 如果能切多于或者等于k段，则left右移到len，否则right左移到len。
 
 终止条件是left小于right-1。也就是说退出循环时left==right-1。
 由于最终要求的是某一个长度大于或者等于k，故只要保证退出循环时left<=len<right，最后返回left即可。
 
 为了保证一定有解，首先判断按照最短长度1来切是否可以得到多于或者等于k段，若不能则直接返回0。
 如果按照最长长度来切也会得到多于或者等于k段，则说明不需要切，直接返回最长长度即可。

*/


public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        int left = 1;
        int right = 0;
        
        for (int i : L) {
            right = Math.max(right, i);
        }
        
        if (cut(L, left) < k) {
            return 0;
        } else if (cut(L, right) >= k) {
            return right;
        }
        
        while (left < right - 1) {
            int mid = (int)(((long)left + (long)right) / 2);
            
            if (cut(L, mid) >= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private long cut(int[] L, int len) {
        long cnt = 0;
        
        for (int i : L) {
            cnt += i / len;
        }
        
        return cnt;
    }
}
