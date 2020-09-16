import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
/**
 *
 * https://leetcode.com/problems/
 *
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LFUCache {
    static class Entry implements Comparable<Entry>{
        int age;
        int key;
        int value;
        int freq;
        Entry( int key, int value){
            this.key = key;
            this.value=value;
            this.freq=1;
        }
        public int compareTo(Entry other){
            return this.freq==other.freq ? this.age-other.age : this.freq-other.freq;
        }
    }

    int counter;
    int capacity;

    Map<Integer,Entry> map;
    PriorityQueue<Entry> queue;
    public LFUCache(int capacity) {
        this.capacity=capacity;
        map = new HashMap<>();
        queue=new PriorityQueue<>();
    }

    public int get(int key) {
        if ( this.capacity == 0 ) return -1;
        Entry entry = map.get(key);
        if ( entry == null ) return -1;
        queue.remove(entry);
        ++entry.freq;
        entry.age=++counter;
        queue.add(entry);
        return entry.value;
    }

    public void put(int key, int value) {
        if ( this.capacity == 0 ) return;
        Entry entry = map.get(key);
        if ( entry == null ){
            if ( map.size() == this.capacity ){
                map.remove( queue.remove().key );
            }
            entry = new Entry(key,value);
            entry.age=++counter;
            map.put(key,entry);
        }
        else {
            queue.remove(entry);
            entry.value = value;
            ++entry.freq;
            entry.age=++counter;
        }
        queue.add(entry);
    }

    public static void main(String[] args){
        LFUCache lfuCache = new LFUCache(2);

        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println("lfuCache.get(1): " + lfuCache.get(1));    // return 1
        lfuCache.put(3, 3); // evicts key 2
        System.out.println("lfuCache.get(2): " + lfuCache.get(2));    // returns -1 (not found)
        lfuCache.put(4, 4); // evicts key 1
        System.out.println("lfuCache.get(1): " + lfuCache.get(1));    // return -1 (not found)
        System.out.println("lfuCache.get(3): " + lfuCache.get(3));    // return 3
        System.out.println("lfuCache.get(3): " + lfuCache.get(4));    // return 4
    }
}
