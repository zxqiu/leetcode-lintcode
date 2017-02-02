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
    char val;
    HashMap<Character, TrieNode> children;
    boolean hasWord;
    public TrieNode(char val) {
        this.val = val;
        children = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('#');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode iter = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!iter.children.containsKey(c)) {
                iter.children.put(c, new TrieNode(c));
            }
            iter = iter.children.get(c);
        }
        iter.hasWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode iter = findStrPos(word);
        
        if (iter != null && iter.hasWord) {
            return true;
        } else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode iter = findStrPos(prefix);
        if (iter != null) {
            return true;
        }
        return false;
    }
    
    public TrieNode findStrPos(String s) {
        TrieNode iter = root;
        for (int i = 0; i < s.length(); i++) {
            if (iter.children.isEmpty() || !iter.children.containsKey(s.charAt(i))) {
                return null;
            }
            iter = iter.children.get(s.charAt(i));
        }
        return iter;
    }
}
