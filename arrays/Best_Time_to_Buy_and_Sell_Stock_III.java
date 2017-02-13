/*

Best Time to Buy and Sell Stock III


Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Notice

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


 Example

Given an example [4,4,6,1,1,4,2,5], return 6.


解：
dynamic programming

在某一个时间t，完成两个交易获取最大利益的方法是在0到t之间完成一次最大profit交易，在t+1到最后一天也完成一次最大profit交易。
需要用到Best Time to Buy and Sell Stock的方法来求dp数组。
maxProfitL[i]表示从0到i的最大profit。利用之前的方法正向从头计算到尾。
maxProfitR[i]表示从i到最后一天的最大profit。一样利用之前的方法从尾计算到头。

*/


class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int[] maxProfitL = new int[prices.length];
        int[] maxProfitR = new int[prices.length];
        int min = prices[0];
        int max = prices[prices.length - 1];
        int profit = 0;
        
        // find out max profit from left to right
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfitL[i] = Math.max(maxProfitL[i - 1], prices[i] - min);
        }
        
        // find out max profit from right to left
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            maxProfitR[i] = Math.max(maxProfitR[i + 1], max - prices[i]);
        }
        
        // find out overall max profit
        for (int i = 1; i < prices.length - 1; i++) {
            profit = Math.max(profit, maxProfitL[i] + maxProfitR[i + 1]);
        }
        profit = Math.max(profit, maxProfitL[prices.length - 1]);
        
        return profit;
    }
};