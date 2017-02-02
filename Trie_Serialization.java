/*

Trie Serialization


Serialize and deserialize a trie (prefix tree, search on internet for more details).

You can specify your own serialization algorithm, the online judge only cares about whether you can successfully deserialize the output from your own serialize function.

 Notice

You don't have to serialize like the test data, you can design your own format.

 Example

str = serialize(old_trie)
>> str can be anything to represent a trie
new_trie = deserialize(str)
>> new_trie should have the same structure and values with old_trie

An example of test data: trie tree <a<b<e<>>c<>d<f<>>>>, denote the following structure:

     root
      /
     a
   / | \
  b  c  d
 /       \
e         f


解：
Stack

这道题首先要明确的是不可以用BFS。因为BFS占用的空间太多，空间复杂度O(26^h)，h为树的高度。
那么只能使用DFS。而我们知道，如果想直接用DFS的结果还原二叉树，至少需要两种不同方式遍历的结果。更何况这是Trie，情况将会更加复杂。
那么，更好的办法是记录下DFS遍历的完整过程，包含从父节点到子节点的过程，也包含从子节点返回父节点的过程。
换句话说，就是记录下来栈的变化过程。

Serialize:
这里使用'+'来表示如栈过程，'-'来表示出栈过程，那么对于example中的树来说，当'a'作为root的子节点被访问到时，应当在结果字符串中加入"+a"。
当从'e'返回'b'时，应当在结果中加入"-"表示'e'被出栈。
那么从root访问到'e'，再从'e'返回到'a'的过程可以表示为"+a+b+e--"，如此执行完之后，栈中剩余的便是root和'a'，下一个将要访问的是'a'的下一个子节点'c'。

Deserialize:
还原整个树只需要将之前的结果转换成栈的访问过程。
遇到一个'+'则入栈其后的一个字符，并且由于是DFS，所以该字符一定是当前栈顶节点的子节点。
遇到一个'-'则出栈一个节点。
直到整个字符串被遍历。

*/

/**
 * Definition of TrieNode:
 * public class TrieNode {
 *     public NavigableMap<Character, TrieNode> children;
 *     public TrieNode() {
 *         children = new TreeMap<Character, TrieNode>();
 *     }
 * }
 */
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a trie which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TrieNode root) {
        if (root == null) {
            return "";
        }
        
        String s = "";
        
        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
            s += "+" + entry.getKey().toString();
            s += serialize(entry.getValue());
            s += "-";
        }
        
        return s;
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TrieNode deserialize(String data) {
        TrieNode root = new TrieNode();
        Stack<TrieNode> st = new Stack<TrieNode>();
        int idx = 0;
        
        st.push(root);
        
        while (idx < data.length()) {
            if (data.charAt(idx) == '+') {
                TrieNode node = new TrieNode();
                st.peek().children.put(data.charAt(++idx), node);
                st.push(node);
            } else if (data.charAt(idx) == '-') {
                st.pop();
            }
            idx++;
        }
        
        return root;
    }
}
