/*

Minimum Spanning Tree


Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find some edges, connect all the cities and spend the least amount.
Return the connects if can connect all the cities, otherwise return empty list.

 Notice

Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.

Have you met this question in a real interview? Yes
Example
Gievn the connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]

Return ["Acity","Bcity",1], ["Acity","Ccity",2]


解:
最小生成树问题。
先将边排序，然后从头遍历，把每个不会构成环的边加入结果数组。

利用union find的思路来判断是否构成环：
如果任意两个节点的根节点相同，那么连接这两个节点就会形成环。

union find参照：
https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf

*/

/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    
    Comparator<Connection> comparator = new Comparator<Connection>() {
        public int compare(Connection a, Connection b) {
            if (a.cost != b.cost) {
                return a.cost - b.cost;
            }
            if (!a.city1.equals(b.city1)) {
                return a.city1.compareTo(b.city1);
            }
            return a.city2.compareTo(b.city2);
        }
    };
    
    public List<Connection> lowestCost(List<Connection> connections) {
        HashMap<String, Integer> cities = new HashMap<String, Integer>();
        List<Connection> ret = new ArrayList<Connection>();
        int[] parent;
        int cnt = 0;
        
        for (Connection c : connections) {
            if (!cities.containsKey(c.city1)) {
                cities.put(c.city1, cnt++);
            }
            if (!cities.containsKey(c.city2)) {
                cities.put(c.city2, cnt++);
            }
        }
        
        Collections.sort(connections, comparator);
        
        parent = new int[cnt];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        for (Connection c : connections) {
            int idx1 = cities.get(c.city1);
            int idx2 = cities.get(c.city2);
            
            int parent1 = find(parent, idx1);
            int parent2 = find(parent, idx2);
            
            if (parent1 != parent2) {
                parent[parent1] = parent2;
                ret.add(c);
            }
        }
        
        return (ret.size() == cnt - 1) ? ret : new ArrayList<Connection>();
    }
    
    private int find(int[] parent, int idx) {
        while (parent[idx] != idx) {
            parent[idx] = parent[parent[idx]];
            idx = parent[idx];
        }
        
        return idx;
    }
}
