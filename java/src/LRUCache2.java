import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/lru-cache/
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCache2 {

    static class Entry {
        int value;
        int key;
        Entry next;
        Entry prev;
        public Entry(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    int capacity;
    Map<Integer,Entry> map = new HashMap<>();
    Entry head;
    Entry tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    void remove(Entry entry){
        Entry next = entry.next;
        Entry prev = entry.prev;
        if ( prev != null ) prev.next=next;
        if ( next != null ) next.prev=prev;
        if ( head == entry ) {
            head = prev;
            if ( head != null ) head.next = null;
        }
        if ( tail == entry ){
            tail = next;
            if ( tail != null ) tail.prev = null;
        }
    }

    void add(Entry entry){
        if ( tail == null ) {
            tail = head = entry;
            entry.next=null;
        }
        else {
            tail.prev = entry;
            entry.next = tail;
            tail = entry;
        }
        entry.prev=null;
    }

    public int get(int key) {
        Entry entry = map.get(key);
        if ( entry == null ) return -1;
        remove(entry);
        add(entry);
        return entry.value;
    }

    public void put(int key, int value) {
        Entry entry = map.get(key);
        if ( entry == null ){
            if ( map.size() == capacity ){
                map.remove(head.key);
                remove(head);
            }
            entry = new Entry(key, value);
            map.put(key, entry);
        }
        else {
            entry.value=value;
            remove(entry);
        }
        add(entry);
    }

    public static void main(String[] args){
        LRUCache2 lruCache = new LRUCache2(3);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        System.out.println("lruCache.get(4): " + lruCache.get(4));    // return 4
        System.out.println("lruCache.get(3): " + lruCache.get(3));    // return 3
        System.out.println("lruCache.get(2): " + lruCache.get(2));    // returns -1 (not found)
        System.out.println("lruCache.get(1): " + lruCache.get(1));    // returns -1 (not found)
        lruCache.put(5, 5); // evicts key 2
        System.out.println("lruCache.get(1): " + lruCache.get(1));    // return -1 (not found)
        System.out.println("lruCache.get(2): " + lruCache.get(2));    // return -1
        System.out.println("lruCache.get(3): " + lruCache.get(3));    // return -1
        System.out.println("lruCache.get(4): " + lruCache.get(4));    // return 4
        System.out.println("lruCache.get(5): " + lruCache.get(5));    // return 5
    }
}



