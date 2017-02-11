/*

Web Logger



Implement a web logger, which provide two methods:

    hit(timestamp), record a hit at given timestamp.
    get_hit_count_in_last_5_minutes(timestamp), get hit count in last 5 minutes.

the two methods will be called with non-descending timestamp (in sec).


 Example

hit(1);
hit(2);
get_hit_count_in_last_5_minutes(3);
>> 2
hit(300);
get_hit_count_in_last_5_minutes(300);
>> 3
get_hit_count_in_last_5_minutes(301);
>> 2


解：

方法一：
TreeMap

这道题可以简单的使用一个TreeMap保存每一秒对应的hit数，然后取出时只要遍历这个map并找出所有五分钟以内的hit就可以。
由于最多遍历300次，时间复杂度O(1)。
空间复杂度可以保持在O(300)，只要每次有新的timestamp就取最近五分钟的tailmap。

*/

public class WebLogger {
    SortedMap<Integer, Integer> hitmap;

    public WebLogger() {
        hitmap = new TreeMap<Integer, Integer>();
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        hitmap = hitmap.tailMap(timestamp - 299); // this line can be removed if you do not care about space Complexity
        
        if (!hitmap.containsKey(timestamp)) {
            hitmap.put(timestamp, 0);
        }
        hitmap.put(timestamp, hitmap.get(timestamp) + 1);
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        hitmap = hitmap.tailMap(timestamp - 299);
        int count = 0;
        
        for (Integer val : hitmap.values()) {
            count += val;
        }
        
        return count;
    }
}


/*

方法二：
arrays

这里用一个数组实现类似的功能。每次hit或者取最近hit复杂度都为O(300)。
在每次有新的timestamp时，就把上一次hit到这一次hit之间的所有hit都清空。

这个方法复杂度与上面一致。由于数组操作比TreeMap快，所以稍微快一点点。

*/


public class WebLogger {
    int[] hitmap;
    int lastHit;
    int count;

    public WebLogger() {
        count = 0;
        lastHit = 0;
        hitmap = new int[300];
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        timeFly(timestamp);
        count++;
        hitmap[timestamp % 300]++;
        lastHit = timestamp;
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        timeFly(timestamp);
        return count;
    }
    
    private void timeFly(int timestamp) {
        if (lastHit > 0) {
            if (timestamp - lastHit >= 300) {
                count = 0;
                Arrays.fill(hitmap, 0);
                return;
            }
            
            for (int i = lastHit + 1; i <= timestamp; i++) {
                if (hitmap[i % 300] > 0) {
                    count -= hitmap[i % 300];
                    hitmap[i % 300] = 0;
                }
            }
        }
    }
}
