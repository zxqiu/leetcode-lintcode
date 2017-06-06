/*

Single Number III


Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example

Given [1,2,2,3,4,4,5,3] return 1 and 5
Challenge

O(n) time, O(1) extra space.


解：
分治法

这道题需要拆解成两个Single Number问题。

先计算出所有数异或的结果，也就是两个single number的异或结果。
找出这个结果中任意一个值为1的bit，用该bit把A中所有数分成两组，这两个数分属一组。
对每组分别进行异或，分别得到的结果就是这两个数。

*/

public class Solution {
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        int xor, diff, candidate0, candidate1;
        List<Integer> ret = new ArrayList<Integer>();
        
        if (A == null || A.length == 0) {
            return ret;
        }
        
        xor = candidate0 = candidate1 = 0;
        
        /* find xor of the two numbers */
        for (int i : A) {
            xor ^= i;
        }
        
        /* find last "1" bit to seperate A into two group */
        diff = xor & (xor ^ (xor - 1));
        
        /* seperate A into to group and find the two numbers */
        for (int i : A) {
            if ((i & diff) == 0) {
                candidate0 ^= i;
            } else {
                candidate1 ^= i;
            }
        }
        
        ret.add(candidate0);
        ret.add(candidate1);
        
        return ret;
    }
}
