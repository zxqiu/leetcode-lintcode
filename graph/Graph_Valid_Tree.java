/*

Graph Valid Tree

Given a directed graph, design an algorithm to find out whether there is a route between two nodes.

Example
Given graph:

A----->B----->C
 \     |
  \    |
   \   |
    \  v
     ->D----->E
for s = B and t = E, return true

for s = D and t = C, return false


解：
BFS

Tree有两个特性：
1.边的数量为点的数量减一；
2.不能出现loop。也就是说在满足上一个特性的前提下，以任意一点作为根，应当可以遍历所有点。

用HashMap保存每个点及其neigobors。
用0作为根节点，BFS遍历整个图。
用visited保存访问过的节点。
如果结束时visited的不等于n，说明有点没有访问到，也就是说一定存在loop，应当返回false。
否则返回true。

*/


public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        
        if (n == 0 || edges.length != n - 1) {
            return false;
        }
        
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            
            map.get(a).add(b);
            map.get(b).add(a);
        }
        
        queue.offer(0);
        visited.add(0);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (Integer next : map.get(cur)) {
                if (visited.contains(next)) {
                    continue;
                }
                
                queue.offer(next);
                visited.add(next);
            }
        }
        
        return (visited.size() == n);
    }
}
