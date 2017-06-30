/*

Gas Station



There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
Notice

The solution is guaranteed to be unique.

Example

Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.
Challenge

O(n) time and O(1) extra space


解：
从最后一点出发，如果可以到到下一个点，则前进，然后再计算能否到达再下一个点。
如果不能到达下一个点，则让起点后退。从后退之后之后的点到后退之前的点，如果有汽油有剩余，且余量足以使汽车前进到希望到达的点，就继续前进。否则继续后退直到可以到达为止。
如果退到了希望到达的点，或者前进到了出发点，则推出循环。
然后判断此时剩余的gas，如果大于等于0则说明可以完成一圈，否则说明不能完成。


*/


public class Solution {
    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length != gas.length) {
            return -1;
        }
        
        int start = gas.length - 1;
        int cur = 0;
        int left = gas[start] - cost[start];
        
        while (start > cur) {
            if (left >= 0) {
                left += gas[cur] - cost[cur];
                cur++;
            } else {
                start--;
                left += gas[start] - cost[start];
            }
        }
        
        return (left >= 0) ? start : -1;
    }
}
