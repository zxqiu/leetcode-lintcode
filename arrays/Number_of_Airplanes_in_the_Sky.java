/*

Number of Airplane in the Sky


Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

 Notice

If landing and flying happens at the same time, we consider landing should happen at first.

Example
For interval list

[
  [1,10],
  [2,3],
  [5,8],
  [4,7]
]
Return 3



解：
扫描线问题。
先把所有点放进一条时间线上，然后从前先后扫描。
每遇到一个起飞点，则统计值增加1，遇到一个降落点则减少1。

这里使用一个TreeMap来对点进行排序。TreeMap的每个节点保存某一个时间点上的起降数量。
注意同一时间可能既有起飞又有降落，此时应当把起飞和降落的架次相减然后在存如该时间点。
保存完所有点后遍历该TreeMap并计算结果。


*/

/**
 * Definition of Interval:
 * public classs Interval {
 * int start, end;
 * Interval(int start, int end) {
 * this.start = start;
 * this.end = end;
 * }
 */

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) { 
        TreeMap<Integer, Integer> dots = new TreeMap<Integer, Integer>();
        int cnt, max;
        
        for (Interval i : airplanes) {
            if (!dots.containsKey(i.start)) {
                dots.put(i.start, 0);
            }
            
            dots.put(i.start, dots.get(i.start) + 1);
            
            if (!dots.containsKey(i.end)) {
                dots.put(i.end, 0);
            }
            
            dots.put(i.end, dots.get(i.end) - 1);
        }
        
        cnt = max = 0;
        for (Integer val : dots.values()) {
            cnt += val;
            max = Math.max(max, cnt);
        }
        
        return max;
    }
}
