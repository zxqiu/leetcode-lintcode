/*

Best Time to Buy and Sell Stock II


Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


 Example

Given an example [2,1,2,0,1], return 2


解：
这道题实际要求的是每个上升区间的最高点和最低点的差。
可以分割成多组一个上升加上一个下降的区间，利用Best Time to Buy and Sell Stock中的方法来计算。

即对每个区间中的每个点计算其最大profit，然后对所有profit求和。
如何分割？
当profit不断增加时，认为处于一个上升区间中。一旦profit下降，就认为上升区间结束，应当把区间profit加入总profit中，并且把min和区间profit置零，进行下一个区间的计算。

在某一个下降区间中，区间profit应当一致为0。只要在每个时间点上先计算min就可以保证这一点。若当前时间点价格低于min，那么min赋值为当前价格，再去计算当前的profit就会得到0。

要注意如果最后一个时间点仍处在一个上升区间中，由于后面没有下降区间来触发区间终止条件，所以在循环结束后要将最后一个区间的profit加入总profit。


这道题还可以通过寻找波峰和波谷来求解最大profit。一样要注意最后一个时间点的终止条件问题。

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
        
        int profit, min, subProfit;
        profit = 0;
        min = prices[0];
        subProfit = 0;
        
        for (int price : prices) {
            min = Math.min(min, price);
            int curProfit = price - min;
            if (subProfit <= curProfit) {
                subProfit = curProfit;
            } else {
                profit += subProfit;
                min = price;
                subProfit = 0;
            }
        }
        
        if (subProfit > 0) {
            profit += subProfit;
        }
        
        return profit;
    }
};