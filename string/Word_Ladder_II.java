/*

Word Ladder II

Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

 Notice

All words have the same length.
All words contain only lowercase alphabetic characters.



 Example

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
  

解：
BFS

这道题的基本思路和word ladder一致，不同的是我们需要记录每个点的前驱，以便在找到end时回溯整条路径。
其次，由于最低深度的那一层可能会有多个符合条件的结果，去重只能在层与层之间，而非每次查找之间。
由于时BFS，当找到end时，不需要想更深层查找，所以使用found标志。

这里用一个树来保存每个字符串的前驱。由于只需要前驱，所以这个树的每个节点都不保存孩子节点。
在找到end时，只需要回溯当前节点的父节点，直到找到start为止。

*/



public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return a list of lists of string
      */
      
    private class TreeNode {
        TreeNode parent;
        String val;
        
        TreeNode(TreeNode parent, String val) {
            this.parent = parent;
            this.val = val;
        }
    }
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (start == null || end == null || dict == null) {
            return ret;
        }
        
        if (start.equals(end)) {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(start);
            ret.add(tmp);
            return ret;
        }
        
        Queue<TreeNode> words = new LinkedList<TreeNode>();
        HashSet<String> dictHash = new HashSet<String>(dict);
        TreeNode head = new TreeNode(null, start);
        
        boolean found = false;
        
        words.offer(head);
        dictHash.add(end);
        
        while (!found && !words.isEmpty()) {
            HashSet<String> used = new HashSet<String>();
            int size = words.size();
            for (int i = 0; i < size; i++) {
                TreeNode word = words.poll();
                
                for (String d : getDict(word.val, dictHash)) {
                    TreeNode node = new TreeNode(word, d);
                    
                    words.offer(node);
                    used.add(d);
                    
                    if (d.equals(end)) {
                        ret.add(buildPath(node));
                        found = true;
                    }
                }
            }
            dictHash.removeAll(used);
        }
        
        return ret;
    }
    
    // get dictionary for s from dict
    private Set<String> getDict(String s, HashSet<String> dict) {
        Set<String> ret = new HashSet<String>();
        
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    continue;
                }
                
                String t = setCharAt(s, i, c);
                if (dict.contains(t)) {
                    ret.add(t);
                }
            }
        }
        
        return ret;
    }
    
    // set in[idx] to c
    private String setCharAt(String in, int idx, char c) {
        char[] out = in.toCharArray();
        out[idx] = c;
        return new String(out);
    }
    
    private List<String> buildPath(TreeNode end) {
        List<String> ret = new ArrayList<String>();
        TreeNode iter = end;
        
        while (iter != null) {
            ret.add(0, iter.val);
            iter = iter.parent;
        }
        
        return ret;
    }
}