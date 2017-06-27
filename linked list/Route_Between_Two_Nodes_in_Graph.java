/*

Route Between Two Nodes in Graph

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
用BFS搜索s开始的每个节点的neighbors，把没有处理过的neighbor加入队列。
如果从队中取出的节点为t，返回true。
队列为空时返回false。

*/


/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<DirectedGraphNode>();
 *     }
 * };
 */
public class Solution {
   /**
     * @param graph: A list of Directed graph node
     * @param s: the starting Directed graph node
     * @param t: the terminal Directed graph node
     * @return: a boolean value
     */
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph, 
                            DirectedGraphNode s, DirectedGraphNode t) {
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        Set<DirectedGraphNode> used = new HashSet<DirectedGraphNode>();
        
        queue.offer(s);
        used.add(s);
        
        while(!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            if (node == t) {
                return true;
            }
            
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!used.contains(neighbor)) {
                    queue.offer(neighbor);
                    used.add(neighbor);
                }
            }
        }
        
        return false;
    }
}
