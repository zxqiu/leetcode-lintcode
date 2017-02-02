/*

Backpack II

Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

 Example

Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.


解：
dynanmic programming
这道题解法和Backpack几乎一致。
把存入dp数组的值换成物品的价值即可。

外层循环遍历物品大小数组，内层循环遍历背包大小从m到0。实际操作时由于背包大小小于当前物品大小时不需要更新dp数组，所以内层循环最小到当前物品大小就行。

*/


public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        int[] dp = new int[m + 1];
        
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], V[i] + dp[j - A[i]]);
            }
        }
        
        return dp[m];
    }
}