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
    private class TreeNode {
        Set<String> tops;
        Map<Character, TreeNode> neighbors;
        public TreeNode() {
            tops = new HashSet<String>();
            neighbors = new HashMap<Character, TreeNode>();
        }
    }
    
    TreeNode root;
    
    // @param dict A dictionary of words dict
    public Typeahead(Set<String> dict) {
        root = buildTree(dict);
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        TreeNode next = root;
        
        for (char c : str.toCharArray()) {
            if (!next.neighbors.containsKey(c)) {
                return new ArrayList<String>();
            }
            next = next.neighbors.get(c);
        }
        
        return new ArrayList<String>(next.tops);
    }
    
    private TreeNode buildTree(Set<String> dict) {
        TreeNode _root = new TreeNode();
        
        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                TreeNode next = _root;
                String subWord = word.substring(i);
                for (char c : subWord.toCharArray()) {
                    if (!next.neighbors.containsKey(c)) {
                        next.neighbors.put(c, new TreeNode());
                    }
                    
                    next = next.neighbors.get(c);
                    next.tops.add(word);
                }
            }
        }
        
        return _root;
    }
}
