import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
/*
 * https://leetcode.com/problems/lru-cache/
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCache {

    static class Entry implements Comparable<Entry> {
        int value;
        int age;
        int key;
        public Entry(int key,int value){
            this.key=key;
            this.value=value;
        }
        public int compareTo(Entry other){
            return this.age - other.age;
        }
    }

    int counter;
    int capacity;
    Map<Integer,Entry> map = new HashMap<>();
    Queue<Entry> queue = new PriorityQueue<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Entry entry = map.get(key);
        if ( entry == null ) return -1;
        queue.remove(entry);
        entry.age=++counter;
        queue.add(entry);
        return entry.value;
    }

    public void put(int key, int value) {
        Entry entry = map.get(key);
        if ( entry == null ){
            if ( map.size() == capacity ){
                map.remove(queue.remove().key);
            }
            entry = new Entry(key, value);
            map.put(key, entry);
        }
        else {
            entry.value=value;
            queue.remove(entry);
        }
        entry.age=++counter;
        queue.add(entry);
    }

    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println("lruCache.get(1): " + lruCache.get(1));    // return 1
        lruCache.put(3, 3); // evicts key 2
        System.out.println("lruCache.get(2): " + lruCache.get(2));    // returns -1 (not found)
        lruCache.put(4, 4); // evicts key 1
        System.out.println("lruCache.get(1): " + lruCache.get(1));    // return -1 (not found)
        System.out.println("lruCache.get(3): " + lruCache.get(3));    // return 3
        System.out.println("lruCache.get(3): " + lruCache.get(4));    // return 4
    }
}



