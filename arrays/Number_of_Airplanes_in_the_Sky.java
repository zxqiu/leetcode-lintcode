/*

Number of Airplanes in the Sky 


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
scan line问题。
把所有起降时间放到同一条时间线上，并标记是起飞还是降落。
扫描这条线，遇到一个起飞就对计数器加一，遇到一个降落就对计数器减一。保存计数器的最大值。
注意，如果两个飞机在同一时间一个起飞一个降落，这时空中的飞机数量应当不变。所以应当把降落排在起飞前面。

*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    private class Node {
        int time;
        boolean isLanding;
        
        public Node(int time, boolean isLanding) {
            this.time = time;
            this.isLanding = isLanding;
        }
    }
    
    public int countOfAirplanes(List<Interval> airplanes) { 
        Queue<Node> q = new PriorityQueue(1, new Comparator<Node>() {
            public int compare(Node a, Node b) {
                if (a.time != b.time) {
                    return a.time - b.time;
                }
                
                if (a.isLanding) {
                    return -1;
                }
                
                return 1;
            }
        });
        int cnt = 0;
        int max = 0;
        
        for (Interval i : airplanes) {
            q.offer(new Node(i.start, false));
            q.offer(new Node(i.end, true));
        }
        
        while (!q.isEmpty()) {
            if (q.poll().isLanding) {
                cnt--;
            } else {
                cnt++;
            }
            max = Math.max(max, cnt);
        }
        
        return max;
    }
}
