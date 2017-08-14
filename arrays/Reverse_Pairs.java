/*

Reverse Pairs


For an array A, if i < j, and A [i] > A [j], called (A [i], A [j]) is a reverse pair.
return total of reverse pairs in A.

Have you met this question in a real interview? Yes
Example
Given A = [2, 4, 1, 3, 5] , (2, 1), (4, 1), (4, 3) are reverse pairs. return 3


解：
merge sort

这道题求有多少组数字满足左边比右边大。

对数组进行merge sort。
将每次递归输入数组分成左右两段，分别统计左右两段内部的reverse pairs，并且在merge时统计左右两边之间的reverse pairs。
把三个统计结果相加并返回。

merge时，如果左边当前值比右边当前值大，那么左边从这个数开始后面所有数都比右边当前值大，需要把左边剩余数量加入统计结果。

*/



public class Solution {
    /**
     * @param A an array
     * @return total of reverse pairs
     */
    public long reversePairs(int[] A) {
        if (A == null || A.length == 0) {
            return 0L;
        }
        
        return mergeSort(A, 0, A.length - 1, new int[A.length]);
    }
    
    private long mergeSort(int[] A, int start, int end, int[] t) {
        if (start == end) {
            t[0] = A[start];
            return 0;
        }
        
        int mid = (start + end) / 2;
        int[] l = new int[mid - start + 1];
        int[] r = new int[end - mid];
        long ret = mergeSort(A, start, mid, l);
        ret += mergeSort(A, mid + 1, end, r);
        ret += merge(l, r, t);
        
        return ret;
    }
    
    private long merge(int[] l, int[] r, int[] t) {
        int left = 0;
        int right = 0;
        int i = 0;
        long ret = 0;
        
        while (left < l.length && right < r.length) {
            if (l[left] > r[right]) {
                ret += l.length - left;
                t[i++] = r[right++];
            } else {
                t[i++] = l[left++];
            }
        }
        
        while (left < l.length) {
            t[i++] = l[left++];
        }
        
        while (right < r.length) {
            t[i++] = r[right++];
        }
        
        return ret;
    }
}
