/*

Best Time to Buy and Sell Stock IV




Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.
Notice

You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).


Example

Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.
Challenge

O(nk) time.


解：
Dynanmic Programming

递推公式：
local[i][j]表示最多交易j次时，i天内的最大profit，且第i天必须完成一笔交易。
global[i][j]表示最多交易j次时，i天内的最大profit，第i天可以交易也可以不交易。
注意这j次交易都需要在i天之内完成，不能延续到i天之后。

计算local时，需要考虑两种可能性，在其中取较大的那个：
1，第i-1天在交易。第j次交易在i-1天已经完成，但是延续到第i天，profit为local[i-1][j]+prices[i]-prices[i-1]
2，第i-1天没交易。这种情况如果要比上一种情况获得的值更大，那第j次交易一定是第i-1天买入，第i天卖出。
    原因是：
      如果第j-1次交易在第i-1天完成，那么也就是说i-1天之内完成了最多j-1次交易，这样一定比情况1中i-1天只能最多j次交易获得的profit小或者相等。
      所以如果情况2想要在i-1天之内比情况1获得更多的profit，一定是第i-1天价格下跌，故之前的最后一次交易在i-1天之前完成。
      并且，第i天价格上涨，可以从第i-1天到第i天获利。
    这种情况下的profit为：
    global[i-1][j-1]+prices[i]-prices[i-1]

global的计算分析两种可能：
1，第i天交易：local[i][j]
1，第i天不交易：global[i-1][j]

故：
local[i][j] = max(global[i-1][j-1]+prices[i]-prices[i-1], local[i-1][j]+prices[i]-prices[i-1])
global[i][j] = max(global[i-1][j], local[i][j])

初始条件：
只需要用到i-1和j-1，故初始化i=0和j=0即可。
这两种情况下所有profit都为0。

方法一：
直接使用上面描述的方法求解。
这样的话空间复杂度过高，无法通过。

*/

class Solution {
    /**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        k = (k > prices.length / 2) ? prices.length / 2 : k;
        
        int[][] local = new int[prices.length][k + 1];
        int[][] global = new int[prices.length][k + 1];
        
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(0, diff), local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        
        return global[prices.length - 1][k];
    }
};

/*

方法二：
由于只需要用到i-1，故更之前的信息不需要保存，所以可以直接用一维数组代替二维数组。
需要注意的是j需要改成从后向前遍历，否则要用到的值会被覆盖掉。

*/

class Solution {
    /**
     * @param k: An integer
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        k = (k > prices.length / 2) ? prices.length / 2 : k;
        
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(0, diff), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        
        return globa[lk];
    }
};
