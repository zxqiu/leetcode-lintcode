/*

Max Points on a Line



Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example

Given 4 points: (1,2), (3,6), (0,0), (1,3).

The maximum number is 3.



解：
为了判断任意两个点组成的直线，必须要计算任意两点，故时间复杂度至少是O(n^2).
换句话说这道题需要硬解。

确定一条直线需要斜率和直线上的任意一点。
以每个点作为起点的直线，计算其与后面所有点的斜率，如果出现相同的斜率，则说明在同一条直线上。

需要考虑两种特殊情况：
1.某一点与起点重和。此时斜率不存在，并且该点存在于任何经过起点的直线上。故需要单独统计。
2.某一点与起点垂直。此时斜率也不存在，需要单独统计。

其他情况计算斜率并保存在一个Map中，每遇到一个相同的斜率，就对Map该项的值加1。
计算完某一起点后面的所有点之后，需要对获得的最大值加上与起点重合点的数量，再与全局最大值比较。

*/


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param points an array of point
     * @return an integer
     */
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        
        int max = 1;
        
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> map = new HashMap<Double, Integer>();
            int same = 1;
            int vCnt = 0;
            int maxLoop = 0;
            Point a = points[i];
            
            for (int j = i + 1; j < points.length; j++) {
                Point b = points[j];
                Double slope;
                
                if (a.x == b.x && a.y == b.y) {
                    same++;
                    continue;
                } else if (a.x == b.x) {
                    vCnt++;
                    maxLoop = Math.max(maxLoop, vCnt);
                    continue;
                } else {
                    slope = ((double)(a.y - b.y)) / ((double)(a.x - b.x));
                    if (!map.containsKey(slope)) {
                    map.put(slope, 0);
                    }
                    
                    map.put(slope, map.get(slope) + 1);
                    maxLoop = Math.max(maxLoop, map.get(slope));
                }
            }
            
            max = Math.max(max, maxLoop + same);
        }
        
        return max;
    }
}
