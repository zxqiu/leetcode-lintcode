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

/*

方法二：
BFS

参见Wikipedia:

Kahn's algorithm[edit]
One of these algorithms, first described by Kahn (1962), works by choosing vertices in the same order as the eventual topological sort. First, find a list of "start nodes" which have no incoming edges and insert them into a set S; at least one such node must exist in a non-empty acyclic graph. Then:

L ← Empty list that will contain the sorted elements
S ← Set of all nodes with no incoming edges
while S is non-empty do
    remove a node n from S
    add n to tail of L
    for each node m with an edge e from n to m do
        remove edge e from the graph
        if m has no other incoming edges then
            insert m into S
if graph has edges then
    return error (graph has at least one cycle)
else 
    return L (a topologically sorted order)
If the graph is a DAG, a solution will be contained in the list L (the solution is not necessarily unique). Otherwise, the graph must have at least one cycle and therefore a topological sorting is impossible.
Reflecting the non-uniqueness of the resulting sort, the structure S can be simply a set or a queue or a stack. Depending on the order that nodes n are removed from set S, a different solution is created. A variation of Kahn's algorithm that breaks ties lexicographically forms a key component of the Coffman–Graham algorithm for parallel scheduling and layered graph drawing.


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
        HashMap<DirectedGraphNode, Integer> in_cnt = new HashMap<DirectedGraphNode, Integer>();
        ArrayList<DirectedGraphNode> ret = new ArrayList<DirectedGraphNode>();
        Queue<DirectedGraphNode> next = new LinkedList<DirectedGraphNode>();
        
        countInDegree(in_cnt, graph);
        
        for (DirectedGraphNode node : graph) {
            if (!in_cnt.containsKey(node)) {
                next.offer(node);
            }
        }
        
        while (!next.isEmpty()) {
            DirectedGraphNode node = next.poll();
            ret.add(node);
            
            for (DirectedGraphNode neighbor : node.neighbors) {
                in_cnt.put(neighbor, in_cnt.get(neighbor) - 1);
                if (in_cnt.get(neighbor) == 0) {
                    next.offer(neighbor);
                }
            }
        }
        
        return ret;
    }
    
    private void countInDegree(HashMap<DirectedGraphNode, Integer> in_cnt, ArrayList<DirectedGraphNode> graph) {
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!in_cnt.containsKey(neighbor)) {
                    in_cnt.put(neighbor, 0);
                }
                in_cnt.put(neighbor, in_cnt.get(neighbor) + 1);
            }
        }
    }
}
