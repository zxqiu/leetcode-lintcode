/*

Building Outline

Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。

An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.

Building Outline

 Notice

Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.

Example
Given 3 buildings：

[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：

[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]


解：
这是一道扫描线问题。
先把所有起点和终点按照坐标从小到大放在同一条线上（list），然后遍历整条线，每遇到一个高度变化点就保存一次outline。
高度需要存入一个最大堆，每次判断堆顶的高度变化。

每遇到一个点时，先保存当前的堆顶，然后分类讨论如下情况：
1. 遇到一个起点时，首先将该点入堆：
  1）如果该点的高度高于之前的堆顶，那么保存之前堆顶的outline；
  2）如果该点高度小于或者等于之前的堆顶，该点入堆后应当排序在堆顶之下，这样可以避免之前堆顶的左边沿被丢弃。
2. 遇到一个终点时，首先应当将该点对应的起点删掉：
  1）如果删掉的是堆顶：
    1>如果堆变为空，保存旧堆顶outline；
    2>如果新的堆顶和删掉的堆顶高度不一样，那么一定是小于旧堆顶，保存旧堆顶的outline。
      并且将新堆顶的左边沿修改为当前点的坐标，也就是说新的堆顶的outline从当前位置开始。
    3>如果两者高度一样，新堆顶的左边沿应当修改为旧堆顶的左边沿，这样相当于合并了高度一样的两个建筑。
  1）如果删掉的不是堆顶，什么都不用做。
  
对list排序时，应当注意以下原则：
1. 坐标不一样时，从左到右排序；
2. 坐标一样时，如果一个起点一个终点，起点排在前面。这样可以避免同样高度的outline被分割成两条。
   否则怎么排都行。但是需要注意在生成outline时，如果出现起点和终点一样的outline，应当略去。
   
对堆排序时，应当注意：
1. 高度不一样时，高度高的排在前面；
2. 高度一样时，坐标小的排在上面。这样可以保证后来的点不会覆盖之前栈顶的左边沿。
  

*/

public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        Queue<Point> heap;
        ArrayList<Point> list;
        
        if (buildings == null || buildings.length == 0) {
            return ret;
        }
        
        heap = new PriorityQueue<Point>(buildings.length, heightComp);
        list = new ArrayList<Point>();
        
        for (int i = 0; i < buildings.length; i++) {
            Point start, end;
            
            start = new Point(buildings[i][0], buildings[i][2], false);
            end = new Point(buildings[i][1], buildings[i][2], true);
            start.pair = end;
            end.pair = start;
            
            list.add(start);
            list.add(end);
        }
        
        Collections.sort(list, idxComp);
        
        for (Point p : list) {
            if (heap.isEmpty()) {
                heap.offer(p);
                continue;
            }
            
            Point top = heap.peek();
            if (!p.isEnd) {
                heap.offer(p);
                
                if (p.height > top.height) {
                    buildOutline(ret, top.idx, p.idx, top.height);
                }
            } else {
                heap.remove(p.pair);
                
                if (p.pair == top) {
                    if (heap.isEmpty()) {
                        buildOutline(ret, top.idx, p.idx, top.height);
                    } else if (heap.peek().height == top.height) {
                        heap.peek().idx = top.idx;
                    } else {
                        buildOutline(ret, top.idx, p.idx, top.height);
                        heap.peek().idx = p.idx;
                    }
                }
            }
        }
        
        return ret;
    }
    
    private void buildOutline(ArrayList<ArrayList<Integer>> ret, int start, int end, int height) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (start == end) {
            return;
        }
        
        list.add(start);
        list.add(end);
        list.add(height);
        
        ret.add(list);
    }
    
    private class Point {
        int idx;
        int height;
        boolean isEnd;
        Point pair;
        
        Point (int idx, int height, boolean isEnd) {
            this.idx = idx;
            this.height = height;
            this.isEnd = isEnd;
            pair = null;
        }
    }
    
    private Comparator<Point> heightComp = new Comparator<Point>() {
        public int compare(Point a, Point b) {
            if (a.height == b.height) {
                return a.idx - b.idx;
            }
            return b.height - a.height;
        }
    };
    
    private Comparator<Point> idxComp = new Comparator<Point>() {
        public int compare(Point a, Point b) {
            if (a.idx == b.idx && a.isEnd != b.isEnd) {
                return (a.isEnd == false) ? -1 : 1;
            }
            return a.idx - b.idx;
        }
    };
}
