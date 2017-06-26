/*

Unique Binary Search Trees

Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?

Have you met this question in a real interview? Yes
Example
Given n = 3, there are a total of 5 unique BST's.

1           3    3       2      1
 \         /    /       / \      \
  3      2     1       1   3      2
 /      /       \                  \
2     1          2                  3


解：
Dynanmic Programming
dp[i]表示1~i可以有多少个独特查找树。

递推公式：
有多少个独特查找数，取决于1~i中每一个数字作为root时，其左右子树有多少种可能性。
故：
dp[i] = dp[0]*dp[i - 1] + dp[1]*dp[i - 2] + dp[2]*dp[i - 3] + ... + dp[i - 1]*dp[0]

初始条件，由于计算dp[i]时涉及到小于i的所有结果，所以至少初始化dp[0]。
而dp[0]表示空树，只有一种情况，故：
dp[0] = 1

*/


public class Solution {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        
        return dp[n];
    }
}
