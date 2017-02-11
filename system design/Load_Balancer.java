/*

Load Balancer


Implement a load balancer for web servers. It provide the following functionality:

    Add a new server to the cluster => add(server_id).
    Remove a bad server from the cluster => remove(server_id).
    Pick a server in the cluster randomly with equal probability => pick().


 Example

At beginning, the cluster is empty => {}.

add(1)
add(2)
add(3)
pick()
>> 1         // the return value is random, it can be either 1, 2, or 3.
pick()
>> 2
pick()
>> 1
pick()
>> 3
remove(1)
pick()
>> 2
pick()
>> 3
pick()
>> 3


解：
Circular Linked List

这道题基本思路是使用一个ArrayList保存server表，然后随机返回其中一个下标对应的server就可以。
但是这样做，add时间复杂度O(1),remove是O(n)，pick是O(1)，并不是很理想。
理想情况下add，remove，pick都应该是O(1)。

分析以下需要解决的问题：
1，如何O(1)的根据server_id删除某一个server。
2，如何保证pick概率平均。

add很简单，大多数数据结构都可以O(1)操作，所以不需要考虑。
为了解决上面的问题，需要的信息有：
1，为了保证O(1)删除，那么首先一定不能使用ArrayList或者数组之类的结构，因为查找server_id必须要遍历。就算根据server_id进行了排序，最快也得O(log(n))。
   可以做到O(1)删除的数据结构最容易想到的是HashMap和HashSet。
2，为了保证pick概率平均，最简单的方案是round robin。用一个pointer轮流指向并返回列表中的server。到达最后一个的时后就返回列表头。
   由于不可以用数组之类依赖下标的结构，那么还能做到从表尾直接返回表头的结构就是circular linked list。

综上，把server_id作为HashMap的key，circular linked list的节点作为HashMap的value，并用一个指针轮流指向这个list，就可以满足条件。
为了简单的在circular linked list中做删除，使用双向指针。

*/


public class LoadBalancer {
    private class Node {
        int id;
        Node prev, next;
        Node(int id) {
            this.id = id;
            prev = this;
            next = this;
        }
    }
    
    Map<Integer, Node> servers;
    Node server;

    public LoadBalancer() {
        servers = new HashMap<>();
        server = null;
    }

    // @param server_id add a new server to the cluster 
    // @return void
    public void add(int server_id) {
        if (servers.containsKey(server_id)) {
            return;
        }
        
        Node newServer = new Node(server_id);
        if (server == null) {
            // initiate first server
            server = newServer;
        }
        
        newServer.next = server.next;
        newServer.prev = server;
        server.next.prev = newServer;
        server.next = newServer;
        
        servers.put(server_id, newServer);
    }

    // @param server_id server_id remove a bad server from the cluster
    // @return void
    public void remove(int server_id) {
        if (!servers.containsKey(server_id)) {
            return;
        }
        
        Node node = servers.get(server_id);
        if (node == server) {
            // current server will be removed. move to next server.
            server = server.next;
        }
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        servers.remove(server_id);
        if (servers.isEmpty()) {
            // if last server is removed, clear pointer
            server = null;
        }
    }

    // @return pick a server in the cluster randomly with equal probability
    public int pick() {
        server = server.next;
        return server.id;
    } 
}
