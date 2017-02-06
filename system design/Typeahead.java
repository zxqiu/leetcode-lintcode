/*

Typeahead

Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. The dictionary will give at the initialize method and wont be changed. The method to find all words with given substring would be called multiple times.


 Example

Given dictionary = {"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}

search "Zhang", return ["Jason Zhang", "Bob Zhang"].

search "James", return ["James Yu"].


解：

这道题有两种做法，一是Inverted Index，二是Trie Tree。
两种做法的区别在于如何存储关键字。
这里使用查找效率更高的Trie Tree。

Trie Tree如何实现请参考Implement Trie和Trie Service。
在存储的时候，每个Trie Node都要保存这个节点和以下所有孩子节点的热门词，以便快速返回而无需遍历。
需要注意的是在插入新关键词的时候，应该把每个词从到到尾的每个字符作为开始的词都插入一次。这样才可以保证用户从一个词的中间某个字符开始输入时也能快速查找到热门词列表。

*/



public class Typeahead {
    private class TrieNode {
        Map<Character, TrieNode> children;
        Set<String> tops;
        TrieNode() {
            children = new HashMap<Character, TrieNode>();
            tops = new HashSet<String>();
        }
    }
    
    TrieNode root;
    
    private void insert(String word, String s) {
        if (word.length() == 0) {
            return;
        }
        
        TrieNode iter = root;
        for (char c : word.toCharArray()) {
            if (!iter.children.containsKey(c)) {
                iter.children.put(c, new TrieNode());
            }
            iter = iter.children.get(c);
            if (!iter.tops.contains(s)) {
                iter.tops.add(s);
            }
        }
        
        insert(word.substring(1, word.length()), s);
    }
    
    // @param dict A dictionary of words dict
    public Typeahead(Set<String> dict) {
        root = new TrieNode();
        for (String s : dict) {
            insert(s, s);
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        List<String> ret = new ArrayList<String>();
        TrieNode iter = root;
        
        // search for the str in Trie
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!iter.children.containsKey(c)) {
                iter = null;
                break;
            }
            iter = iter.children.get(c);
        }

        if (iter != null) {
            // str found
            for (String s : iter.tops) {
                ret.add(s);
            }
        }
        
        return ret;
    }
}
