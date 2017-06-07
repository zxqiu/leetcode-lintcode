/*

Backpack


Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
Notice

You can not divide any item into small pieces.

Example

If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.
Challenge

O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.



解：
dynanmic programming

这道题用来分析DP的优化再好不过。
基本思路是计算当前物品放进背包后，生于空间最多能放多少物品。

方法一：
dp[i][j]表示A[i-1]放入后尺寸为j的背包后，该背包最多能放多少东西。

递推公式：
A[i-1]放入后，剩余空间为j-A[i-1]。
故只要找到空间为j-A[i-1]的背包最多能放多少东西进去即可。该背包的大小不能为负数。
注意同一个东西不能重复放入，故我们对A排序，然后只需要找出空间j-A[i-1]的背包在放入A[i-1]之前最多能放多少东西。A[i-1]之后的东西一定放不下，不需要考虑。
故：
dp[i][j] = max(dp[1][j-A[i-1]], dp[2][j-A[i-1]], ... , dp[i-1][j-A[i-1]]), j-A[i-1] > 0

初始条件：
任何大小的背包什么都不放时占用空间为0。
dp[0][j] = 0

该方法时间复杂度超过了O(n*m)，会超时。

*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int[][] dp  = new int[A.length + 1][m + 1];
        int max = 0;

        Arrays.sort(A);

        for (int i = 1; i <= A.length; i++) {
            for (int j = m; j > 0; j--) {
                if (j - A[i - 1] < 0) {
                    break;
                }
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j - A[i - 1]]);
                }
                dp[i][j] += A[i - 1];
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max;
    }
}

/*

方法二：
对上面方法进行简单优化。
由于A.length往往大于m，故将两个循环交换一下。
虽然时间复杂度不变，结果竟然AC了。。


*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int[][] dp  = new int[m + 1][A.length + 1];
        int max = 0;

        Arrays.sort(A);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= A.length; j++) {
                if (i - A[j - 1] < 0) {
                    break;
                }
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - A[j -1]][k]);
                }
                dp[i][j] += A[j - 1];
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max;
    }
}

/*

方法三：
上面两个方法存在大量重复计算。
改良以下dp的定义：
dp[i][j]表示尺寸为j的背包在计算到A[i]时最多能装多少东西，A[i]可装可不装。

由以上定义可知，此时的dp[i][j]是全局最大。

递推公式：
装A[i]时，最多能装的量为尺寸j-A[i-1]的背包在不装A[i-1]时最大的装载量加上A[i-1]：
dp[i][j] = dp[i-1][j-A[i-1]] + A[i-1]
不装时，最多能装的量为尺寸j的背包不装A[i-1]时的装载量：
dp[i][j] = dp[i-1][j]

两者取最大值。
需要注意的是当装如A[i-1]后的剩余空间小于0时，只能选择不装A[i-1]。

此时时间复杂度O(m*n)

*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int[][] dp  = new int[A.length + 1][m + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = m; j > 0; j--) {
                dp[i][j] = dp[i - 1][j];
                if (j - A[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - A[i - 1]] + A[i - 1],
                                    dp[i - 1][j]);
                }
            }
        }
        
        return dp[A.length][m];
    }
}


/*

方法四：
观察上面递推公式发现，计算dp[i][j]只需要用到dp[i-1][k]，k小于j，故可以反向遍历背包大小，并去掉i，把dp变成一维数组。
由于现在计算下一个物品时，上一个物品的值自动传递，故不需要再判断当前物品装不下的情况。

此时得到最优解：
时间O(m * n)，空间O(m)

*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        int[] dp  = new int[m + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = m; j > 0; j--) {
                if (j - A[i - 1] >= 0) {
                    dp[j] = Math.max(dp[j - A[i - 1]] + A[i - 1],
                                    dp[j]);
                }
            }
        }
        
        return dp[m];
    }
}
