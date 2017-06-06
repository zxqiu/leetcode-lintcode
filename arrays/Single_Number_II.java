/*

Single Number II



Given 3*n + 1 numbers, every numbers occurs triple times except one, find it.

Example

Given [1,1,2,3,3,3,2,2,4,1] return 4
Challenge

One-pass, constant extra space.


解：
按位统计每个数字的每一位。如果某一位的计数器超过3则清零重来。
由于存在一个单独的数字，故最后留下的就是它。再用位运算还原即可。

*/

public class Solution {
	/**
	 * @param A : An integer array
	 * @return : An integer 
	 */
    public int singleNumberII(int[] A) {
        int[] cnt = new int[32];
        int ret = 0;
        
        for (int a : A) {
            for (int i = 0; i < 32; i++) {
                cnt[i] = (((a & (1 << i)) >> i) + cnt[i]) % 3;
            }
        }
        
        for (int i = 0; i < 32; i++) {
            ret |= (cnt[i] << i);
        }
        
        return ret;
    }
}
