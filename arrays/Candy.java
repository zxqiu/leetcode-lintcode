/*

Candy



There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.

    Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?

Example

Given ratings = [1, 2], return 3.

Given ratings = [1, 1, 1], return 3.

Given ratings = [1, 2, 2], return 4. ([1,2,1]).


解：
这道题首先理解要求：
1，糖果的数量最小值为1。
2，如果一个孩子比他前面孩子rating高，那么应当比前面的孩子多至少一个糖果。
3，如果一个孩子比他后面孩子rating高，那么应当比后面的孩子多至少一个糖果。
4，如果一个孩子跟他前面的孩子rating一样：
  1）如果这个孩子比后面的孩子rating低或者一样，那么这个孩子只有一个糖果。
  2）如果这个孩子比后面孩子rating高，那么这个孩子有几个糖果取决于规则3。


创建并初始化一个数组tmp，用来表示每个孩子对应的糖果。
1，从前向后遍历一次，将所有递增区间应有的值填进tmp。暂时不处理任何递减和相等区间。
2，从后向前遍历一次，将所有递增区间的值填进tmp。根据要求4，注意以下两点：
  1）对于某个rating峰值，也就是高于前后，这个值对应的tmp值在1中应该已经填过一次。这次遍历时填入的值不应当小于之前的值。
  2）对于任意相等值，本次遍历只需要处理最后一个。
3，对tmp中所有数字求和并返回。

*/


public class Solution {
    /**
     * @param ratings Children's ratings
     * @return the minimum candies you must give
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int[] tmp = new int[ratings.length];
        int ret = 0;
        
        Arrays.fill(tmp, 1);
        
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                tmp[i] = tmp[i - 1] + 1;
            }
        }
        
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                tmp[i] = Math.max(tmp[i], tmp[i + 1] + 1);
            }
        }
        
        for (int i : tmp) {
            ret += i;
        }
        
        return ret;
    }
}
