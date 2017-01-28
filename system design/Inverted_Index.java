/*

Inverted Index

Create an inverted index with given documents.

 Notice

Ensure that data does not include punctuation.

 Example
 
Given a list of documents with id and content. (class Document)

[
  {
    "id": 1,
    "content": "This is the content of document 1 it is very short"
  },
  {
    "id": 2,
    "content": "This is the content of document 2 it is very long bilabial bilabial heheh hahaha ..."
  },
]
Return an inverted index (HashMap with key is the word and value is a list of document ids).

{
   "This": [1, 2],
   "is": [1, 2],
   ...
}


解：
这道题只需要把每个document中的每个词作为key放进HashMap中，并且把对应的document id组成一个List放进HashMap的value中。
注意需要查重，避免同样的id多次放进某一个key的List里面。

*/


/**
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class Solution {
    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        Map<String, List<Integer>> ret = new HashMap<String, List<Integer>>();
        if (docs == null) {
            return ret;
        }
        
        for (Document doc : docs) {
            String[] words = doc.content.split(" ");
            
            for (String word : words) {
                if (word.length() == 0) {
                    continue;
                }
                if (ret.containsKey(word)) {
                    if (ret.get(word).contains(doc.id)) {
                        continue;
                    }
                    ret.get(word).add(doc.id);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(doc.id);
                    ret.put(word, list);
                }
            }
        }
        
        return ret;
    }
}