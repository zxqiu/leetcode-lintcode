/*

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

How we serialize an undirected graph:

Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

   1
  / \
 /   \
0 --- 2
     / \
     \_/


 Example
 
return a deep copied graph.


解：
BFS

基本思路是先把每个节点复制后放进一个队列，然后依次复制他们的邻居，再把复制后的邻居放进该队列。
分成三步来做。
第一步，把第一个节点复制后放进队列，先不复制它的邻居，并把复制后的节点放进一个HashMap用来查重。
第二步，从队列中取出一个节点tmp，遍历它的所有邻居。如果一个邻居不在HashMap中，就表示该邻居还未被复制过，那么复制这个邻居，并把它放进队列，同时把复制后的邻居放进tmp的邻居列表中。如果一个邻居存在与HashMap中，说明这个邻居已经被复制过，直接从HashMap中得到复制后的节点放进tmp的邻居列表。
第三步，重复第二步直到队列为空。


*/



/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Map<Integer, UndirectedGraphNode> created = new HashMap<>();
        Queue<UndirectedGraphNode> next = new LinkedList<>();
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        
        created.put(root.label, root);
        next.offer(node);
        while (!next.isEmpty()) {
            UndirectedGraphNode tmp = next.poll();
            for (UndirectedGraphNode neighbor : tmp.neighbors) {
                if (!created.containsKey(neighbor.label)) {
                    created.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    next.offer(neighbor);
                }
                
                UndirectedGraphNode copyNeighbor = created.get(neighbor.label);
                created.get(tmp.label).neighbors.add(copyNeighbor);
            }
        }
        
        return root;
    }
}