/*

Add and Search Word

Design a data structure that supports the following two operations: addWord(word) and search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

 Notice

You may assume that all words are consist of lowercase letters a-z.

Example
addWord("bad")
addWord("dad")
addWord("mad")
search("pad")  // return false
search("bad")  // return true
search(".ad")  // return true
search("b..")  // return true


解：
TrieTree
根据输入构建一个TrieTree。
可以参考Implement Trie：
https://github.com/zxqiu/leetcode-lintcode/blob/master/system%20design/Implement_Trie.java

1.搜索的时候如果遇到"."，就用当前节点的每一个neighbor作为根节点递归搜索substing(i+1)。
如果有任意一个neighbor返回了true，就直接返回true，否则返回false。

2.如果遇到其他字符，在neighbors中搜索当前字符，找不到就返回false，否则继续查找下一个字符。

*/

public class WordDictionary {
    public class TrieNode {
        HashMap<Character, TrieNode> neighbors;
        boolean hasWord;
        
        public TrieNode() {
            neighbors = new HashMap<Character, TrieNode>();
            hasWord = false;
        }
    }
    
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(root, word);
    }
    
    private boolean searchHelper(TrieNode node, String word) {
        int i;
        
        for (i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (c == '.') {
                for (TrieNode n : node.neighbors.values()) {
                    if (searchHelper(n, word.substring(i + 1))) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!node.neighbors.containsKey(c)) {
                    return false;
                }
                
                node = node.neighbors.get(c);
            }
        }
        
        return (i == word.length()) && node.hasWord;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
