/*

Coins in a Line II

There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?

Example
Given values array A = [1,2,2], return true.

Given A = [1,2,4], return false.


解：
Dynanmic Programming

递推公式：
dp[i]表示先手面对从i到最后一个硬币，获得的value最多比后手多多少（有可能为负）。
从后向前遍历。
思路与Coins in a Line基本一致，分析第一回合的情况：
1.先手拿一个硬币，获得的value为values[i]：
  1）后手拿一个硬币，剩余从i+2到最后一个，两者的value差为values[i]-values[i+1]+dp[i+2]；
  2）后手拿两个硬币，剩余从i+3到最后一个，value差为values[i]-values[i+1]-values[i+2]+dp[i+3]
2.先手拿两个硬币，获得的value为values[i]+values[i+1]：
  1）后手拿一个硬币，剩余从i+3到最后一个，value差为values[i]+values[i+1]-values[i+2]+dp[i+3]
  2）后手拿两个硬币，剩余从i+4到最后一个，value差为values[i]+values[i+1]-values[i+2]+values[i+3]+dp[i+4]

两种后手情况应该取对后手最有利的，也就是最小的。
两种先手情况应该取对先手最有利的，也就是最大的。

故：
dp[i]=max(min(values[i]-values[i+1]+dp[i+2], values[i]-values[i+1]-values[i+2]+dp[i+3]),
          min(values[i]+values[i+1]-values[i+2]+dp[i+3], values[i]+values[i+1]-values[i+2]+values[i+3]+dp[i+4]))
          
初始条件：
需要用到dp[i+4]，故需要初始化前四个。
dp[len] = 0;
dp[len - 1] = values[len - 1];
dp[len - 2] = values[len - 1] + values[len - 2];
dp[len - 3] = values[len - 3] + values[len - 2] - values[len - 1];

*/

public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        } else if (values.length == 1) {
            return true;
        } else if (values.length == 2) {
            return true;
        } else if (values.length == 3) {
            return values[0] + values[1] > values[2];
        }
        
        int[] dp = new int[values.length + 1];
        int len = values.length;
        
        dp[len] = 0;
        dp[len - 1] = values[len - 1];
        dp[len - 2] = values[len - 1] + values[len - 2];
        dp[len - 3] = values[len - 3] + values[len - 2] - values[len - 1];
        
        for (int i = len - 4; i >= 0; i--) {
            int takeOne = Math.min(values[i] - values[i + 1] + dp[i + 2],
                             values[i] - values[i + 1] - values[i + 2] + dp[i + 3]);
            int takeTwo = Math.min(values[i] + values[i + 1] - values[i + 2] + dp[i + 3],
                             values[i] + values[i + 1] - values[i + 2] - values[i + 3] + dp[i + 4]);
            dp[i] = Math.max(takeOne, takeTwo);
        }
        
        return dp[0] > 0;
    }
}
