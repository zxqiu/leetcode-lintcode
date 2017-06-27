/*

Anagrams

Given an array of strings, return all groups of strings that are anagrams.

 Notice

All inputs will be in lower-case

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Challenge 
What is Anagram?
- Two strings are anagram if they can be the same after change the order of characters.


解：
这道题的关键是用某一种方法找出anagrams的hash key，然后把拥有相同key的单词放进一个列表。
最后如果某一个列表的大小大约1，那么将这个列表里的所有单词都放进结果列表。

最简单的方法是把单词里面的字母排序，变成新的单词后作为hash key。
这样拥有相同字母的单词就会被放进同一个列表。

*/


public class Solution {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> ret = new ArrayList<String>();
        
        for (String s : strs) {
            char[] c = s.toCharArray();
            String tmp;
            
            Arrays.sort(c);
            tmp = String.valueOf(c);
            
            if (!map.containsKey(tmp)) {
                map.put(tmp, new ArrayList<String>());
            }
            
            map.get(tmp).add(s);
        }
        
        for (List<String> list : map.values()) {
            if (list.size() > 1) {
                ret.addAll(list);
            }
        }
        
        return ret;
    }
}
