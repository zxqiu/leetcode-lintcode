/*

Trie Service

Build tries from a list of pairs. Save top 10 for each node.

 Example

Given a list of

<"abc", 2>
<"ac", 4>
<"ab", 9>

Return <a[9,4,2]<b[9,2]<c[2]<>>c[4]<>>>, and denote the following tree structure:

         Root
         / 
       a(9,4,2)
      /    \
    b(9,2) c(4)
   /
 c(2)



解：
把输入的单词放进树中，在放入频率的时候按从大到小顺序插入top10 List中即可。
如果top10 List大小大于10，则删掉最后一个元素。

*/



/**
 * Definition of TrieNode:
 * public class TrieNode {
 *     public NavigableMap<Character, TrieNode> children;
 *     public List<Integer> top10;
 *     public TrieNode() {
 *         children = new TreeMap<Character, TrieNode>();
 *         top10 = new ArrayList<Integer>();
 *     }
 * }
 */
public class TrieService {

    private TrieNode root = null;

    public TrieService() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        // Return root of trie root, and 
        // lintcode will print the tree struct.
        return root;
    }

    // @param word a string
    // @param frequency an integer
    public void insert(String word, int frequency) {
        TrieNode iter = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!iter.children.containsKey(c)) {
                iter.children.put(c, new TrieNode());
            }
            
            TrieNode node = iter.children.get(c);
            int insert_idx;
            for (insert_idx = 0; insert_idx < node.top10.size(); insert_idx++) {
                if (node.top10.get(insert_idx) <= frequency) {
                    break;
                }
            }
            
            node.top10.add(insert_idx, frequency);
            
            if (node.top10.size() == 11) {
                node.top10.remove(10);
            }
            
            iter = node;
        }
    }
 }
