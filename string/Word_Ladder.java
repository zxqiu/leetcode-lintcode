/*

Word Ladder

Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
 Notice

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.


 Example

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.


解：
BFS

这道题最根本的思路是，每次将下一个循环需要查找的词，以及其对应的字典一并放入队列内。
如果发现某个词对应的字典内包含一个词可以变换成目标，就返回当前循环的次数。
如果这样做，一定会超时，因为字典可以很大，就算删掉某条路径上已经经历过的词依然会很大。

也可以不保存字典，每次从总字典中查找对应词可以变换成的词，然后基于这些词进行计算。
因为字母是有限的（a~z），所以我们尝试将每个词的每一个字符轮番换成a~z，然后判断这些词在字典中是否存在。
由于字典可以转换成HashSet，所以查找字典中是否存在每个词为常数时间复杂度。故查找所有词的复杂度为O(26*word.length)。

每次循环中对队列中的每个词进行字典搜索，找到其可以变换成的词加入队列中。
在循环开始之前，将目标词也加入字典中，这样若发现某个词与目标词一致，就可以返回了。
若最后没有在循环中遇到目标词，则说明目标词不可达。


*/


public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return an integer
      */
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null) {
            return 0;
        }
        
        if (start.equals(end)) {
            return 1;
        }
        
        int cnt = 0;
        Queue<String> words = new LinkedList<String>();
        HashSet<String> dictHash = new HashSet<String>(dict);
        HashSet<String> usedHash = new HashSet<String>();
        
        words.offer(start);
        dictHash.add(end);
        
        while (!words.isEmpty()) {
            int size = words.size();
            cnt++;
            
            for (int i = 0; i < size; i++) {
                String s = words.poll();
                
                for (String d : getDict(s, dictHash)) {
                    if (usedHash.contains(d)) {
                        continue;
                    }
                    if (d.equals(end)) {
                        return cnt + 1;
                    }
                    
                    words.offer(d);
                    usedHash.add(d);
                }
            }
        }
        
        return 0;
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
        StringBuilder sb = new StringBuilder(in);
        sb.setCharAt(idx, c);
        return sb.toString();
    }
}