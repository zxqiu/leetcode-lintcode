/*

Implement Trie

Implement a trie with insert, search, and startsWith methods.

Notice

You may assume that all inputs are consist of lowercase letters a-z.



 Example

insert("lintcode")
search("code")
>>> false
startsWith("lint")
>>> true
startsWith("linterror")
>>> false
insert("linterror")
search("lintcode)
>>> true
startsWith("linterror")
>>> true


解：
对于Trie，需要解决的问题一是如何查找每个节点的子节点，二是如何确定从root到这个节点为止是否存在单词。
对于第一个问题，可以使用数组或者HashMap。
使用数组的话每次查找子节点需要遍历整个数组，由于最多26个字母，所以是常数复杂度。如果使用HashMap，则每次查找直接判断对应字符是否存在即可，也是常数复杂度。
对于第二个问题，直接在每个节点中保存一个flag用来标记当前节点是否是某个单词的最后一个字符即可。

*/



/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    HashMap<Character, TrieNode> neighbors;
    boolean hasWord;
    
    public TrieNode() {
        neighbors = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (!node.neighbors.containsKey(c)) {
                node.neighbors.put(c, new TrieNode());
            }

            node = node.neighbors.get(c);
        }
        
        node.hasWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = traceWord(word);
        
        return (node == null) ? false : node.hasWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return traceWord(prefix) != null;
    }
    
    private TrieNode traceWord(String word) {
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.neighbors.containsKey(c)) {
                return null;
            }
            
            node = node.neighbors.get(c);
        }
        
        return node;
    }
}
