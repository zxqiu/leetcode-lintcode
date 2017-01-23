/*
Implement a memcache which support the following features:

get(curtTime, key). Get the key's value, return 2147483647 if key does not exist.
set(curtTime, key, value, ttl). Set the key-value pair in memcache with a time to live (ttl). The key will be valid from curtTime to curtTime + ttl - 1 and it will be expired after ttl seconds. if ttl is 0, the key lives forever until out of memory.
delete(curtTime, key). Delete the key.
incr(curtTime, key, delta). Increase the key's value by delta return the new value. Return 2147483647 if key does not exist.
decr(curtTime, key, delta). Decrease the key's value by delta return the new value. Return 2147483647 if key does not exist.
It's guaranteed that the input is given with increasingcurtTime.



Clarification
Actually, a real memcache server will evict keys if memory is not sufficient, and it also supports variety of value types like string and integer. In our case, let's make it simple, we can assume that we have enough memory and all of the values are integers.

Search "LRU" & "LFU" on google to get more information about how memcache evict data.

Try the following problem to learn LRU cache:
http://www.lintcode.com/problem/lru-cache

Example
get(1, 0)
>> 2147483647
set(2, 1, 1, 2)
get(3, 1)
>> 1
get(4, 1)
>> 2147483647
incr(5, 1, 1)
>> 2147483647
set(6, 1, 3, 0)
incr(7, 1, 1)
>> 4
decr(8, 1, 1)
>> 3
get(9, 1)
>> 3
delete(10, 1)
get(11, 1)
>> 2147483647
incr(12, 1, 1)
>> 2147483647

*/


public class Memcache {
    private class Data {
        int value;
        int ttl;
        int editTime;
        
        public Data(int value, int ttl, int editTime) {
            this.value = value;
            this.ttl = ttl;
            this.editTime = editTime;
        }
    }
    
    HashMap<Integer, Data> cache;

    public Memcache() {
        cache = new HashMap<Integer, Data>();
    }

    public int get(int curtTime, int key) {
        int value = 2147483647;
        if (!cache.containsKey(key)) {
            return value;
        }
        
        Data data = cache.get(key);
        if (data.ttl == 0 || data.ttl + data.editTime - 1 >= curtTime) {
            value = data.value;
        }
        
        return value;
    }

    public void set(int curtTime, int key, int value, int ttl) {
        Data data = new Data(value, ttl, curtTime);
        cache.put(key, data);
    }

    public void delete(int curtTime, int key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }
    
    public int incr(int curtTime, int key, int delta) {
        int value = 2147483647;
        if (!cache.containsKey(key)) {
            return value;
        }
        
        Data data = cache.get(key);
        if (data.ttl == 0 || data.ttl + data.editTime - 1 >= curtTime) {
            data.value += delta;
            value = data.value;
            cache.put(key, data);
        } else {
            cache.remove(key);
        }
        
        return value;
    }

    public int decr(int curtTime, int key, int delta) {
        int value = 2147483647;
        if (!cache.containsKey(key)) {
            return value;
        }
        
        Data data = cache.get(key);
        if (data.ttl == 0 || data.ttl + data.editTime - 1 >= curtTime) {
            data.value -= delta;
            value = data.value;
            cache.put(key, data);
        } else {
            cache.remove(key);
        }
        
        return value;
    }
}