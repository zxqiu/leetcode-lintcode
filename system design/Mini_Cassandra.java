/*

Mini Cassandra

Cassandra is a NoSQL storage. The structure has two-level keys.

Level 1: raw_key. The same as hash_key or shard_key.
Level 2: column_key.
Level 3: column_value
raw_key is used to hash and can not support range query. let's simplify this to a string.
column_key is sorted and support range query. let's simplify this to integer.
column_value is a string. you can serialize any data into a string and store it in column value.

implement the following methods:

insert(raw_key, column_key, column_value)
query(raw_key, column_start, column_end) // return a list of entries


Example

insert("google", 1, "haha")
query("google", 0, 1)
>> [（1, "haha")]


解：
使用HashMap来定位raw_key，每个raw_key对应一个TreeMap。
利用TreeMap自排序特征（基于红黑树）来对column_key排序。
*/














/**
 * Definition of Column:
 * public class Column {
 *     public int key;
 *     public String value;
 *     public Column(int key, String value) {
 *         this.key = key;
 *         this.value = value;
 *    }
 * }
 */
public class MiniCassandra {
    
    HashMap<String, TreeMap<Integer, String>> store;

    public MiniCassandra() {
        store = new HashMap<String, TreeMap<Integer, String>>();
    }
    
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        TreeMap<Integer, String> data;
        if (store.containsKey(raw_key)) {
            data = store.get(raw_key);
        } else {
            data = new TreeMap<Integer, String>();
        }
        
        data.put(column_key, column_value);
        store.put(raw_key, data);
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        List<Column> ret = new ArrayList<Column>();
        
        if (!store.containsKey(raw_key)) {
            return ret;
        }
        
        TreeMap<Integer, String> data = store.get(raw_key);
        for (Map.Entry<Integer, String> entry : data.subMap(column_start, true, column_end, true).entrySet()) {
            ret.add(new Column(entry.getKey(), entry.getValue()));
        }
        
        return ret;
    }
}