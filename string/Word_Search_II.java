/*

Word Search II

Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 


Example
Given matrix:
doaf
agai
dcan
and dictionary:
{"dog", "dad", "dgdg", "can", "again"}

return {"dog", "dad", "can", "again"}


dog:
 [d][o] a  f
  a [g] a  i
  d  c  a  n

dad:
 [d] o  a  f
 [a] g  a  i
 [d] c  a  n

can:
  d  o  a  f
  a  g  a  i
  d [c][a][n]

again:
  d  o  a  f
 [a][g][a][i]
  d  c  a [n]

Challenge 
Using trie to implement your algorithm.


解：
这道题重在理解和应用Trie树。
首先实现一个Trie树，只需要插入和返回根结点两个操作。
可以参考Implement Trie这道题来实现。

然后便历整个board，每个字符都从Trie的根结点开始进行DFS查找，若找到一个词则加入结果列表。
与Search Word类似，每次递归需要判断两个坐标是否合法，同时需要额外判断坐标指向的字符是否在Trie内，若不满足条件则直接返回。
为了避免board中存在重复的单词，可以先把结果放入一个Set，利用Set自查重的特性来避免重复，等到全部处理完再一次加入结果列表并返回。

*/

public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        Trie trie = new Trie();
        ArrayList<String> ret = new ArrayList<String>();
        HashSet<String> set = new HashSet<String>();
        
        if (words == null || board == null || board.length == 0) {
            return ret;
        }
        
        for (String s : words) {
            trie.insert(s);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(board, i, j, set, trie.getRoot());
            }
        }
        
        for (String s : set) {
            ret.add(s);
        }
        
        return ret;
    }
    
    private void helper(char[][] board, int x, int y, HashSet<String> set, TrieNode root) {
        int[][] direct = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        char backup;
        
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
            || !root.children.containsKey(board[x][y])) {
            return;
        } else if (root.children.get(board[x][y]).word.length() > 0) {
            set.add(root.children.get(board[x][y]).word);
        }
        
        backup = board[x][y];
        board[x][y] = '#';
        
        for (int i = 0; i < direct.length; i++) {
            int newX, newY;
            
            newX = direct[i][0] + x;
            newY = direct[i][1] + y;
            
            helper(board, newX, newY, set, root.children.get(backup));
        }
        
        board[x][y] = backup;
    }
    
    class TrieNode {
        HashMap<Character, TrieNode> children;
        char val;
        String word;
        TrieNode(char val, String word) {
            this.val = val;
            this.word = word;
            children = new HashMap<Character, TrieNode>();
        }
    }
    
    class Trie {
        private TrieNode root;
    
        public Trie() {
            root = new TrieNode('a', "");
        }
    
        public void insert(String word) {
            char[] s = word.toCharArray();
            TrieNode next = root;
            
            for (int i = 0; i < s.length; i++) {
                if (!next.children.containsKey(s[i])) {
                    next.children.put(s[i], new TrieNode(s[i], ""));
                }
                
                if (i == s.length - 1) {
                    next.children.get(s[i]).word = word;
                }
                
                next = next.children.get(s[i]);
            }
        }
        
        public TrieNode getRoot() {
            return root;
        }
    }
}
