/*

Topological Sorting


Given an directed graph, a topological order of the graph nodes is defined as follow:

    For each directed edge A -> B in graph, A must before B in the order list.
    The first node in the order can be any node in the graph with no nodes direct to it.

Find any topological order for the given graph.
Notice

You can assume that there is at least one topological order in the graph.
Have you met this question in a real interview?
Clarification

Learn more about representation of graphs
Example

For graph as follow:

picture

The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...

Challenge

Can you do it in both BFS and DFS?


方法一：
DFS

参见Wikipedia :

Depth-first search

An alternative algorithm for topological sorting is based on depth-first search. The algorithm loops through each node of the graph, in an arbitrary order, initiating a depth-first search that terminates when it hits any node that has already been visited since the beginning of the topological sort or the node has no outgoing edges (i.e. a leaf node):

L ← Empty list that will contain the sorted nodes
while there are unmarked nodes do
    select an unmarked node n
    visit(n) 

function visit(node n)
    if n has a temporary mark then stop (not a DAG)
    if n is not marked (i.e. has not been visited yet) then
        mark n temporarily
        for each node m with an edge from n to m do
            visit(m)
        mark n permanently
        unmark n temporarily
        add n to head of L

Each node n gets prepended to the output list L only after considering all other nodes which depend on n (all descendants of n in the graph). Specifically, when the algorithm adds node n, we are guaranteed that all nodes which depend on n are already in the output list L: they were added to L either by the recursive call to visit() which ended before the call to visit n, or by a call to visit() which started even before the call to visit n. Since each edge and node is visited once, the algorithm runs in linear time. This depth-first-search-based algorithm is the one described by Cormen et al. (2001); it seems to have been first described in print by Tarjan (1976).

*/

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ret = new ArrayList<DirectedGraphNode>();
        HashSet<Integer> used = new HashSet<Integer>();
        
        if (graph == null || graph.size() == 0) {
            return ret;
        }
        
        for (DirectedGraphNode node : graph) {
            if (!used.contains(node.label)) {
                helper(node, new HashSet<Integer>(), used, ret);
            }
        }
        
        return ret;
    }
    
    private void helper(DirectedGraphNode node, HashSet<Integer> path, HashSet<Integer> used, ArrayList<DirectedGraphNode> ret) {
        if (ret == null || path.contains(node.label)) {
            ret = null;
            return;
        }
        
        if (used.contains(node.label)) {
            return;
        }
        
        path.add(node.label);
        
        for (DirectedGraphNode next : node.neighbors) {
            helper(next, path, used, ret);
        }
        
        if (ret == null) {
            return;
        }
        
        ret.add(0, node);
        used.add(node.label);
        path.remove(node.label);
    }
}



