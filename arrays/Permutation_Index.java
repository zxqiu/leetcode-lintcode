/*

Permutation Index


Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.

Example
Given [1,2,4], return 1.

解：
如果有四个数字，总共有4!种排列。
第一个数字有4种可能，后面有3！种，共有4*3！种。
对于一个固定的四个数的排列，第一个数字m之后如果有n个小于m的数，该排列之前应当有n*3！+其后的排列数量。
故总共有 : m0*3!+m1*2!+m2*1！+m1*0!

m表示该数后面有多少小于该数的数字。


*/


public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */
    public long permutationIndex(int[] A) {
        long ret = 1;
        long fact = 1;
        
        if (A == null || A.length == 0) {
            return 0;
        }
        
        for (int i = A.length - 1; i >= 0; i--) {
            int cnt = 0;
            
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[i]) {
                    cnt++;
                }
            }
            
            ret += cnt * fact;
            fact *= A.length - i;
        }
        
        return ret;
    }
}
