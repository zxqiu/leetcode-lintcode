/*

Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.


Example
Given array [3,2,3,1,2], return 1.


解：
Greedy

在某一个时间区间内，想要通过一笔交易获得最大利益，那么一定是在某个价格最低的时间m买入，并在m之后的某个最高价时间点n卖出。

对每个时间点，只要搞清楚在这个时间点之前的最低价格，就可以算出这个时间上可以获得的最大利润。

计算每个时间点的最大利润并求出最大值就是想要的结果。

*/


public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int min, profit;
        min = prices[0];
        profit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }
        
        return profit;
    }
}