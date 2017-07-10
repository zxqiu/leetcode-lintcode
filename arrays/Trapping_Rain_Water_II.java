/*

Trapping Rain Water II


Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.

Example
Given 5*4 matrix

[12,13,0,12]
[13,4,13,12]
[13,8,10,12]
[12,13,12,12]
[13,13,13,13]
return 14.


解：
BFS

首先有一下要点：
1.四周所有位置无法盛水。
2.利用木桶原理，从四周最低的位置开始，遍历其四周所有未访问过的位置，如果某一点低于该最低位置，其盛水量为两者高度差。
3.如果一个位置可以盛水，那么其周围其他低于或者等于它的位置也一定能盛水。

综上，解法如下：
1.利用最小heap保存四周所有位置，同时记录进visted数组。
2.访问heap中最低点四周的所有位置，如果没有访问过，加入heap并记录进visited数组。如果小于该最低点，计算其存水量。
3.重复2，如果新的位置高度小于上一次高度，计算存水量时应当用之前取出过的最高高度减去当前高度。

*/

public class Solution {
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    
    private class Height {
        int x, y, val;
        Height(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    
    public int trapRainWater(int[][] heights) {
        int ret = 0;
        int lvl = 0;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        Queue<Height> q = new PriorityQueue<Height>(1, new Comparator<Height>() {
                public int compare(Height a, Height b) {
                    return a.val - b.val;
                }
            });
        
        for (int i = 0; i < heights.length; i++) {
            Height h0 = new Height(i, 0, heights[i][0]);
            Height h1 = new Height(i, heights[0].length - 1, heights[i][heights[0].length - 1]);
            q.offer(h0);
            q.offer(h1);
            visited[h0.x][h0.y] = true;
            visited[h1.x][h1.y] = true;
        }
        
        for (int i = 1; i < heights[0].length - 1; i++) {
            Height h0 = new Height(0, i, heights[0][i]);
            Height h1 = new Height(heights.length - 1, i, heights[heights.length - 1][i]);
            q.offer(h0);
            q.offer(h1);
            visited[h0.x][h0.y] = true;
            visited[h1.x][h1.y] = true;
        }
        
        while (!q.isEmpty()) {
            Height h = q.poll();
            lvl = Math.max(h.val, lvl);
            
            for (int i = 0; i < dir.length; i++) {
                int x = h.x + dir[i][0];
                int y = h.y + dir[i][1];
                Height tmp;
                
                if (x < 0 || x >= heights.length
                    || y < 0 || y >= heights[0].length) {
                    continue;
                }
                
                if (visited[x][y]) {
                    continue;
                }
                
                tmp = new Height(x, y, heights[x][y]);
                ret += Math.max(0, lvl - tmp.val);
                visited[x][y] = true;
                q.offer(tmp);
            }
        }
        
        return ret;
    }
};
